package PartA;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;


class Ex2_1Test {

    @Test
    public void Tests() {
        String[] fileNames = new String[100];
        fileNames = Ex2_1.createTextFiles(100, 8, 1000);
        Instant start = Instant.now();
        int numOfLines = Ex2_1.getNumOfLines(fileNames);
        Instant end = Instant.now();
        long time = Duration.between(start, end).toMillis();
        System.out.println("Time using the 'getNumOfLines' function : " + time + " ms");
        System.out.println("Number of lines: " + numOfLines);

        Ex2_1 help = new Ex2_1();
        System.out.println("----------------------------------");
        start = Instant.now();
        numOfLines = help.getNumOfLinesThreads(fileNames);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("Time using the 'getNumOfLinesThreads' function : " + time + " ms");
        System.out.println("Number of lines: " + numOfLines);
        System.out.println("----------------------------------");

        start = Instant.now();
        numOfLines = help.getNumOfLinesThreadPool(fileNames);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("Time using the 'getNumOfLinesThreadPool' function : " + time + " ms");
        System.out.println("Number of lines: " + numOfLines);
        System.out.println("----------------------------------");

        String[] fileNames2 = new String[1000];
        fileNames2 = Ex2_1.createTextFiles(1000, 8, 1000);
        Instant start2 = Instant.now();
        int numOfLines2 = Ex2_1.getNumOfLines(fileNames2);
        Instant end2 = Instant.now();
        long time2 = Duration.between(start2, end2).toMillis();
        System.out.println("Time using the 'getNumOfLines' function : " + time2 + " ms");
        System.out.println("Number of lines: " + numOfLines2);

        Ex2_1 help2 = new Ex2_1();
        System.out.println("----------------------------------");
        start2 = Instant.now();
        numOfLines2 = help.getNumOfLinesThreads(fileNames2);
        end2 = Instant.now();
        time2 = Duration.between(start2, end2).toMillis();
        System.out.println("Time using the 'getNumOfLinesThreads' function : " + time2 + " ms");
        System.out.println("Number of lines: " + numOfLines2);
        System.out.println("----------------------------------");

        start2 = Instant.now();
        numOfLines2 = help.getNumOfLinesThreadPool(fileNames2);
        end2 = Instant.now();
        time2 = Duration.between(start2, end2).toMillis();
        System.out.println("Time using the 'getNumOfLinesThreadPool' function : " + time2 + " ms");
        System.out.println("Number of lines: " + numOfLines2);

    }

}

