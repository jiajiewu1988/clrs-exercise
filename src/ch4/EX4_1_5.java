package ch4;

public class EX4_1_5 {
	//use the array from CLRS 4.1
	private static int[] A = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
	
	public static int[] findItoJplusOne(int[] A) {
		int[] result = new int[3];
		result[0] = 0;
		
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		
		//Find A[1..j]
		for (int j = 0; j < A.length; j++) {
			sum += A[j];
			if (sum > maxSum) {
				maxSum = sum;
				result[1] = j;
				result[2] = sum;
			}
		}
		
		//Find A[i..j+1] in constant time
		if (result[1] == A.length - 1) return result; //if A's max subarray is itself, return A[1..j]
		else {
			result[1] = result[1] + 1;
			sum = 0;
			maxSum = Integer.MIN_VALUE;
			
			//Since A[1..j] is a known size array, this loop runs in constant time
			for (int i = result[1]; i >= 0; i--) {
				sum += A[i];
				if (sum > maxSum) {
					maxSum = sum;
					result[0] = i;
					result[2] = maxSum;
				}
			}
		}
		return result;
	}
	
	public static void main(String args[]) {
		int[] result = findItoJplusOne(A);
		int sum = 0;
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		System.out.println("\n"+sum);
	}
}
