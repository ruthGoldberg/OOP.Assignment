import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyThread extends Thread {
    private  String nameOFile;
    private int numOfLines;

    public MyThread(String nameOFile) {
       this.nameOFile = nameOFile;
    }

    @Override
    public void run (){
        int lineCount = 0 ;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.nameOFile));
            while (reader.readLine() != null){
                lineCount++;
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.numOfLines = lineCount;
    }

    public String getNameOFile() {
        return nameOFile;
    }

    public int getNumOfLines() {
        return numOfLines;
    }
}
