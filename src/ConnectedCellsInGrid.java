import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ConnectedCellsInGrid {

    // Complete the connectedCell function below.
    static int connectedCell(int[][] matrix) {
    	
    	int max = 0;
    	int maxRows = matrix.length;
    	int maxCols = matrix[0].length;
    	
    	for(int i = 0;i < maxRows;i++) {
    		for(int j = 0;j < maxCols;j++) {
    			if(matrix[i][j] == 1) {
    				int count = getCount(matrix, i, j, maxRows, maxCols) + 1;
        	    	if(count > max)
        	    		max = count;
    			}
    	    	
        	}
    	}
    	
    	return max;
    }

    private static int getCount(int[][] matrix, int row, int col, int maxRows, int maxCols) {
		
    	int total = 0;
    	matrix[row][col] = 0;
    	
    	// left
		if(col - 1 >= 0 && matrix[row][col - 1] == 1) {
			total+= (getCount(matrix, row, col - 1, maxRows, maxCols) + 1);
		}
    	
    	// left top
		if(row - 1 >= 0 && col - 1 > 0 && matrix[row - 1][col - 1] == 1) {
			total+= (getCount(matrix, row - 1, col - 1, maxRows, maxCols) + 1);
		}
		
    	// top
		if(row - 1 >= 0 && matrix[row - 1][col] == 1) {
			total+= (getCount(matrix, row - 1, col, maxRows, maxCols) + 1);
		}
		
    	// top right
		if(row - 1 >= 0 && col + 1 < maxCols && matrix[row - 1][col + 1] == 1) {
			total+= (getCount(matrix, row - 1, col + 1, maxRows, maxCols) + 1);
		}
		
    	// right
		if(col + 1 < maxCols && matrix[row][col + 1] == 1) {
			total+= (getCount(matrix, row, col + 1, maxRows, maxCols) + 1);
		}
		
    	// right bottom
		if(row + 1 < maxRows && col + 1 < maxCols && matrix[row + 1][col + 1] == 1) {
			total+= (getCount(matrix, row + 1, col + 1, maxRows, maxCols) + 1);
		}
		
    	// bottom
		if(row + 1 < maxRows && matrix[row + 1][col] == 1) {
			total+= (getCount(matrix, row + 1, col, maxRows, maxCols) + 1);
		}
		
    	// bottom left
		if(row + 1 < maxRows && col - 1 >= 0 &&matrix[row + 1][col - 1] == 1) {
			total+= (getCount(matrix, row + 1, col - 1, maxRows, maxCols) + 1);
		}
    	
		return total;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
