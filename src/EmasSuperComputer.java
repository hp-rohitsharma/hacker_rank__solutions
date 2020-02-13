import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class EmasSuperComputer {

    // Complete the twoPluses function below.
    static int twoPluses(String[] grid) {

    	String[][] _grid = convertTo2D(grid);
    	int[][] count = new int[_grid.length][_grid[0].length];
    	int r = _grid.length;
    	int c = _grid[0].length;
    	
    	int max = 0;
    	for(int i = 0; i < r;i++) {
    		for(int j = 0; j < c;j++) {
        		if(_grid[i][j].equals("G")) {
        			// left, top, right, bottom
        			int[][] cordinates = new int[][] {{i,j},{i,j},{i,j},{i,j}};
        			int m1 = getMaxPlus(_grid, cordinates, r, c, 1, count, true);
        			//System.out.println(i+" "+j+" "+m1);
        			for(int k = i; k < r;k++) {
        	    		for(int l = j + 1; l < c;l++) {
        	        		if(_grid[k][l].equals("G")) {
        	        			// left, top, right, bottom
        	        			int[][] _cordinates = new int[][] {{k,l},{k,l},{k,l},{k,l}};
        	        			int m2 = getMaxPlus(_grid, _cordinates, r, c, 1, count, false);
        	        			int prod = (m1*4 - 3) * (m2*4 - 3);
        	        			//System.out.println(k+" - "+l+" "+m2);
        	        			max = prod > max ? prod : max;
        	        		}
        	        	}	
        	    	}
        			clear(count);
        		}
        	}	
    	}
    	
    	return max;
    	
    }

    private static void clear(int[][] count) {
    	for(int k = 0; k < count.length; k++) {
    		for(int l = 0; l < count[0].length; l++) {
    			count[k][l] = 0;
    		}
    	}
	}

	private static int getMaxPlus(String[][] _grid, int[][] cordinates, int r, int c, int max, int[][] count, boolean update) {
		
    	// left
    	int left[] = {cordinates[0][0], cordinates[0][1] - 1};
    	if(!(left[1] >= 0 && _grid[left[0]][left[1]].equals("G") && count[left[0]][left[1]] == 0)) {
    		return max;
    	}
    	
    	// top
    	int top[] = {cordinates[1][0] - 1, cordinates[1][1]};
    	if(!(top[0] >= 0 && _grid[top[0]][top[1]].equals("G") && count[top[0]][top[1]] == 0)) {
    		return max;
    	}
    	
    	// right
    	int right[] = {cordinates[2][0], cordinates[2][1] + 1};
    	if(!(right[1] < c && _grid[right[0]][right[1]].equals("G") && count[right[0]][right[1]] == 0)) {
    		return max;
    	}
    	
    	// bottom
    	int bottom[] = {cordinates[3][0] + 1, cordinates[3][1]};
    	if(!(bottom[0] < r && _grid[bottom[0]][bottom[1]].equals("G") && count[bottom[0]][bottom[1]] == 0)) {
    		return max;
    	}
    	
    	
    	max = getMaxPlus(_grid, new int[][] {{left[0], left[1]},{top[0],top[1]},{right[0],right[1]}, {bottom[0], bottom[1]}}, r, c, max + 1, count, update);
    	
    	if(update && max > count[left[0]][left[1]] && max > count[right[0]][right[1]] && max > count[top[0]][top[1]] && max > count[bottom[0]][bottom[1]]) {
    		
    		count[left[0]][left[1]] = max;
    		count[right[0]][right[1]] = max;
    		count[top[0]][top[1]] = max;
			count[bottom[0]][bottom[1]] = max;
    	}
    	
    	return max;
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

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        int result = twoPluses(grid);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
