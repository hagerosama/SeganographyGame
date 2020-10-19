package game_play;
import files_package.FileOperations;
import hiding_and_retrieving.Get2Bits;
import hiding_and_retrieving.HideIn2Bits;
import hiding_and_retrieving.LSB_Encode;
import hiding_and_retrieving.LSB_decode;
import hiding_and_retrieving.SteganographyDecrypt;
import hiding_and_retrieving.SteganographyEncrypt;
import java.awt.AlphaComposite;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class SceneFrame {
     
     private javafx.scene.image.Image usedImage;
     public static String category = "";
     public static String word;
     //use the randomly generated file paths
     public static int photoOf = 1;
     private String scene , num ;
     private File f1;
     private File f2;
     private Timer ti;
     //randomly generated numbers to be used as the x and y coordinates in which the small image will be put
     private int rndm;
     //width, height and min used to decide the range in which the random number will be generated
     private final int width, height, min;
     //count to number of failed clicks
     private int count = 0;
     
     //initialise the gui components
     private JFrame mainFrame;
     private ImageIcon img;
     private JLabel myLabel;
     private BufferedImage sceneImg,numImg;
     private final Graphics2D g;
     public static void main(String[] args) {
          //create object of the class to use it in referencing variables in both static and non-static methods (make all variables usuable in all methods)
          SceneFrame program = new SceneFrame();
          program.graphics();
          //displaying the two merged images in a JFrame
          program.display();          
     }
     
     public SceneFrame(){
          scene = FileOperations.newSceneImage();
          num = FileOperations.newPuzzleImage(photoOf);
          mainFrame = new JFrame("Steganography");
          if(photoOf>3)
               PlayFrame.main(null);
          f1 = new File(scene);
          f2 = new File(num);
          
          usedImage = setBits();
          //turn the file path of the numberImage and scenImg into images to work with
          try {
               sceneImg = ImageIO.read(f1);
               numImg = ImageIO.read(f2);
          } catch (IOException e) {
               System.err.println("Can't read files\n"+e);
          }
          //get image width and height to use later for random positioning
          width = sceneImg.getWidth();
          height = sceneImg.getHeight();
          min = 0;
          generateRndm();
          //create an object of the graphics library to use later in merging the two images
          g = sceneImg.createGraphics();
     }

     public void display() {
          displayLoading();
          //setting up the frame
          mainFrame.setLayout(new FlowLayout());
          //creating an ImageIcon from the merged image
          JLabel jLabel = new JLabel();
          timer(jLabel);
          mainFrame.add(jLabel);
          img = new ImageIcon(sceneImg);
          //passing the ImageIcon to the label component
          myLabel = new JLabel(img);
          labelListener();
          mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          mainFrame.getContentPane().add(myLabel);
          mainFrame.pack();
          mainFrame.setLocationRelativeTo(null);
          mainFrame.setVisible(true);
     }

     private void timer(JLabel jLabel3) {
          ti = new Timer();
          ti.schedule(new TimerTask() {
               int c = 20;
               @Override
               public void run() {
                    jLabel3.setText("Timer: " + (c--));
                    if (c == -1) {
                         JOptionPane.showMessageDialog(null, "Time ran out! You Lost.");
                         mainFrame.dispose();
                         photoOf = 1;
                         new PlayFrame().setVisible(true);
                    }
               }
          }, 0, 1000);

     }

     private void labelMouseClicked(java.awt.event.MouseEvent evt) {

          //get the coordinates of the point where the mouse clicked
          Point p = evt.getPoint();
          //extract the x and y coordinate out of point
          int pX = (int) p.getX();
          int pY = (int) p.getY();
          int newRndm = rndm;
          //will be used in constricting the range of the clicks on the x axis to only the width of the numberImage
          int nwRndm = rndm + 20;
          //compare the y of the click to the y's on the numberImage and the x of the click is constricted to the width of the numberImage
          for (int j = 0; j < 20; newRndm++, j++) {
               if (pY == newRndm && pX >= newRndm && pX <= nwRndm) {
                    //System.out.println("image found");
                    ti.cancel();
                    //The hidden word appears here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    JOptionPane.showMessageDialog(null, "the hidden word was: " + getBits());
                    photoOf++;
                    if (photoOf > 3) {
                         mainFrame.dispose();
                         JOptionPane.showMessageDialog(mainFrame, "You Won!!!\nThe hidden message is: "+word);
                         photoOf = 1;
                         count = 0;
                         PlayFrame.main(null);
                         return;
                    }
                    mainFrame.dispose();
                    scene = FileOperations.newSceneImage();
                    num = FileOperations.newPuzzleImage(photoOf);
                    main(null);
                    break;
               }
          }
          //count limit is 2 because it is 0 indexed (3 trials)
          if (count > 2) {
               JOptionPane.showMessageDialog(null, "Game Over");
               ti.cancel();
               photoOf = 1;
               count = 0;
               mainFrame.dispose();
               new PlayFrame().setVisible(true);
          }

     }
     
     private void displayLoading() {
          JFrame jf = new JFrame("Loading Screen");
          jf.add(new JLabel("Loading..."));
          jf.setLayout(new FlowLayout());
          jf.setVisible(true);
          jf.pack();
          
          try {
               Thread.sleep(1000);
          } catch (InterruptedException ex) {
               Logger.getLogger(SceneFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
          jf.dispose();
     }
     
     private void generateRndm(){
          rndm = (int) (Math.random() * (height -min + 1) + min);
     }
     
     public int getRndm() {
          return rndm;
     }
     
     public void graphics(){
          //change the size of the numberImage
          Image newNumberImage = null;
          try {
               newNumberImage = numImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
          } catch (NullPointerException e) {
               System.err.println("No worries\nIt's all right \u263A");
               mainFrame.dispose();
               photoOf = 1;
               count = 0;
               PlayFrame.main(null);
          }
          //choosing the setting in which we want to merge the two images
          g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
          //merge the two images and put the numberImage in the randomly chosen position
          g.drawImage(newNumberImage, rndm, rndm, null);
          g.dispose();
     }
     
     private void labelListener(){
          myLabel.addMouseListener(new java.awt.event.MouseAdapter() {
               @Override
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    labelMouseClicked(evt);
                    //updating the count each time the mouse is clicked
                    count++;
               }
          });
     }
     
     private javafx.scene.image.Image setBits(){
          switch(photoOf){
               case 1:{
                    LSB_Encode lsb = new LSB_Encode();
                    int [] bits = lsb.bit_Msg(word.split(" ")[photoOf-1]);
                    BufferedImage bi = lsb.readImageFile(num);
                    try {
                         lsb.hideTheMessage(bits, bi);
                         return SwingFXUtils.toFXImage(bi, null);
                    } catch (Exception e) {
                         System.err.println(e);
                         System.exit(1);
                         return null;
                    }
                    
               }
               case 2:{
                    HideIn2Bits hib = new HideIn2Bits(num);
                    return SwingFXUtils.toFXImage(hib.hide(word.split(" ")[photoOf-1]), null);
               }
               case 3:{
                    SteganographyEncrypt se = new SteganographyEncrypt();
                    try {
                         return se.encrypt(new javafx.scene.image.Image(new FileInputStream(num)), word.split(" ")[photoOf - 1]);
                    } catch (FileNotFoundException e) {
                         System.err.println(e);
                         System.exit(1);
                    }
               }
               default:
                    System.exit(1);
                    return null;
          }
               
     }
     
     private String getBits(){
          switch(photoOf){
               case 1:{
               try {
                    LSB_decode b_decode = new LSB_decode();
                    b_decode.DecodeTheMessage(b_decode.readImageFile());
                    return b_decode.getMsg();
               } catch (Exception ex) {
                    System.err.println(ex);
                    System.exit(1);
                    break;
               }
               }
               case 2:{
                    try {
                         return new Get2Bits().retrieve();
                    } catch (Exception e) {
                         System.err.println(e);
                         System.exit(1);
                         break;
                    }
                    
               }
               case 3:{
                    SteganographyDecrypt sd = new SteganographyDecrypt();
                    return sd.decrypt(usedImage);
               }
               default:return null;
          }
          return null;
     }
     
}
