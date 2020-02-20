import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FullCountingSort {

    // Complete the countSort function below.
    static void countSort(List<List<String>> arr) {

    	long start = System.currentTimeMillis();
    	
    	StringBuilder[] result = new StringBuilder[101];
    	
    	for(int i = 0; i < arr.size()/2;i++) {
    		List<String> _str = arr.get(i);
    		int key = Integer.parseInt(_str.get(0));
    		
    		if(result[key] == null) {
    			StringBuilder sb =  new StringBuilder();
    			sb = sb.append("-");
    			result[key] = sb;
    		} else {
    			result[key] = result[key].append(" ").append("-");
    		}
    		
    	}
    	
    	for(int i = arr.size()/2; i < arr.size();i++) {
    		List<String> _str = arr.get(i);
    		int key = Integer.parseInt(_str.get(0));
    		String value = _str.get(1);
    		
    		if(result[key] == null) {
    			StringBuilder sb =  new StringBuilder();
    			sb = sb.append(value);
    			result[key] = sb;
    		} else {
    			result[key] = result[key].append(" ").append(value);
    		}
    		
    	}
    	
    	StringBuilder r = new StringBuilder();
    	
    	for(int i = 0 ;i < result.length;i++) {
    		if(result[i] != null) {
    			r = r.append(result[i]).append(" ");	
    		}
    	}
    	
    	System.out.println(r.toString().trim());
    	System.out.println(System.currentTimeMillis() - start);
    }
    
    private static String getHyphens(int length) {
    	
    	StringBuilder str = new StringBuilder();
    	
    	while(length-- > 0) {
    		str = str.append("-");
    	}
    	
    	return str.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        countSort(arr);

        bufferedReader.close();
    }
}
