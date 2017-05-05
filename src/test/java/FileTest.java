import org.junit.Test;

/**
 * Created by chock on 2017/5/5.
 */
public class FileTest {
    @Test
    public static void main(String[] args) {
        String FileName = "E:/test.txt";
        String inputStr = "insert String To File";
        FileMethod fileMethod = new FileMethod();
        fileMethod.TextToFile(FileName, inputStr);
    }
}