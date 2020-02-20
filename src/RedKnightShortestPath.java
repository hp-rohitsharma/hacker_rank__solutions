import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RedKnightShortestPath {

    // Complete the printShortestPath function below.
    static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        // Print the distance along with the sequence of moves.
    	
    	int[][] grid = new int[n][n];
    	boolean[][] visited = new boolean[n][n];
    	List<String> path = new ArrayList<>();
    	
    	int count = count(grid, n, i_start, j_start, i_end, j_end, path, visited);
    	if(count == Integer.MAX_VALUE) {
    		System.out.println("Impossible");
    	} else {
    		System.out.println(count);
    		visited = new boolean[n][n];
    		StringBuilder sb =  new StringBuilder();
    		path(grid, count, n, i_start,j_start,i_end,j_end, visited, sb);
    		
    		System.out.println(sb.toString().trim());
    	}
    	
    }

    private static int count(int[][] grid, int n, int i, int j, int i_end, int j_end, List<String> path, boolean[][] visited) {
    	
    	if(grid[i][j] > 0) {
    		return grid[i][j];	
    	}
    	
    	if(visited[i][j] == true) {
    		return Integer.MAX_VALUE;
    	}
    	
    	if(i == i_end && j == j_end) {
    		return 0;
    	}
    	
    	visited[i][j] = true;
    			
    	// UL, UR, R, LR, LL, L
    	int ul = Integer.MAX_VALUE;
    	int ur = Integer.MAX_VALUE;
    	int r = Integer.MAX_VALUE;
    	int	lr = Integer.MAX_VALUE;
    	int ll = Integer.MAX_VALUE;
    	int l = Integer.MAX_VALUE;
    	
    	// UL
    	if(i - 2 >= 0 && j - 1 >= 0) {
    		ul = count(grid, n, i - 2, j - 1, i_end, j_end, path, visited);
    	}
    	
    	// UR
    	if(i - 2 >= 0 && j + 1 < n) {
    		ur = count(grid, n, i - 2, j + 1, i_end, j_end, path, visited);
    	}
    	
    	// R
    	if(j + 2 < n) {
    		r = count(grid, n, i, j + 2, i_end, j_end, path, visited);
    	}
    	
    	// LR
    	if(i + 2 < n && j + 1 < n) {
    		lr = count(grid, n, i + 2, j + 1, i_end, j_end, path, visited);
    	}
    	
    	// LL
    	if(i + 2 < n && j - 1 >= 0) {
    		ll = count(grid, n, i + 2, j - 1, i_end, j_end, path, visited);
    	}
    	
    	// L
    	if(j - 2 >= 0) {
    		l = count(grid, n, i, j - 2, i_end, j_end, path, visited);
    	}
    	
    	int[] steps = {ul, ur, r, lr, ll, l};
    	Arrays.sort(steps);
    	int min = steps[0];
    	
    	if(min != Integer.MAX_VALUE) {
    		String _path = selectPath(min, ul, ur, r, lr, ll, l);
    		if(_path != null)
    			path.add(_path);
    		min = min + 1;
    	}
    	
    	grid[i][j] = min;
    	
		return min;
	}

    private static void path(int[][] grid, int count, int n, int i, int j, int i_end, int j_end, boolean[][] visited, StringBuilder sb) {

    	if(i == i_end && j== j_end) {
    		return;	
    	}
    	
    	visited[i][j] = true;
    			
    	// UL, UR, R, LR, LL, L
    	
    	// UL
    	if(i - 2 >= 0 && j - 1 >= 0 && grid[i-2][j-1] == count - 1) {
    		sb = sb.append("UL").append(" ");
    		path(grid, count - 1, n, i - 2, j - 1, i_end, j_end, visited, sb);
    	} 
    	// UR
    	else if(i - 2 >= 0 && j + 1 < n && grid[i-2][j+1] == count - 1) { 
    		sb = sb.append("UR").append(" ");
    		path(grid, count - 1, n, i - 2, j + 1, i_end, j_end, visited, sb);
    	} 
    	// R
    	else if(j + 2 < n && grid[i][j+2] == count - 1) { 
    		sb = sb.append("R").append(" ");
    		path(grid, count - 1, n, i, j + 2, i_end, j_end, visited, sb);
    	} 
    	//LR
    	else if(i + 2 < n && j + 1 < n && grid[i+2][j+1] == count - 1) { // LR
    		sb = sb.append("LR").append(" ");
    		path(grid, count - 1, n, i + 2, j + 1, i_end, j_end, visited, sb);
    	} 
    	//LL
    	else if(i + 2 < n && j - 1 >= 0 && grid[i+2][j-1] == count - 1) { // LL
    		sb = sb.append("LL").append(" ");
    		path(grid, count - 1, n, i + 2, j - 1, i_end, j_end, visited, sb);
    	} 
    	//L
    	else if(j - 2 >= 0 && grid[i][j-2] == count - 1) { // L
    		sb = sb.append("L").append(" ");
    		path(grid, count - 1, n, i, j - 2, i_end, j_end, visited, sb);
    	}
    	
	}

	private static String selectPath(int min, int ul, int ur, int r, int lr, int ll, int l) {
		
		if(ul == min) {
			return "UL";
		}
		
		if(ur == min) {
			return "UR";
		}
		
		if(r == min) {
			return "R";
		}
		
		if(lr == min) {
			return "LR";
		}
		
		if(ll == min) {
			return "LL";
		}
		
		if(l == min) {
			return "L";
		}
		
		return null;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] i_startJ_start = scanner.nextLine().split(" ");

        int i_start = Integer.parseInt(i_startJ_start[0]);

        int j_start = Integer.parseInt(i_startJ_start[1]);

        int i_end = Integer.parseInt(i_startJ_start[2]);

        int j_end = Integer.parseInt(i_startJ_start[3]);

        printShortestPath(n, i_start, j_start, i_end, j_end);

        scanner.close();
    }
}
