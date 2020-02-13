import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class DosreyThief {

	private static long MAX = 0;
	private static long MIN = Long.MAX_VALUE;
	private static long counter = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String[] nAndx = bufferedReader.readLine().trim().split(" ");
		
		int N = Integer.parseInt(nAndx[0]);
		int X = Integer.parseInt(nAndx[1]);
		
		int[] g = new int[N];
		int[] v = new int[N];
		
		Map<Integer, List<Integer>> map =  new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			String[] vAndg = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

			int V = Integer.parseInt(vAndg[0]); // value
			int G = Integer.parseInt(vAndg[1]); // gold
			
			//v[i] = V;
			//g[i] = G;
			
			if(map.containsKey(V)) {
				List<Integer> sizes = map.get(V);
				sizes.add(G);
				map.put(V, sizes); 
			} else {
				List<Integer> sizes = new ArrayList<>();
				sizes.add(G);
				map.put(V, sizes); 				
			}
			
		}
		
		ArrayList<Integer> sortedKeys = new ArrayList<Integer>(map.keySet()); 
		Collections.sort(sortedKeys);  
		
		int j = 0;
		for(int k = sortedKeys.size() - 1 ; k >=0; k--) {
			int value = sortedKeys.get(k);
			List<Integer> sizes = map.get(value);
			for (Integer size: sizes) {
				g[j] = size;
				v[j] = value;
				MIN = MIN < size ? MIN : size;
				j++;
			}
		}
		
		
    
		// sort by value
		//TreeMap<Integer, List<Integer>> sorted = new TreeMap<>(map); 
		 
		//int[] g = new int[map.size()];
		//int[] v = new int[map.size()];
		
		/*int j = 0 ;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			g[j] = entry.getKey();
			v[j] = entry.getValue();
			j++;
		}*/
		
		long[][] cache = new long[N+1][X+1];
		try {
			dp(X, 0, g, v, 0, cache);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(counter);
		if(MAX > 0)
			System.out.println(MAX);
		else
			System.out.println("Got caught!");

	}

	private static void dp(int X, int i, int[] g, int[] v, int max, long[][] cache) {

		/**
		 * ignore conditions
		 * when X = 0
		 * when x < 0
		 * when g,v length reached
		 * when cache have data
		 * if X is smaller than min piece size
		 */
		
		//System.out.println(X+" "+i+" "+max);		
		counter++;
		
		if(X == 0) {
			MAX = MAX > max ? MAX : max;
			cache[i][X] = MAX;
			//return max;
			//throw new RuntimeException();
		}			
		
		if (MIN > X || i == g.length || X < 0) {
			return ;		
		}

		// already processed
		if(cache[i][X] > 0) {
			return;
		}
		
		// sold
		dp(X - g[i], i + 1, g, v, max + v[i], cache);

		// didn't sold
		dp(X, i + 1, g, v, max, cache);
		
		//return Math.max(p1, p2);
	}

}
