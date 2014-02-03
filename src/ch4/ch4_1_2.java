package ch4;

/**
 * 4.1.2 
 * Write pseudocode for the brute-force method of solving the maximum-subarray problem.
 * Your pseudore should run in Theta(n^2) time
 * 
 * 4.1.3
 * Implement both the brute-force and recursive algorithms for the maximum-subarray problem
 * on your computer. What problem size n0 give the corssover point at which the recursive 
 * algorithm beats the brute-force algorithm?
 * Then, change the base case of the recursive algorithm to use the brute-force algorithm
 * whenever the problem size is less than n0. Does that change the corssover point?
 * 
 * @author jerry
 *
 */

import java.math.*;

public class ch4_1_2 {
	//use the array from CLRS 4.1
	private static int[] A = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

	public static int[] bruteForce(int[] A, int low, int high) {
		int[] result = new int[3];	//result[0]: max-left, result[1]: max-right, result[2]: max-sum
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		
		for (int i = low; i <= high; i++) {
			for (int j = i; j <= high; j++) {
				sum += A[j];
				if (sum > maxSum) {
					maxSum = sum;
					result[0] = i;
					result[1] = j;
				}
			}
			sum = 0;
		}
		result[2] = maxSum;
		return result;
	}
	
	public static int[] findMaxSubArray(int[] A, int low, int high) {
		int[] result = new int[3];	
		
		if (high == low) {
			result[0] = low;
			result[1] = high;
			result[2] = A[low];
		} else {
			int mid = (low + high) / 2;
			int[] leftResult = findMaxSubArray(A, low, mid);
			int[] rightResult = findMaxSubArray(A, mid + 1, high);
			int[] crossResult = findMaxCrossingSubarray(A, low, mid, high);
			
			if (leftResult[2] >= rightResult[2] && leftResult[2] >= crossResult[2]) result = leftResult;
			else if (rightResult[2] >= leftResult[2] && rightResult[2] >= crossResult[2]) result = rightResult;
			else result = crossResult;
		}
		
		return result;
	}
	
	public static int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
		int[] result = new int[3];
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		
		for (int i = mid; i >= low; i--) {
			sum = sum + A[i];
			if (sum > leftSum) {
				leftSum = sum;
				//max-left
				result[0] = i;
			}
		}
		
		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		for (int j = mid + 1; j <= high; j++) {
			sum = sum + A[j];
			if (sum > rightSum) {
				rightSum = sum;
				//max-right
				result[1] = j;
			}
		}
		
		result[2] = leftSum + rightSum;
		return result;
	}
	
	//Test method
	public static void main(String[] args) {
		int high = A.length - 1;
		long startTime = System.currentTimeMillis();
		int[] bruteResult = findMaxSubArray(A, 0, high);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		for (int i = 0; i < 3; i++) {
			System.out.print(bruteResult[i] + " ");
		}
		System.out.println("Time: " + totalTime);
	}
	
}
