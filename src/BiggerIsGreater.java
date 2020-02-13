import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BiggerIsGreater {

    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {

    	char[] chars = w.toCharArray();
    	int size = chars.length;
    	if(chars.length <= 1) {
    		return "no answer";
    	} 
    	// when last char is bigger than second last then swap them
    	else if(chars[size - 2] < chars[size - 1]) {
    		// swap
    		char slc = chars[size - 2];
    		chars[size - 2] = chars[size -1];
    		chars[size - 1] = slc;
    	} 
    	// look for last wave 
    	else {
    		size--;
    		int index = -1;
    		
    		while(size > 1) {
    			if(chars[size - 2] < chars[size - 1]) {
    				index = size - 2;
    				break;
    			}
    			size--;
    		}
    		
    		if(index >= 0) {
    			int smallestIndex = getSmallest(chars, index + 1, chars[index]);
    			// swap
        		char slc = chars[smallestIndex];
        		chars[smallestIndex] = chars[index];
        		chars[index] = slc;
        		// sort in increasing order all chars after index to ensure minimum from them
        		sort(chars, index + 1);
    			return String.valueOf(chars);
    		} else {
    			return "no answer";
    		}
    	}

    	return String.valueOf(chars);
    }
    
    private static int getSmallest(char[] chars, int index, char compareWith) {
    	int _index = index;
    	
    	while(index < chars.length) {
    		if(chars[index] > compareWith) {
    			_index = index;
    		} else {
    			break;
    		}
    		index++;
    	}
    	
		return _index;
	}

	private static void sort(char[] chars, int i) {
		char sub[] = Arrays.copyOfRange(chars, i,  chars.length);
		Arrays.sort(sub);
		for(int j = 0; j < sub.length;j++,i++) {
			chars[i] = sub[j];
		}
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            String w = scanner.nextLine();

            String result = biggerIsGreater(w);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}