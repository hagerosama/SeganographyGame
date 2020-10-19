package hiding_and_retrieving;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class TwoBits {
     protected final char key;
     private final static String fileName = "2bits.png";
     protected int height,width;
     public TwoBits() {
          key = '~';
     }
     public static String getFileName(){
          return fileName;
     }
     protected BufferedImage createImage(String file){
          Image img;
          try {
               img = new Image(new FileInputStream(file));
          } catch (FileNotFoundException e) {
               System.exit(1);
               img = null;
          }
          BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
          return bi;
     }
}
