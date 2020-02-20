import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class HighestValuePalindrome {

    // Complete the highestValuePalindrome function below.
    static String highestValuePalindrome(String s, int n, int k) {

    	if(!isPossible(s, k)){
    		return "-1";
    	}
    	
    	int[] result =  new int[s.length()];
    	String res = dp(s,0, s.length() -1, k, result); 
    	
    	if(res.equals("-1")){
    		return "-1";
    	} else {
    		return convertToString(result);
    	}
    	
    }
    
    private static String convertToString(int[] result) {
		
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ;i < result.length;i++){
    		sb.append(result[i]);
    	}
    	
		return sb.toString();
	}

	private static boolean isPossible(String s, int k) {
		
    	int totalMismatch = 0;
    	int start = 0;
    	int end = s.length() -1;
    	while(start < end) {
    		if(s.charAt(start++) != s.charAt(end--))
    			totalMismatch++;
    	}
    	
    	return totalMismatch <= k;
	}
    
    private static String dp(String s, int start, int end, int k, int[] result) {
    	
    	if(result[start] > 0) {
    		return "0";	
    	}
    	
    	if(k < 0)
    		return "-1";
    	
    	if(start > end) {
    		return "0";
    	}
    	
    	if(start == end && k > 0) {
    		result[start] = 9;
    		return "0";
    	}
    	
    	// case when both filled with nine
    	String result1 = "-1";
    	if(s.charAt(start) != '9' && s.charAt(end) != '9') {
    		result1 = dp(s, start + 1, end - 1, k - 2, result);
    	}
    	
    	if(result1.equals("-1")) {
    		// case when both are same
    		if(s.charAt(start) == s.charAt(end)) {
    			String result2 = dp(s, start + 1, end - 1, k, result);
        		
        		if(result2.equals("-1")) {
        			return "-1";
        		} else {
        			result[start] = s.charAt(start)-'0';
            		result[end] = s.charAt(start)-'0';
        		}
    		} else {
    			// case when replaced with larger number
    			String result2 = dp(s, start + 1, end - 1, k - 1, result);
        		
        		if(result2.equals("-1")) {
        			return "-1";
        		} else {
        			int big = 0;
        			if(s.charAt(start) > s.charAt(end)) {
        				big = s.charAt(start) -'0';
        			} else {
        				big = s.charAt(end)-'0';
        			}
        			result[start] = big;
            		result[end] = big;
        		}
    		}
    	}
    	else {
    		result[start] = 9;
    		result[end] = 9;
    	}
    	return "0";
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scanner.nextLine();

        String result = highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
