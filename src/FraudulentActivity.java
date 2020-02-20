import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentActivity {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {

    	int freq[] = new int[201];

    	int j = 0;
    	while(j < d) {
    		freq[expenditure[j++]]++;
    	}
    	
    	int count = 0;
    	boolean isEven = (d % 2 == 0); 
    	for(int i = d;i < expenditure.length;i++) {
    		
    		int toRemove = expenditure[i - d];
    		int toAdd = expenditure[i];
    		
    		double median = median(freq, d, isEven);
    		
    		if(isEven) {
    			// no need to multiply by 2 as it is not divided by 2
    			if(toAdd >= median)
    				count++;
    		}
    		else {
    			if(toAdd >= 2*median)
    				count++;
    		}
    		
    		freq[toRemove]--;
    		freq[toAdd]++;
    		
    	}
    	
    	return count;
    }

    private static double median(int[] freq, int d, boolean isEven) {
	
    	if(isEven) { // even
    		
    		int mid1 = (int) Math.floor((d + 1d) / 2d);
    		int mid2 = (int) Math.ceil((d + 1d) / 2d);
    		
    		int tot = 0;
    		int first = -1;
    		int second = -1;
    		
    		for(int i = 0 ;i < freq.length;i++) {
    			tot+=freq[i];
    			if(tot >= mid2) {
    				second = i;
    				if(first < 0)
    					first = i;
    				break;
    			}
    			if(tot >= mid1) {
    				first = i;
    			}
    		}
    		
    		return (first + second);
    		
    	} else { // odd
    		
    		int mid = (int) (d + 1) / 2;
    		int tot = 0;
    		for(int i = 0 ;i < freq.length;i++) {
    			tot+=freq[i];
    			if(tot >= mid) {
    				return i;
    			}
    		}
    	}
    	
    	return 0;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
