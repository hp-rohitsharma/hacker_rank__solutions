
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MatrixLayerRotation {

	static void matrixRotation(List<List<Integer>> matrix, int steps) {

		int M = matrix.size();
		int N = matrix.get(0).size();

		int i = 0;
		int j = 0;
		int k = 0;
		int r = steps;

		while (i < M / 2 && j < N / 2) {
			int m = M - 2 * k;
			int n = N - 2 * k;
			//r = r % (2 * (m + n));
			
			if(m == 1 || n == 1) {
				r = 0;
			} else {
				r = steps % ((m + (n - 2)) * 2);
			}
			
			if(r > 0) {				
				int pos[] = newPosition(i, j, m - 1, n - 1, r);
				shiftLayer(i, j, pos[0], pos[1], m, n, r, matrix);
			}			
			k++;
			i++;
			j++;
		}
		
		print(matrix); 
	}

	private static void print(List<List<Integer>> matrix) {
		 for(List<Integer> row : matrix) {
			 for(Integer value : row) {
				 System.out.print(value);
				 System.out.print(" ");
			 }
			 System.out.println();
		 }
	}

	private static void shiftLayer(int i, int j, int iNext, int jNext, int m, int n, int r, List<List<Integer>> matrix) {

		int[] cache = new int[r]; 
		int iStart = i;
		int jStart = j;

		int iMin = iStart;
		int jMin = jStart ;
		int iMax = iStart + m - 1;
		int jMax = jStart + n - 1;
		
		int c = 0;
		boolean fromCache = false;
		do {			
			int nextVal = matrix.get(iNext).get(jNext);
			int currVal;
			if(c < r) {
				if(fromCache) {
					currVal = cache[c];
				} else {
					currVal = matrix.get(i).get(j);
				}											
			} else {
				fromCache = true;
				c = 0;				
				currVal = cache[c];
			}
			
			matrix.get(iNext).set(jNext, currVal);
			
			cache[c++] = nextVal;
			int nextPos[] = next(i, j, iMin, jMin, iMax , jMax);
			i = nextPos[0];
			j = nextPos[1];

			int nextPosToChange[] = next(iNext, jNext, iMin, jMin, iMax, jMax);
			iNext = nextPosToChange[0];
			jNext = nextPosToChange[1];

		} while (iStart != i || jStart != j);

	}

	private static int[] next(int i, int j, int iMin, int jMin, int iMax, int jMax) {

		if (j == jMin) { // down
			if (i + 1 > iMax) {
				j = j + 1;
				i = iMax;
			} else {
				i = i + 1;
			}

			return new int[] { i, j };
		}

		if (i == iMax) { // right
			if (j + 1 > jMax) {
				i = i - 1;
				j = jMax;
			} else {
				j = j + 1;
			}

			return new int[] { i, j };
		}

		if (j == jMax) { // up
			if (i - 1 < iMin) {
				j = j - 1;
				i = iMin;
			} else {
				i = i - 1;
			}

			return new int[] { i, j };
		}

		if (i == iMin) { // left
			if (j - 1 < jMin) {
				i = i + 1;
				j = jMin;
			} else {
				j = j - 1;
			}

			return new int[] { i, j };
		}

		return new int[] { i, j };

	}

	private static int[] newPosition(int iStart, int jStart, int mSteps, int nSteps, int r) {

		int i = 0;
		int j = 0;

		if (r <= mSteps) { // down and less or equal steps
			j = 0;
			i = r;
			return new int[] { i + iStart, j + jStart };
		}
		// down and more steps
		r = r - mSteps;

		if (r <= nSteps) { // right
			j = r;
			i = mSteps;
			return new int[] { i + iStart, j + jStart };
		}

		r = r - nSteps;

		if (r <= mSteps) { // up
			j = nSteps;
			i = mSteps - r;
			return new int[] { i + iStart, j + jStart };
		}

		r = r - mSteps;

		if (r <= nSteps) { // left
			i = 0;
			j = nSteps - r;
			return new int[] { i + iStart, j + jStart };
		}

		r = r - nSteps;

		// should not reach here ever
		return new int[] { i + iStart, j + jStart };
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int m = Integer.parseInt(mnr[0]);

		int n = Integer.parseInt(mnr[1]);

		int r = Integer.parseInt(mnr[2]);

		List<List<Integer>> matrix = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			String[] matrixRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

			List<Integer> matrixRowItems = new ArrayList<>();

			for (int j = 0; j < n; j++) {
				int matrixItem = Integer.parseInt(matrixRowTempItems[j]);
				matrixRowItems.add(matrixItem);
			}

			matrix.add(matrixRowItems);
		}

		matrixRotation(matrix, r);

		bufferedReader.close();
	}
}
