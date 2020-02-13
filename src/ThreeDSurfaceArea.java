import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ThreeDSurfaceArea {

    // Complete the surfaceArea function below.
    static int surfaceArea(int[][] A) {

    	int[][] aux = new int[A.length][A[0].length];

    	for(int i= 0;i< A.length;i++) {
    		for(int j= 0;j< A[0].length;j++) {
        		
    			int area = 0;
    			if(i == 0) {
    				area+=A[i][j];
    			} else {
    				if(A[i-1][j]< A[i][j]) {
    					area+=(A[i][j] - A[i-1][j]);
    				}
    			}
    			
    			if(j == 0) {
    				area+=A[i][j];
    			} else {
    				if(A[i][j-1]< A[i][j]) {
    					area+=(A[i][j] - A[i][j-1]);
    				}
    			}
    			
    			if(i == A.length - 1) {
    				area+=A[i][j];
    			} else {
    				if(A[i + 1][j]< A[i][j]) {
    					area+=(A[i][j] - A[i+1][j]);
    				}
    			}
    			
    			if(j == A[0].length - 1) {
    				area+=A[i][j];
    			} else {
    				if(A[i][j+1]< A[i][j]) {
    					area+=(A[i][j] - A[i][j + 1]);
    				}
    			}
    		
    			aux[i][j] = area; 
        	}
    	}
    	
    	int sa = 2*aux.length*aux[0].length;
    	
    	for(int i= 0;i< aux.length;i++) {
    		for(int j= 0;j< aux[0].length;j++) {
    			sa+=aux[i][j];
    		}
    	}
    	
    	return sa;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] HW = scanner.nextLine().split(" ");

        int H = Integer.parseInt(HW[0]);

        int W = Integer.parseInt(HW[1]);

        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            String[] ARowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < W; j++) {
                int AItem = Integer.parseInt(ARowItems[j]);
                A[i][j] = AItem;
            }
        }

        int result = surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
