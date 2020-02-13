import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlokAnagram {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {

    	int total = 0;
    	for(int i = 1 ;i < s.length();i++) { // sub string size
    		
    		Map<String, Integer> map = new HashMap<>();
    		for(int j = 0 ;j <= s.length() - i;j++) { // all sub strings
    			char[] sub = s.substring(j, j + i).toCharArray();
    	        Arrays.sort(sub);
    	        String _sub = new String(sub);
    	        if(map.containsKey(_sub)) {
    	        	int value = map.get(_sub);
    	        	map.put(_sub, value + 1);
    	        } else {
    	        	map.put(_sub, 1);
    	        }
        	}
    		
    		Set<Entry<String, Integer>> entries = map.entrySet();
    		for(Entry<String, Integer> entry :entries) {
    			int n = entry.getValue();
    			total+=(n*(n-1)/2);
    		}
    	}

    	return total;
    }

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
