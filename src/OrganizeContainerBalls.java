import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class OrganizeContainerBalls {

    // Complete the organizingContainers function below.
    static String organizingContainers(int[][] container) {

    	long[] sumR = new long[container.length];
    	long[] sumC = new long[container.length];

    	for(int i = 0; i < container.length;i++) {
    		for(int j = 0; j < container.length; j++) {
    			sumR[i]+=container[i][j];
    		}
    	}
    	
    	for(int i = 0; i < container.length;i++) {
    		for(int j = 0; j < container.length; j++) {
    			sumC[i]+=container[j][i];
    		}
    	}
    	
    	Arrays.sort(sumR);
    	Arrays.sort(sumC);
    	
    	for(int i = 0; i < sumR.length;i++) {
    		if(sumR[i] != sumC[i]) {
    			return "Impossible";
    		}
    	}
    	
    	return "Possible";
    	
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] container = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] containerRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < n; j++) {
                    int containerItem = Integer.parseInt(containerRowItems[j]);
                    container[i][j] = containerItem;
                }
            }

            String result = organizingContainers(container);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
