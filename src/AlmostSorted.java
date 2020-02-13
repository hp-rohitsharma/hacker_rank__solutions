import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class AlmostSorted {

    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {

    	if(sorted(arr)) {
    		// print and return
    		System.out.println("yes");
    		return;
    	}
    	
    	int v = arr[0];
    	int i = 1;
    	
    	while(i < arr.length) {
    		// when previous value is greater than current then its a problem 
    		if(v > arr[i]) {
    			// check for least value now as that should only be swapped to get sorted  
    			// i -1 because previous value is biggest so far
    			int j = getLeastValueIndex(arr, i - 1);
    			
    			swap(arr, j, i - 1);
    			if(sorted(arr)) {
    				// print and return
    				System.out.println("yes");
    				System.out.println("swap "+(i)+" "+(j + 1));
    				return;
    			} else {
    				//revert
    				swap(arr, j, i - 1);
    				break;
    			}
    			
    		}
    		v = arr[i];
    		i++;
    	}
    	
    	int start = 0;
    	int k = 1;
    	while(k < arr.length) {
    		if(arr[k] < arr[k - 1]) {
    			start = k - 1;
    			break;
    		}
    		k++;
    	}
    	
    	int end = arr.length - 1;
    	while(k < arr.length) {
    		if(arr[k] > arr[k - 1]) {
    			end = k - 1;
    			break;
    		}
    		k++;
    	}
    			
    	reverse(arr, start, end);
    	
    	if(sorted(arr)) {
    		// print and  return
    		System.out.println("yes");
    		System.out.println("reverse "+(start + 1)+" "+(end + 1));
    	} else {
    		// print and return
    		System.out.println("no");
    	}
    	
    }

    private static int getLeastValueIndex(int[] arr, int i) {
		int min = arr[i];
		int minIndex = i;
		
    	while(i < arr.length) {
    		if(arr[i] < min) {
    			min = arr[i];
    			minIndex = i;
    		} 
    		i++;
    	}
    		
		return minIndex;
	}

	private static void reverse(int[] arr, int start, int end) {
		while(start < end) {
			swap(arr, start, end);
			start++;
			end--;
		}
	}

	private static void swap(int[] arr, int i, int j) {
    	int v = arr[i];
    	arr[i] = arr[j];
    	arr[j] = v;
    }
    
    private static boolean sorted(int[] arr) {
    	int i = 1;
    	while(i < arr.length) {
    		if(arr[i] < arr[i-1]) {
    			return false;
    		}
    		i++;
    	}
    	return true;
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }
}
