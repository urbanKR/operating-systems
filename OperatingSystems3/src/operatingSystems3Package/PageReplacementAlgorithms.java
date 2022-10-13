package operatingSystems3Package;

import java.util.Arrays;
import java.util.Scanner;

public class PageReplacementAlgorithms {
	
	public static int FIFO(int arr[], int frameNr) {
		int pageArr[] = new int[frameNr];
		for(int j=0; j<frameNr; j++) {
			pageArr[j] = -1;
		}
		
		int pageFNr = 0;
		int line = 0;
		for(int i=0; i<arr.length; i++) {
				if(!isInArr(arr[i], pageArr))
				{
					pageFNr++;
					pageArr[line] = arr[i];
					line++;
				}
//			}
//			else
//			{
//				if(!isInArr(arr[i], pageArr))
//				{
//					pageFNr++;
//					pageArr[line] = arr[i];
//					line++;
//				}
//			}
			if(line > frameNr - 1)
			{
				line = 0;
			}
		}
		return pageFNr;
	}
	
	public static int OPT(int arr[], int frameNr) {
		int pageArr[] = new int[frameNr];
		int pageFNr = 0;
		for(int j=0; j<frameNr; j++) {
			if(!isInArr(arr[j], pageArr))
			{
				pageArr[j] = arr[j];
				pageFNr++;
			}
			else
			{
				pageArr[j] = 0;
			}
		}
		int count = 0;
		int maxDist = Integer.MIN_VALUE;
		int firstOccNr = -1;
		for(int i=frameNr; i<arr.length; i++) {
				if(!isInArr(arr[i], pageArr))
				{
					pageFNr++;
					if(i+1 < arr.length)
					{
						for(int l=0; l<frameNr; l++) {
							for(int k=i+1; k<arr.length; k++) {
								count++;
								if(arr[k] == pageArr[l])
								{
									break;
								}
							}
							if(count > maxDist)
							{
								maxDist = count;
								firstOccNr = pageArr[l];
							}
							count = 0;
						}
						if(firstOccNr != -1)
						{
							pageArr[findElemId(firstOccNr, pageArr)] = arr[i];
							firstOccNr = -1;
							maxDist = Integer.MIN_VALUE;
						}
					}
				}
		}
		return pageFNr;
	}
	
	public static int LRU(int arr[], int frameNr) {
		int pageArr[] = new int[frameNr];
		int pageOccArr[] = new int[frameNr];
		int pageFNr = 0;
		for(int i=0; i<frameNr; i++) {
			pageArr[i] = 0;
			pageOccArr[i] = 0;
		}
		for(int i=0; i<frameNr; i++) {
			if(!isInArr(arr[i], pageArr))
			{
				pageFNr++;
				pageArr[i] = arr[i];
			}
			for(int j=frameNr-1-i; j>=0; j--) {
				pageOccArr[j]++;
			}
		}
		for(int i=frameNr; i<arr.length; i++) {
			if(!isInArr(arr[i], pageArr))
			{
				pageFNr++;
				pageArr[findElemId(findMax(pageOccArr),pageOccArr)] = arr[i];
				pageOccArr[findElemId(findMax(pageOccArr),pageOccArr)] = 0;
			}
			else
			{
				for(int j=0; j<frameNr; j++) {
					if(pageArr[j] == arr[i])
					{
						pageOccArr[j] = 0;
					}
				}
			}
			for(int k=0; k<frameNr; k++) {
				pageOccArr[k]++;
			}
		}
		return pageFNr;
	}
	
	public static int RAND(int arr[], int frameNr) {
		int pageArr[] = new int[frameNr];
		int pageFNr = 0;
		for(int j=0; j<frameNr; j++) {
			if(!isInArr(arr[j], pageArr))
			{
				pageArr[j] = arr[j];
				pageFNr++;
			}
			else
			{
				pageArr[j] = 0;
			}
		}
		int randFrameNr;
		for(int i=0; i<arr.length; i++) {
			if(!isInArr(arr[i], pageArr))
			{
				pageFNr++;
				randFrameNr = getRandomNumber(0, frameNr-1);
				pageArr[randFrameNr] = arr[i];
			}
		}
		return pageFNr;
	}
	
	public static int ARLU(int arr[], int frameNr) {
		int pageArr[] = new int[frameNr];
		int referenceBitArr[] = new int[frameNr];
		for(int j=0; j<frameNr; j++) {
			pageArr[j] = -1;
			referenceBitArr[j] = 0;
		}
		int pageFNr = 0;
		int line = 0;
		for(int i=0; i<arr.length; i++) {
			if(!isInArr(arr[i], pageArr))
			{
				pageFNr++;
				for(int k=line; k<frameNr; k++) {
					if(referenceBitArr[k] == 0)
					{
						pageArr[line] = arr[i];
						line++;
						break;
					}
					else
					{
						referenceBitArr[k] = 0;
						line++;
					}
				}
				if(line == frameNr)
				{
					line = 0;
				}
			}
			else
			{
				referenceBitArr[findElemId(arr[i], pageArr)] = 1;
			}
		}
		return pageFNr;
	}
	
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
	
	public static int[] getRandArr2(int n, int min, int max) {
		int arr[] = new int[n];
		int k = min+2;
		int l = min;
		for(int i=0; i<n; i++) {
			arr[i] = getRandomNumber(l, k);
			k=k*2;
			if(k>max)
			{
				k=max;
			}
			if(l<=max)
			{
				l=k/2;
			}
		}
		return arr;
	}

	public static void main(String args[]) {
		int n, minP, maxP, frameNr;
		Scanner scan = new Scanner(System.in);
		System.out.println("Number of pages: ");
		n = scan.nextInt();
		System.out.println("Max Page value: ");
		minP = scan.nextInt();
		System.out.println("Min Page value: ");
		maxP = scan.nextInt();
		System.out.println("Number of frames: ");
		frameNr = scan.nextInt();
		int ReferenceArr[] = getRandArr2(n, maxP, minP);
//		ReferenceArr[] = {0,4,1,4,2,4,3,4,2,4,0,4,1,4,2,4,3,4};
		System.out.println("Page array: "+Arrays.toString(ReferenceArr)+"\n");
//		int testArr[] = new int[9];
//		
//		for(int i=0; i< testArr.length; i++) {
//			testArr[i] = getRandomNumber(0, 150);
//		} 
		System.out.println("FIFO: "+FIFO(ReferenceArr, frameNr));
		System.out.println("OPT: "+OPT(ReferenceArr, frameNr));
		System.out.println("LRU: "+LRU(ReferenceArr, frameNr));
		System.out.println("RAND: "+RAND(ReferenceArr, frameNr));
		System.out.println("ARLU: "+ARLU(ReferenceArr, frameNr));
		scan.close();
	}
	
}
