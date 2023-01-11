import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class Ex2_1 {

    public static String[] createTextFiles(int n, int seed, int bound) {
        String fileArr[] = new String[n];
        Random random = new Random(seed);
        for (int i = 1; i <= n; i++) {
            try {
                int line = random.nextInt(bound);
                FileWriter myWriter = new FileWriter("file_" + i + ".txt");
                for (int j = 1; j <= line; j++) {
                    myWriter.write("Hello world\n");
                }
                myWriter.close();
                fileArr[i - 1] = ("file_" + i + ".txt");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return fileArr;
    }

    public static int getNumOfLines(String[] fileNames){
        int lineCount = 0;
        try {
            for (int i = 0; i < fileNames.length; i++) {
                BufferedReader reader = new BufferedReader(new FileReader(fileNames[i]));
                while (reader.readLine() != null){
                    lineCount++;
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lineCount;
    }

    public  int getNumOfLinesThreads(String[] fileNames){
        int count = 0 ;
        for(int i = 0 ; i <fileNames.length ; i++){
            MyThread t1 = new MyThread(fileNames[i]);
            t1.run();
            count += t1.getNumOfLines();
        }
        return count;
    }

    public  int getNumOfLinesThreadPool(String[] fileNames){
        Integer lineCount = 0 ;
        try {
            ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
            List<Future<Integer>> list = new ArrayList<Future<Integer>>();
            for(int i = 0 ; i <fileNames.length ; i++) {
                list.add(executor.submit(new MyThreadPool(fileNames[i]))) ;
            }
            for (Future<Integer> future : list)
                lineCount += future.get();
            executor.shutdown();
        }catch ( InterruptedException | ExecutionException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return lineCount;
    }

    public static void main(String[] args) {
        String [] fileNames = new String[100];
        fileNames = createTextFiles(100 , 8 , 1000);
        Instant start = Instant.now();
        int numOfLines = getNumOfLines(fileNames);
        Instant end = Instant.now();
        long time = Duration.between(start, end).toMillis();
        System.out.println("Time using the 'getNumOfLines' function : " +time + " ms" );
        System.out.println("Number of lines: "+numOfLines);

        Ex2_1 help = new Ex2_1();
        System.out.println("----------------------------------");
        start = Instant.now();
        numOfLines = help.getNumOfLinesThreads(fileNames);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("Time using the 'getNumOfLinesThreads' function : " +time + " ms");
        System.out.println("Number of lines: "+numOfLines);
        System.out.println("----------------------------------");

        start = Instant.now();
        numOfLines = help.getNumOfLinesThreadPool(fileNames);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("Time using the 'getNumOfLinesThreadPool' function : " +time + " ms");
        System.out.println("Number of lines: "+numOfLines);

    }
}
