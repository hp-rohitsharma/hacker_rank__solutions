import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BFSShortestReach {

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {

    	int[] result = new int[n + 1];
    	
    	Map<Integer, List<Integer>> adjList = createAdjListMap(edges);
    	
    	List<Integer> nodes = new ArrayList<>();
    	nodes.add(s);
    	performBFS(nodes, 0, adjList, result);
    	
    	// remove s from result
    	int _result[] = new int[n - 1]; 
    	for(int i = 1, j = 0;i < result.length; i++,j++) {
    		if(i == s) {
    			j--;
    			continue;	
    		}

    		if(result[i] > 0) {
    			_result[j] = result[i]*6;
    		} else {
    			_result[j] = -1;
    		}
    	}
    	
    	return _result;
    }

    private static void performBFS(List<Integer> nodes, int level, Map<Integer, List<Integer>> adjList, int[] result) {
		
    	if(nodes.size() == 0) {
    		return;
    	}
    	
    	List<Integer> nln = new ArrayList<>();
    	for(int j = 0; j < nodes.size(); j++) {
    		
    		int node = nodes.get(j);
    		if(result[node] > 0) {
    			continue;
    		}
    		if(adjList.get(node) != null)
    			nln.addAll(adjList.get(node));
    		result[node] = level;
    	}
    	
    	performBFS(nln, level + 1, adjList, result);
		
	}

	private static Map<Integer, List<Integer>> createAdjListMap(int[][] edges) {
		
		Map<Integer, List<Integer>> adjList =  new HashMap<>();
		
		for(int i = 0 ; i < edges.length;i++){
			int[] edge = edges[i];
			add(edge[0],edge[1], adjList);
			add(edge[1],edge[0], adjList);
		}
		
		return adjList;
	}

	private static void add(int i, int j, Map<Integer, List<Integer>> adjList) {
		 if(adjList.containsKey(i)) {
			 List<Integer> adjNodes = adjList.get(i);
			 adjNodes.add(j);
		 } else {
			 List<Integer> adjNodes = new ArrayList<>();
			 adjNodes.add(j);
			 adjList.put(i, adjNodes);
		 }
		
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
