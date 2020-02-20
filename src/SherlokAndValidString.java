import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlokAndValidString {

    // Complete the isValid function below.
    static String isValid(String s) {

    	int[] chars = new int[26];
    	
    	for(int i = 0; i < s.length(); i++){
    		char c = s.charAt(i);
    		chars[c-'a']++;
    	}
    	
    	int[] copy1 = chars.clone();
    	int[] copy2 = chars.clone();
    	if(isSameCount(chars) || (subtractOneFromMax(copy1) && isSameCount(copy1) || (removeOneChar(copy2) && isSameCount(copy2)))) {
    		return "YES";
    	} else {
    		return "NO";
    	}

    }

    private static boolean removeOneChar(int[] chars) {
		
    	for(int i = 1; i < chars.length; i++){
    		if(chars[i] == 0){
    			continue;
    		}
    		if(chars[i] == 1){
    			chars[i]--;
        		return true;
    		}
    	}
    	
		return false;
	}

	private static boolean isSameCount(int[] chars) {
		
    	int prevCount = chars[0];
    	for(int i = 1; i < chars.length; i++){
    		if(chars[i] == 0){
    			continue;
    		}
    		if(prevCount == chars[i])
    			prevCount = chars[i];
    		else 
    			return false;
    	}
		return true;
	}

	private static boolean subtractOneFromMax(int[] chars) {
		
		int maxIndex = 0;
		int maxValue = chars[0];
		
		for(int i = 1; i < chars.length; i++) {
			if(chars[i] == 0){
    			continue;
    		}
    		if(chars[i] > maxValue) {
    			maxValue = chars[i];
    			maxIndex = i;
    		}
    	}
		
		chars[maxIndex]--;
		return true;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
