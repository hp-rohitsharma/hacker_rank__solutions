import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LilysHomework {

    // Complete the lilysHomework function below.
    static int lilysHomework(int[] arr) {
    	
    	Map<Integer, Integer> map = new HashMap<>();
    	Map<Integer, Integer> map1 = new HashMap<>();
    	for(int i= 0; i < arr.length;i++) {
    		map.put(arr[i], i);
    		map1.put(arr[i], i);
    	}
    	
    	
    	int[] sorted = arr.clone();
    	int[] copy = arr.clone();
    	
    	Arrays.sort(sorted);
    	
    	int c1 = 0;
    	for(int i= 0; i < arr.length;i++) {
    		if(arr[i] != sorted[i]) {
    			c1++;
    			int swapWithIndex = map.get(sorted[i]);
    			arr[swapWithIndex] = arr[i];
    			map.put(arr[i], swapWithIndex);
    		}
    	}

    	// compare reverse
    	int c2 = 0;
    	for(int i = copy.length - 1,j = 0; i >=0;i--,j++) {
    		if(copy[j] != sorted[i]) {
    			c2++;
	    		int swapWithIndex = map1.get(sorted[i]);
	    		copy[swapWithIndex] = copy[j];
				map1.put(copy[j], swapWithIndex);
    		}
    	}
    	
    	return Math.min(c1, c2);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");


        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = lilysHomework(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
