package hiding_and_retrieving;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.ByteBuffer;

public class Get2Bits extends TwoBits{
     public String retrieve()throws Exception{
          BufferedImage bi = createImage(getFileName());
          if(bi==null){
               System.err.println("Cannot Read File");
               return null;
          }
          return getMsg(bi);
     }
     private String getMsg(BufferedImage bi){
          final int [] pixels = ((DataBufferInt)bi.getRaster().getDataBuffer()).getData();
          String msg1="",M="";
          char c;
          ss:for(int i = 0;i<pixels.length;i++)
          {
               byte[] bs = ByteBuffer.allocate(4).putInt(pixels[i]).array();
               for(int j = 1;j<4;j++)
               {
                    /*
                    110101 & 11
                    */
                    String teString = String.format("%2s", Integer.toBinaryString(bs[j]&0b11)).replace(' ', '0');
                    msg1+=teString;
                    //System.out.println(teString+" " +msg1.length());
                    if(msg1.length()%8==0)
                    {
                         int temp = Integer.valueOf(msg1.substring(msg1.length()-8),2);
                         c = (char)temp;
                         System.out.println(c);
                         if(c==key)
                              break ss;
                         M+=c;
                    }
               }
          }
          return M;
     }
}
