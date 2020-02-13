import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BombermanGame {

    // Complete the bomberMan function below.
    static String[] bomberMan(int n, String[] grid) {

    	if(n == 1) {
    		return grid;
    	}
    	
    	if(n == 2) {
    		return mergeColumnsOfEachRow(getAllBombs(grid.length, grid[0].length()));
    	}
    	
    	if(n == 3 || (n - 2) % 4 == 0 || (n - 3) % 4 == 0) {
    		return handle(grid, getAllBombs(grid.length, grid[0].length()));
    	}
    	
    	if(n == 4 || n % 4 == 0 || (n - 1) % 4 == 0)  {
    		return handle(handle(grid, getAllBombs(grid.length, grid[0].length())), getAllBombs(grid.length, grid[0].length()));
    	}
    	
    	return grid;
    	
    }

    private static String[] handle(String[] grid, String[][] allBombs) {
		String[][] _grid = convertTo2D(grid);
    	for(int i = 0 ; i < _grid.length; i++) {
    		for(int j = 0 ; j < _grid[0].length; j++) {
    			if(_grid[i][j].equals("O")) {
    				allBombs[i][j] = ".";
    				if(i > 0)
    					allBombs[i-1][j] = ".";
    				if(i < _grid.length - 1)
    					allBombs[i+1][j] = ".";
    				if(j > 0)
    					allBombs[i][j-1] = ".";
    				if(j < _grid[0].length - 1)
    					allBombs[i][j+1] = ".";
    			}
        	}	
    	}
    	
    	return mergeColumnsOfEachRow(allBombs);
	}

	private static String[] mergeColumnsOfEachRow(String[][] allBombs) {
		
		String[] result = new String[allBombs.length];
		
		for(int i = 0 ; i < allBombs.length;i++) {
			StringBuilder b =  new StringBuilder();
    		for(int j = 0 ; j < allBombs[0].length;j++) {
    			b.append(allBombs[i][j]);
        	}	
    		result[i] = b.toString();
    	}
		
		return result;
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

	private static String[][] getAllBombs(int row, int columns) {
		
		String[][] all = new String[row][columns];
		
		for(int i = 0 ; i < row;i++) {
    		for(int j = 0 ; j < columns;j++) {
        		all[i][j] = "O";
        	}
		}
		
		return all;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] rcn = scanner.nextLine().split(" ");

        int r = Integer.parseInt(rcn[0]);

        int c = Integer.parseInt(rcn[1]);

        int n = Integer.parseInt(rcn[2]);

        String[] grid = new String[r];

        for (int i = 0; i < r; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = bomberMan(n, grid);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
