import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BearAndSteadyGenes {

    // Complete the steadyGene function below.
    static int steadyGene(String gene) {

    	Map<String, Integer> total = new HashMap<>();
    	total.put("A", 0);
    	total.put("G", 0);
    	total.put("T", 0);
    	total.put("C", 0);
    	
    	for(int i = 0 ; i < gene.length();i++) {
    		String c = gene.charAt(i)+"";
    		if(total.containsKey(c)) {
    			int count = total.get(c);
    			total.put(c, count+1);
    		}
    	}
    	
    	int size = gene.length()/4;
    	
    	Map<String, Integer> overFlowed = new HashMap<>();
    	overFlowed.put("A", 0);
    	overFlowed.put("G", 0);
    	overFlowed.put("T", 0);
    	overFlowed.put("C", 0);
    	
    	int aCount = total.get("A");
    	if(aCount - size > 0) {
    		overFlowed.put("A", aCount - size);
    	}
    	
    	int tCount = total.get("T");
    	if(tCount - size > 0) {
    		overFlowed.put("T", tCount - size);
    	}
    		
    	int cCount = total.get("C");
    	if(cCount - size > 0) {
    		overFlowed.put("C", cCount - size);
    	}
    		
    	int gCount = total.get("G");
    	if(gCount - size > 0) {
    		overFlowed.put("G", gCount - size);
    	}
    		
    	int start = 0;
    	int end = gene.length() - 1;
    	
    	while(start < end) {
    		String s = gene.charAt(start)+"";
    		if(overFlowed.get(s) > 0) {
    			int c = overFlowed.get(s);
    			overFlowed.put(s, c-1);
    		}
    		
    		String e = gene.charAt(end)+"";
    		if(overFlowed.get(e) > 0) {
    			int c = overFlowed.get(e);
    			overFlowed.put(e, c-1);
    		}
    		
    		if(isOverFlowMapEmpty(overFlowed)) {
    			break;
    		}
    		
    		start++;
    		end--;
    	}
    	
    	return end - start +1;
    }

    private static boolean isOverFlowMapEmpty(Map<String, Integer> overFlowed) {

    	Set<String> keys = overFlowed.keySet();
    	for (String key : keys) {
    		if(overFlowed.get(key) != 0) {
    			return false;
    		}
		}
		return true;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String gene = scanner.nextLine();

        int result = steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
