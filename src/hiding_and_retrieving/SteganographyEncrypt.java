package hiding_and_retrieving;

import java.util.stream.IntStream;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.util.Pair;

public class SteganographyEncrypt extends EncryptAndDecrypt
{
    private int msgLen;
    
    public Image encrypt(Image image, String message) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage copy = new WritableImage(image.getPixelReader(), width, height);
        PixelWriter writer = copy.getPixelWriter();
        PixelReader reader = image.getPixelReader();

        boolean[] bits = changePixel(message);

        IntStream.range(0, bits.length)
                .mapToObj(i -> new Pair<>(i, reader.getArgb(i % width, i / width)))
                .map(pair -> new Pair<>(pair.getKey(), bits[pair.getKey()] ? pair.getValue() | 1 : pair.getValue() &~ 1))
                .forEach(pair -> {
                    int x = pair.getKey() % width;
                    int y = pair.getKey() / width;

                    writer.setArgb(x, y, pair.getValue());
                });

        return copy;
    }
    
    @Override
    protected void CreateText()
    {
        binaryMsg = new String[msgLen+1];
        for(int i=0; i<msgLen; i++)
        {
            binaryMsg[i] = Integer.toBinaryString(msg.charAt(i));
        }
        binaryMsg[msgLen] = "00000000";
        msgLen++;
        msg+='\n';
    }
    
    private boolean[] changePixel(String message) {
        byte[] data = message.getBytes();

        // int = 32 bits
        // byte = 8 bits
        boolean[] bits = new boolean[32 + data.length * 8];

        // encode length
        String binary = Integer.toBinaryString(data.length);
        while (binary.length() < 32) {
            binary = "0" + binary;
        }

        for (int i = 0; i < 32; i++) {
            bits[i] = binary.charAt(i) == '1';
        }

        // [7, 6, 5 ... 0]
        // encode message
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];

            for (int j = 0; j < 8; j++) {
                bits[32 + i*8 + j] = ((b >> (7 - j)) & 1) == 1;
            }
        }

        return bits;
    }
}
