import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Encryption {

    // Complete the encryption function below.
    static String encryption(String s) {

    	s = s.replaceAll(" ", "");
    	double d = Math.sqrt(s.length());
    	
    	int column = (int)Math.ceil(d);
    	int row = (int)Math.floor(d);
    	
    	if((row * column) < s.length()) {
    		row++;
    	}
    	
    	String[][] grid = new String[row][column];
    	
    	int k = 0;
    	for(int i = 0; i < row;i++) {
    		for(int j = 0 ; j < column;j++) {
    			if(k < s.length())
    				grid[i][j] = s.charAt(k++)+"";
        	}
    	}

    	StringBuilder result = new StringBuilder();
    	for(int i = 0; i < column;i++) {
    		for(int j = 0 ; j < row;j++) {
    			if(grid[j][i] != null)
    				result.append(grid[j][i]);
        	}
    		if(i + 1 < column) {
    			result.append(" ");
    		}
    	}
    	return result.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
