package operatingSystems5Package;

import java.util.Arrays;
import java.util.Scanner;

public class OperatingSystems5Class {



	public static int findMin(int arr[]) {
		int mini = Integer.MAX_VALUE;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] < mini )
			{
				mini = arr[i];
			}
		}
		return mini;
	}

	public static int findMax(int arr[]) {
		int max = Integer.MIN_VALUE;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] > max)
			{
				max = arr[i];
			}
		}
		return max;
	}

	public static boolean isInArr(int nr, int arr[]) {
		int n = arr.length;
		boolean result = false;
		for(int i=0; i<n; i++)
		{
			if(arr[i] == nr)
			{
				result = true;
			}
		}
		return result;
	}

	public static int findElemId(int el, int arr[]) {
		int id = -1;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == el)
			{
				id = i;
				break;
			}
		}
		return id;
	}

	public static void sortAsc(int arr[]) {
		int n = arr.length;
		int x = 0;
		for(int i=0; i<n-1; i++)
		{
			for(int j=i+1; j<n; j++)
			{
				if(arr[i] > arr[j])
				{
					x = arr[i];
					arr[i] = arr[j];
					arr[j] = x;
				}
			}
		}
	}

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static int[] getRandArr(int n, int min, int max) {
		int arr[] = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = getRandomNumber(min, max);
		}
		return arr;
	}
	
	public static int getSumUntilEqual(int arr[], int bound) {
		int sum = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == 0) break;
			if(sum + arr[i] <= bound)
			{
				sum += arr[i];
			}
		}
		return sum;
	}
	
	public static int getCountUntilEqual(int arr[], int bound, int start) {
		int sum = 0;
		int count = 0;
		for(int i=start; i<arr.length; i++) {
			if(arr[i] == 0) break;
			if(sum + arr[i] <= bound)
			{
				sum += arr[i];
				count++;
			}
		}
		return count;
	}

	public static int lengthWithout0(int arr[]) {
		int result = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] != 0)
			{
				result++;
			}
		}
		return result;
	}
	
//	public static int CPULoad(int arr[][], int valArr[][], int time) {
//		int result = 0;
//		int count = 0;
//		for(int i=0; i<valArr.length; i++) {
//			int j = 0;
//			while(j < valArr[i].length && valArr[i][j] != 0) {
//				int sum = 0;
//				count = 0;
//				while(j < valArr[i].length && valArr[i][j] != 0) {
//					if(sum + valArr[i][j] <= 100)
//					{
//						sum += valArr[i][j];
//					}
//					else
//					{
//						result += time;
//						count++;
//						sum = 0;
//					}
//					j++;
//				}
//			}
//		}
//		return result/count;
//	}
	
//	while(j < arr[i].length && arr[i][j] != 0) {
//		j = getCountUntilEqual(valArr[i], 100, start);
//		result += time;
//		count++;
//		start += j;
//	}
	
	
	public static int CPULoad(int arr[][], int valArr[][], int time) {
	int result = 0;
	int avg = 0;
	int deviation = 0;
	int avgDeviation = 0;
	int prev = 0;
	for(int i=0; i<valArr.length; i++) {
		for(int j=0; j<valArr[i].length; j++) {
			avg += valArr[i][j];
		}
		avg = avg/lengthWithout0(valArr[i]);
		if(i != 0) deviation = Math.abs(avg - prev);
		avgDeviation += deviation;
		result += avg;
		prev = avg;
		avg = 0;
	}
	System.out.println("avg deviation from the value in previous point: " + avgDeviation/(arr.length - 1));
	return result/arr.length;
}
	
	public static void main(String args[]) {
		int n;
		Scanner scan = new Scanner(System.in);
		System.out.println("Number of processors: ");
		n = scan.nextInt();
		int processorArr[][] = new int[n][100];
		int processorArrV[][] = new int[n][100];
		int time = 5;
		
		for(int i=0; i<n; i++) {
			processorArr[i] = getRandArr(getRandomNumber(1, 100), 1, 1000);
		}
		for(int i=0; i<processorArr.length; i++) {
			for(int j=0; j<processorArr[i].length; j++) {
				processorArrV[i][j] = getRandomNumber(1, 100);
			}
		}
//		for(int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(processorArrV[i]));
//		}
		System.out.println("avg CPU load: " + CPULoad(processorArr, processorArrV, time));
		scan.close();
	}
}
