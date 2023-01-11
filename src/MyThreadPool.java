import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class MyThreadPool implements Callable{

    private  String nameOFile;

    public MyThreadPool(String nameOFile) {
        this.nameOFile = nameOFile;
    }

    @Override
    public Object call() throws Exception {
        Integer lineCount = 0 ;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.nameOFile));
            while (reader.readLine() != null){
                lineCount++;
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lineCount;
    }
}
