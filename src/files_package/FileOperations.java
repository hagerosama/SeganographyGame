package files_package;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public abstract class FileOperations {
     public static String newSceneImage() {
        Random rand = new Random();
        int n = rand.nextInt(11) + 1;
        String sceneName = "Scene" + n + ".jpg";
        return sceneName;
    }
     public static String newPuzzleImage(int num){
        Random rand = new Random();
        int m = rand.nextInt(5) + 1;
        String ImageName = Integer.toString(num) + Integer.toString(m);
        return ImageName+".jpg";
    }
     public static String fileReader(String category) {
        String line = null;
        String fileName = category + ".txt";
        //System.out.println(FileName);
        try {
            File file = new File(fileName);
            int c = 0;
            Scanner scanner = new Scanner(file);
            Random random = new Random();
            int r = random.nextInt(18) + 1;
            while (scanner.hasNextLine() && c != r) {
                line = scanner.nextLine();
                c++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("error, please insert the valid files");
        }
        return line;
    }
}
