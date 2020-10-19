package hiding_and_retrieving;

public abstract class EncryptAndDecrypt {
     protected java.awt.image.BufferedImage img;
     protected String msg;
     protected int key;
     protected int imgWidth;
     protected int imgLenth;
     protected String[] binaryMsg;
     protected abstract void CreateText();
    
    protected void createImg()
    {
        this.imgLenth = this.img.getHeight();
        this.imgWidth = this.img.getWidth();
    }
    
}
