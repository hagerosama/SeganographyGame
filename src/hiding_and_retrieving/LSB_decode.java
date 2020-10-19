package hiding_and_retrieving;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LSB_decode {
     public static final String STEGIMAGEFILE = "steg.png";
     
     private String b_msg;
     public int len;

     public LSB_decode() {
          b_msg = "";
          len = 0;
     }
     
     
     public static void main(String[] args) throws Exception {
          LSB_decode lsb = new LSB_decode();
          BufferedImage yImage = lsb.readImageFile();
          lsb.DecodeTheMessage(yImage);
          System.out.println("The msg is : "+lsb.getMsg());
     }
     
     public BufferedImage readImageFile() {
          BufferedImage theImage = null;
          File p = new File(STEGIMAGEFILE);
          try {
               theImage = ImageIO.read(p);
          } catch (IOException e) {
               System.err.println(e);
               System.exit(1);
          }
          return theImage;
     }
     
     public void DecodeTheMessage(BufferedImage yImage) throws Exception {
          
          int j = 0;
          int currentBitEntry = 0;
          String bx_msg = "";
          for (int x = 0; x < yImage.getWidth(); x++) {
               for (int y = 0; y < yImage.getHeight(); y++) {
                    if (x == 0 && y < 8) {
                         //System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+" "+b_msg);
                         int currentPixel = yImage.getRGB(x, y);
                         int red = currentPixel >> 16;
                         red = red & 255;
                         int green = currentPixel >> 8;
                         green = green & 255;
                         int blue = currentPixel;
                         blue = blue & 255;
                         String x_s = Integer.toBinaryString(blue);
                         bx_msg += x_s.charAt(x_s.length() - 1);
                         len = Integer.parseInt(bx_msg, 2);
                         
                    } else if (currentBitEntry < len * 8) {
//System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+" "+b_msg);
                         int currentPixel = yImage.getRGB(x, y);
                         int red = currentPixel >> 16;
                         red = red & 255;
                         int green = currentPixel >> 8;
                         green = green & 255;
                         int blue = currentPixel;
                         blue = blue & 255;
                         String x_s = Integer.toBinaryString(blue);
                         b_msg += x_s.charAt(x_s.length() - 1);
                         
                         currentBitEntry++;
                         //System.out.println("curre "+currentBitEntry);
                    }
               }
          }
          //System.out.println("bin value of msg hided in img is " + b_msg);
     }
     
     public String getMsg()
     {
          String msg = "";
          for (int i = 0; i < len * 8; i = i + 8) {
               
               String sub = b_msg.substring(i, i + 8);
               
               int m = Integer.parseInt(sub, 2);
               char ch = (char) m;
               //System.out.println("m " + m + " c " + ch);
               msg += ch;
          }
          return msg;
     }
}
