import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CommonChild {

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {

    	int[][] cache =  new int[s1.length()+1][s2.length()+1];
    	for(int i =0 ; i < s1.length();i++) {
    		for(int j =0 ; j < s2.length();j++) {
    			if(s1.charAt(i) == s2.charAt(j)) {
    				return count(0 , 0, s1, s2, cache);
    			}
        	}	
    	}
		
    	return 0;
    }

    private static int count(int i, int j, String s1, String s2, int[][] cache) {
    	
    	if (i >= s1.length() || j >= s2.length())
			return 0;

		if(cache[i][j] > 0) {
			return cache[i][j];
		}
		
		int m3 = count(i + 1, j + 1, s1, s2, cache) + (s1.charAt(i) == s2.charAt(j) ? 1 : 0);
		int m2 = count(i + 1, j, s1, s2, cache);
		int m1 = count(i, j + 1, s1, s2, cache);
		int[] ip = new int[] {m1,m2,m3};
		Arrays.sort(ip);
		cache[i][j] = ip[2];
		
		return cache[i][j];
    	
    }
    
	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
