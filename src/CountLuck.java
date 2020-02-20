import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CountLuck {

    // Complete the countLuck function below.
    static String countLuck(String[] matrix, int k) {

    	String[][] _grid = convertTo2D(matrix);
    	int maxRows = _grid.length;
    	int maxCols = _grid[0].length;
    	boolean[][] processed = new boolean[maxRows][maxCols];
    	for(int i = 0; i < maxRows; i++) {
    		for(int j = 0; j < maxCols; j++) {
    			if(_grid[i][j].equals("M")) {
    				boolean msg = check(_grid, i, j, k, maxRows, maxCols, 0, processed);
        	    	if(msg == false) {
        	    		return "Oops!";
        	    	} else {
        	    		return "Impressed";
        	    	}
    			}
        	}
    	}
    	
    	return "Impressed";

    }

    private static boolean check(String[][] _grid, int row, int col, int maxWands, int maxRows, int maxCols, int wand, boolean[][] processed) {
    	
    	if(_grid[row][col].equals("*")) {
    		return maxWands == wand;
    	}
    	
    	processed[row][col] = true;
    	
    	int paths = 0;
    	boolean left = false;
    	boolean right = false;
    	boolean top = false;
    	boolean bottom = false;;
    	
    	// left
		if(col - 1 >= 0 && !_grid[row][col - 1].equals("X") && !processed[row][col - 1]) {
			left = true;
			paths++;
		}
		
    	// top
		if(row - 1 >= 0 && !_grid[row - 1][col].equals("X") && !processed[row - 1][col]) {
			top = true;
			paths++;
		}
		
    	// right
		if(col + 1 < maxCols && !_grid[row][col + 1].equals("X") && !processed[row][col + 1]) {
			right = true;
			paths++;
		}
		
    	// bottom
		if(row + 1 < maxRows && !_grid[row + 1][col].equals("X") && !processed[row + 1][col]) {
			bottom = true;
			paths++;
		}
		
		if(paths > 1) {
			wand++;	
		}

		if(left) {
    		left = check(_grid, row, col - 1, maxWands, maxRows, maxCols, wand, processed);
    	}
    	
    	if(top) {
    		top = check(_grid, row - 1, col, maxWands, maxRows, maxCols, wand, processed);
    	}
    	
    	if(right) {
    		right = check(_grid, row, col + 1, maxWands, maxRows, maxCols, wand, processed);
    	}
    	
    	if(bottom) {
    		bottom = check(_grid, row + 1, col, maxWands, maxRows, maxCols, wand, processed);
    	}
    	
    	return left || top || right || bottom;
	}

	private static String[][] convertTo2D(String[] grid) {
		String[][] _grid = new String[grid.length][grid[0].split("").length];
		
		for(int i = 0 ; i < grid.length;i++) {
			String[] _row = grid[i].split("");
    		for(int j = 0 ; j < _row.length;j++) {
        		_grid[i][j] = _row[j];
        	}
		}	
		
		return _grid;
	}
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            String[] matrix = new String[n];

            for (int i = 0; i < n; i++) {
                String matrixItem = scanner.nextLine();
                matrix[i] = matrixItem;
            }

            int k = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = countLuck(matrix, k);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
