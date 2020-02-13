import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class AbsolutePermutation {

    // Complete the absolutePermutation function below.
	static int[] absolutePermutation(int n, int k) {
		int[] result = new int[n];
		Set<Integer> used = new HashSet<>();
		for (int i = 0; i < n; i++) {
			int v = getValue(i + 1, k, n, used);
			used.add(v);
			if (v < 0) {
				return new int[] { -1 };
			}
			result[i] = v;
		}
		return result;
	}

	private static int getValue(int i, int k, int n, Set<Integer> used ) {

		int v1 = i - k;
		int v2 = i + k;
		
		if(v1 < 1 && v2 > n) {
			return -1;	
		}
		
		int min = Math.min(v1, v2);
		if(used.contains(min) || min < 1) {
			int max = Math.max(v1, v2);
			if(used.contains(max) || max > n) {
				return -1;
			} else {
				return max;
			}
		} else {
			return min;
		}
		
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] result = absolutePermutation(n, k);

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
