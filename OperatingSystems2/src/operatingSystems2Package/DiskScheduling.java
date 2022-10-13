package operatingSystems2Package;


public class DiskScheduling {

	public static void FCFS(int nr, int diskArr[], int head) {
	    System.out.println("FCFS");
		int seekT = 0;
		int dist = 0;
		int curr = 0;

		for(int i=0; i<nr; i++) {
			curr = diskArr[i];
			dist = Math.abs(curr - head);
			seekT += dist;
			head = curr;
		}

		System.out.println("Seek Sequence:");

		for(int i=0; i<nr; i++) {
			System.out.println(diskArr[i]);
		}

		System.out.println("Number of total movement of disk head: "+seekT+"\n");
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

	public static int findMinSeek(int curr, int arr[]) {
	    int seekArr[] = new int[arr.length];
	    for(int i=0; i<arr.length; i++) {
	        seekArr[i] = Math.abs(arr[i] - curr);
	    }
	    int result = findMin(seekArr);
	    return result;
	}

	public static int findElemId(int el, int arr[]) {
	    int id = -1;
	    for(int i=0; i<arr.length; i++) {
	        if(arr[i] == el)
	        {
	            id = i;
	        }
	    }
	    return id;
	}

	public static int[] removeFromArr(int id, int arr[]) {
	    int newArr[] = new int[arr.length];
	    for(int i=0; i<arr.length; i++) {
	        if(i != id)
	        {
	            newArr[i] = arr[i];
	        }
	        else
	        {
	        	newArr[i] = Integer.MAX_VALUE;
	        }
	    }
	    return newArr;
	}

	public static void SSTF(int nr, int diskArr[], int head) {
	    System.out.println("SSTF");
		int seekT = 0;
		int dist = 0;
		int curr = 0;

		System.out.println("Seek Sequence:");
		for(int i=0; i<nr; i++) {
			dist = findMinSeek(head, diskArr);
			seekT += dist;
			curr = head + dist;
			if(findElemId(curr, diskArr) == -1)
			{
				curr  = head - dist;
			}
			
			System.out.println(curr);
			
			head = curr;
			diskArr = removeFromArr(findElemId(head, diskArr), diskArr);
		}
		System.out.println("Number of total movement of disk head: "+seekT+"\n");
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

//	public static int findMinLeft(int arr[], int curr) {
//	    int mini = Integer.MAX_VALUE;
//	    int currId = findElemId(curr, arr);
//	    for(int i=0; i<currId; i++) {
//	        if(arr[i] < mini )
//	        {
//	            mini = arr[i];
//	        }
//	    }
//	    return mini;
//	}
//	
//	public static int findMinSeekL(int curr, int arr[]) {
//	    int seekArr[] = new int[arr.length];
//	    for(int i=0; i<arr.length; i++) {
//	        seekArr[i] = Math.abs(arr[i] - curr);
//	    }
//	    int result = findMinLeft(seekArr, curr);
//	    return result;
//	}
//	public static void invalidFromId(int id, int arr[]) {
//		for(int i=0; i<arr.length; i++) {
//			if(i >= id)
//			{
//				 arr[i] = Integer.MAX_VALUE;
//			}
//	    }
//	}
	//I have chosen the "towards 0" direction, but we can easily change that
	public static void SCAN(int nr, int arr[], int head) {
	    System.out.println("SCAN");
		int seekT = 0;
		int dist = 0;
		int curr = 0;
		boolean directL = true;
		int diskArr[] = new int[arr.length+2];
		diskArr[arr.length] = 0;
		diskArr[arr.length+1] = head;
		for(int k=0; k<arr.length; k++) {
			diskArr[k] = arr[k];
		}
		
		sortAsc(diskArr);
		System.out.println("Seek Sequence:");
		int count = findElemId(head, diskArr) - 1;
		diskArr = removeFromArr(findElemId(head, diskArr), diskArr);
		for(int i=0; i<nr+1; i++) {
			if(directL)
			{
				
				dist = Math.abs(diskArr[count] - head);
				seekT += dist;
				curr = head + dist;
				if(findElemId(curr, diskArr) == -1)
				{
					curr  = head - dist;
				}
				
				System.out.println(curr);
				head = curr;
				diskArr = removeFromArr(findElemId(head, diskArr), diskArr);
				count--;
				
				if(count == 0)
				{
					directL = false;
				}
				
			}
			else
			{
				dist = findMinSeek(head, diskArr);
				seekT += dist;
				curr = head + dist;
				if(findElemId(curr, diskArr) == -1)
				{
					curr  = head - dist;
				}
				
				System.out.println(curr);
				
				head = curr;
				diskArr = removeFromArr(findElemId(head, diskArr), diskArr);
			}
		}
		System.out.println("Number of total movement of disk head: "+seekT+"\n");
	}
	//I have chosen the "towards 0" direction, but we can easily change that
	public static void C_SCAN(int nr, int arr[], int head, int upBound) {
	    System.out.println("C-SCAN");
		int seekT = 0;
		int dist = 0;
		int curr = 0;
		boolean directL = true;
		int diskArr[] = new int[arr.length+2];
		diskArr[arr.length] = 0;
		diskArr[arr.length+1] = head;
		for(int k=0; k<arr.length; k++) {
			diskArr[k] = arr[k];
		}
		
		sortAsc(diskArr);
		System.out.println("Seek Sequence:");
		int count = findElemId(head, diskArr) - 1;
		diskArr = removeFromArr(findElemId(head, diskArr), diskArr);
		for(int i=0; i<nr+1; i++) {
			if(directL)
			{
				
				dist = Math.abs(diskArr[count] - head);
				seekT += dist;
				curr = head + dist;
				if(findElemId(curr, diskArr) == -1)
				{
					curr  = head - dist;
				}
				
				System.out.println(curr);
				head = curr;
				diskArr = removeFromArr(findElemId(head, diskArr), diskArr);
				count--;
				
				if(count == 0)
				{
					directL = false;
				}
				
				
			}
			else
			{
			
				if(head == 0)
				{
					curr = upBound;
					head = curr;
					System.out.println(curr);
				}
				dist = findMinSeek(head, diskArr);
				seekT += dist;
				curr = head + dist;
				if(findElemId(curr, diskArr) == -1)
				{
					curr  = head - dist;
				}
				
				System.out.println(curr);
				
				head = curr;
				diskArr = removeFromArr(findElemId(head, diskArr), diskArr);
			}
		}
		seekT += upBound;
		System.out.println("Number of total movement of disk head: "+seekT+"\n");
	}
	
	public static void EDF(int nr, int diskArr[], int head, int deadlineArr[]) {
	    System.out.println("EDF");
		int seekT = 0;
		int dist = 0;
		int curr = 0;

		System.out.println("Seek Sequence:");
		for(int i=0; i<nr; i++) {
			dist = Math.abs(diskArr[findElemId(findMin(deadlineArr), deadlineArr)] - head);
			seekT += dist;
			curr = head + dist;
			if(findElemId(curr, diskArr) == -1)
			{
				curr  = head - dist;
			}
			
			System.out.println(curr);
			
			head = curr;
			deadlineArr = removeFromArr(findElemId(head, diskArr), deadlineArr);
			diskArr = removeFromArr(findElemId(head, diskArr), diskArr);
		}
		System.out.println("Number of total movement of disk head: "+seekT+"\n");
	}
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	public static void main(String args[]) {
		//int testArr[] = {98, 183, 37, 122, 14, 124, 65, 67};
		int testArr[] = new int[9];
		
		for(int i=0; i< testArr.length; i++) {
			testArr[i] = getRandomNumber(0, 150);
		} 
		int head = 53;
		int upBound = 200;
		int deadlineArr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		//lower bound is equal 0

		FCFS(testArr.length, testArr, head);
		SSTF(testArr.length, testArr, head);
		SCAN(testArr.length, testArr, head);
		C_SCAN(testArr.length, testArr, head, upBound);
		EDF(testArr.length, testArr, head, deadlineArr);

	}
	
}