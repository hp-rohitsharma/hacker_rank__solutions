import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class EvenTree {

    // Complete the evenForest function below.
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {

    	boolean[][] grid = new boolean[t_nodes + 1][t_nodes + 1];
    	
    	for(int i = 0; i < t_from.size(); i++) {
        	grid[t_from.get(i)][t_to.get(i)] = true;
        	grid[t_to.get(i)][t_from.get(i)] = true;
    	}
    	
    	Set<Integer> nodes = new HashSet<>();
    	for(int i = 0; i < t_from.size(); i++) {
        	nodes.add(t_from.get(i));
    	}
    	for(int i = 0; i < t_to.size(); i++) {
    		nodes.add(t_to.get(i));
    	}
    	
    	List<Integer> _nodes = new ArrayList<>(nodes);
    	
    	int counter = 0;
    	for(int j = 0; j < t_from.size(); j++) {
    		grid[t_from.get(j)][t_to.get(j)] = false;
    		grid[t_to.get(j)][t_from.get(j)] = false;
    		if(valid(grid, _nodes)) {
    			counter++;
    		} else {
    			// reconnect nodes as in is invalid
    			grid[t_from.get(j)][t_to.get(j)] = true;
    			grid[t_to.get(j)][t_from.get(j)] = true;
    		}
    	}
    
    	return counter;
    }

    private static boolean valid(boolean[][] grid, List<Integer> nodes) {

    	Set<Integer> processed = new HashSet<>();
    	
    	for(int j = 0; j < nodes.size(); j++) {
    		int node = nodes.get(j);
    		if(!processed.contains(node)){
    			Set<Integer> connected = new HashSet<>();
    			countNodes(grid, node, connected);
    			if(connected.size() % 2 != 0) {
    				return false;
    			}
    			processed.addAll(connected);
    		}
    	}
    	
    	return true;
	}

	private static int countNodes(boolean[][] grid, int j, Set<Integer> connected) {
		
		connected.add(j);
		
		Set<Integer> siblings = getConnectedNodes(grid, j);
		
		int[] count = new int[1];
		siblings.forEach((node)-> {
			if(!connected.contains(node)) {
				count[0]+=(countNodes(grid, node, connected) + 1);
			}
		});
		
		return siblings.size() + count[0] + 1;
	}

	private static Set<Integer> getConnectedNodes(boolean[][] grid, int j) {
		
		Set<Integer> connected = new HashSet<>();
		
		// row
		for(int i = 0 ;i < grid.length;i++) {
			if(grid[j][i] == true) {
				connected.add(i);
			}
		}
		
		// col
		for(int i = 0 ;i < grid[j].length;i++) {
			if(grid[i][j] == true) {
				connected.add(i);
			}
		}
		
		return connected;
	}

	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int tNodes = Integer.parseInt(tNodesEdges[0]);
        int tEdges = Integer.parseInt(tNodesEdges[1]);

        List<Integer> tFrom = new ArrayList<>();
        List<Integer> tTo = new ArrayList<>();

        IntStream.range(0, tEdges).forEach(i -> {
            try {
                String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                tFrom.add(Integer.parseInt(tFromTo[0]));
                tTo.add(Integer.parseInt(tFromTo[1]));
            } catch (IOException ex) {
            	try {
					bufferedReader.close();
					bufferedWriter.close();
				} catch (IOException e) {
				}
                throw new RuntimeException(ex);
            }
        });

        int res = evenForest(tNodes, tEdges, tFrom, tTo);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
