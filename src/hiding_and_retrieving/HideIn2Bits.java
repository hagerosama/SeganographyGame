package hiding_and_retrieving;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

public class HideIn2Bits extends TwoBits{
     private final String imgFile;
     
     public HideIn2Bits(String imgFile) {
          this.imgFile = imgFile;
     }
     
     public BufferedImage hide(String msg){
          msg+=key;
          BufferedImage bi = createImage(imgFile);
          if(bi==null){
               System.err.println("File not found");
               System.exit(1);
          }
          height = bi.getHeight();
          width = bi.getWidth();
          int[] pixels = hideMsg(bi, msg);
          bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
          bi.setRGB(0, 0, width, height, pixels, 0, width);
          try {
               System.out.println(ImageIO.write(bi, "png", new File(getFileName())));
          } catch (IOException e) {
          }
          return bi;
     }
     private String msgToBinString(String msg){
          byte[] messageByteArray = new byte[msg.length()];
          String binaryStrings ="";
          int I=0;
          for(char c:msg.toCharArray())
          {
               messageByteArray[I] = (byte)c;
               I++;
          }
          
          for(byte by:messageByteArray)
          {
               binaryStrings += String.format("%8s", Integer.toBinaryString(by)).replace(' ', '0');
          }
          return binaryStrings;
     }
     private int [] hideMsg(BufferedImage bi, String msg){
          final int [] pixels = ((DataBufferInt)bi.getRaster().getDataBuffer()).getData();
          String binaryStrings = msgToBinString(msg);
          int k = 0;
          boolean complete = false;
          for(int i = 0;i<pixels.length;i++)
          {
               byte [] bs = ByteBuffer.allocate(4).putInt(pixels[i]).array();
               for(int j = 1;j<4;j++,k+=2)
               {
                    
                    if(k>=binaryStrings.length()){ complete = true;break;}
                    byte temp = (byte) (bs[j] & 0b11111100);
                    int t = Integer.valueOf(binaryStrings.charAt(k)+""+binaryStrings.charAt(k+1),2);
                    temp+=t;
                    bs[j] = temp;
               }
               int x = 0,y=24;
               for(byte a : bs){ 
                    x+=(a&0xff)<<y;
                    y-=8;
               }
               pixels[i] = x;
               if(complete)break;
          }
          return pixels;
     }
}
