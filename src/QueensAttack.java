import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class QueensAttack {

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {

    	// n could be huge upto 1 lac so cannot create 2d array
    	Map<Integer, Set<Integer>> _obs = convertToMap(obstacles);
    	
    	int counter = 0;

    	// move left top
    	int row = r_q;
    	int col = c_q;
    	while(--row > 0 && --col > 0 && !isBlocked(row, col, _obs)) {
    		counter++;
    	}
    	
    	// move top
    	row = r_q;
    	col = c_q;
    	while(--row > 0 && !isBlocked(row, col, _obs)) {
    		counter++;
    	}
    	
    	// move right top
    	row = r_q;
    	col = c_q;
    	while(--row > 0 && ++col <= n && !isBlocked(row, col, _obs)) {
    		counter++;
    	}
    	
    	// move right
    	row = r_q;
    	col = c_q;
    	while(++col <= n && !isBlocked(row, col, _obs)) {
    		counter++;
    	}
    	
    	// move right bottom
    	row = r_q;
    	col = c_q;
    	while(++row <= n && ++col <= n && !isBlocked(row, col, _obs)) {
    		counter++;
    	}
    	
    	// move bottom
    	row = r_q;
    	col = c_q;
    	while(++row <= n && !isBlocked(row, col, _obs)) {
    		counter++;
    	}
    	
    	// move left bottom
    	row = r_q;
    	col = c_q;
    	while(++row <= n && --col > 0 && !isBlocked(row, col, _obs)) {
    		counter++;
    	}
    	
    	// move left
    	row = r_q;
    	col = c_q;
    	while(--col > 0 && !isBlocked(row, col, _obs)) {
    		counter++;
    	}
    	

    	return counter;
    }

    private static boolean isBlocked(int row, int col, Map<Integer, Set<Integer>> _obs) {
		
    	if(_obs.containsKey(row)) {
    		return ((Set<Integer>)_obs.get(row)).contains(col);
    	}
		return false;
	}

	private static Map<Integer, Set<Integer>> convertToMap(int[][] obstacles) {
		
    	Map<Integer, Set<Integer>> _obs = new HashMap<>();
    	
    	for (int[] obs : obstacles) {
    		int key = obs[0]; // row
    		int value = obs[1]; //column
    		if(_obs.containsKey(key)) {
    			Set<Integer> set = _obs.get(key);
    			set.add(value);
    		} else {
    			Set<Integer> set = new HashSet<>();
    			set.add(value);
    			_obs.put(key, set);
    		}
		}
    	
		return _obs;
	}

    private static Set<String> _convertToMap(int[][] obstacles) {
		
    	Set<String> obs = new HashSet<>();
    	
    	for (int[] _obs : obstacles) {
    		obs.add(new StringBuilder().append(_obs[0]).append("-").append(_obs[1]).toString());
		}
    	
		return obs;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
