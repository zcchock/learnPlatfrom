import org.junit.Test;

/**
 * Created by chock on 2017/5/5.
 */
public class FileTest {
    public static void main(String[] args) {
//        String FileName = "E:/test.txt";
//        String inputStr = "insert String To File";
        FileMethod fileMethod = new FileMethod();
//        fileMethod.TextToFile(FileName, inputStr);
        String FileName = "E:/test.txt";
        String output = "";
        try {
            output = fileMethod.ReadTxtFile(FileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(output);
    }


}