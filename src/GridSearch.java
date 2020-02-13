import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class GridSearch {

    // Complete the gridSearch function below.
    static String gridSearch(String[] G, String[] P) {
    	
    	String[][] _G = convertTo2D(G);
    	String[][] _P = convertTo2D(P);

    	int gr = _G.length;
    	int gc = _G[0].length;
    	
    	int pr = _P.length;
    	int pc = _P[0].length;
    	
    	for(int i = 0 ; i < gr; i++) {
    		for(int j = 0 ; j < gc; j++) {
    			if(_G[i][j].equals(_P[0][0])
    				    && (i + pr <= gr) 
    				    && (j + pc <= gc)
    					&& isSame(_P, _G, i , j, pr, pc)) {
    				return "YES";
    			}
        	}
		}
    	
    	return "NO";
    }
    
    private static boolean isSame(String[][] _P, String[][] _G, int _i, int _j, int pr, int pc) {

    	for(int i = _i, k = 0; k < pr; i++, k++) {
    		for(int j = _j, l = 0; l < pc; j++, l++) {
    			if(!_G[i][j].equals(_P[k][l])) {
    				return false;
    			}
        	}
		}

		return true;
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
            String[] RC = scanner.nextLine().split(" ");

            int R = Integer.parseInt(RC[0]);

            int C = Integer.parseInt(RC[1]);

            String[] G = new String[R];

            for (int i = 0; i < R; i++) {
                String GItem = scanner.nextLine();
                G[i] = GItem;
            }

            String[] rc = scanner.nextLine().split(" ");

            int r = Integer.parseInt(rc[0]);

            int c = Integer.parseInt(rc[1]);

            String[] P = new String[r];

            for (int i = 0; i < r; i++) {
                String PItem = scanner.nextLine();
                P[i] = PItem;
            }

            String result = gridSearch(G, P);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
