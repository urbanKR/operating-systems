package OperatingSystems1Package;

import java.util.*;

public class RR {
	public static int findMin(int t[]) {
		int min = t[0];
		for(int i=0; i<t.length; i++) {
			if(t[i] < min)
			{
				min = t[i];
			}
		}
		return min;
	}
	public static int findMax(int t[]) {
		int max = t[0];
		for(int i=0; i<t.length; i++) {
			if(t[i] > max)
			{
				max = t[i];
			}
		}
		return max;
	}
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter number of processes:");
		int n = scan.nextInt();
		int id[] = new int[n];   
		int arrT[] = new int[n];     
		int burstT[] = new int[n];
		int completionT[] = new int[n];       
		int waitingT[] = new int[n];    
		double avg=0;
		int burstCopy[] = new int[n];

		
		System.out.println("enter time quantum:");
		int tq = scan.nextInt();
		for(int i=0; i<n; i++) {
			System.out.println("enter process nr "+(i+1)+" arrival time:");
			arrT[i] = scan.nextInt();
			System.out.println("enter process "+(i+1)+" burst time:");
			burstT[i] = scan.nextInt();
			id[i] = i + 1;
			burstCopy[i] = burstT[i];
		}
//		int x = 0;
//		for(int i=0; i<n-1; i++) {
//			for(int j=i+1; j<n; j++) {
//				if(arrT[i] > arrT[j])
//				{
//					x = arrT[i];
//					arrT[i] = arrT[j];
//					arrT[j] = x;
//					x = burstT[j];
//					burstT[j] = burstT[i];
//					burstT[i] = x;
//					x = burstCopy[j];
//					burstCopy[j] = burstCopy[i];
//					burstCopy[i] = x;
//					x = id[i];
//					id[i] = id[j];
//					id[j] = x;
//					
//				}
//			}
//		}
		int allB = 1;
		while(allB != 0) {
			for(int i=0; i<n; i++) {
				if(burstT[i] > tq) 
				{
					for(int j=0; j<n; j++) {
						if(j != i && burstT[j] != 0)
						{
							waitingT[j] += tq;
						}
					}
					burstT[i] -= tq;
				}
				else {
					for(int j=0; j<n; j++) {
						if(j != i && burstT[j] != 0)
						{
							waitingT[j] += burstT[i];
						}
					}
					burstT[i] = 0;
				}
			}
			allB = 0;
			for(int i=0; i<n; i++) {
				allB += burstT[i];
			}
		}
//		int count = 0;
//		int currT = 0;
//		while(count < n)  
//		{  
//			for(int i=0; i<n; i++)  
//			{  
//				int x = tq;  
//				if(burstT[i] == 0)  
//				{  
//					count++;    
//				}  
//				else if(burstT[i] > tq)
//				{
//					burstT[i] = burstT[i] - tq; 
//				}
//				else if(burstT[i] >= 0)  
//				{  
//					x = burstT[i];  
//					burstT[i] = 0;  
//				}  
//				currT = currT + x;  
//				turnT[i] = currT;  
//			}
//		}  
		//		int x = 0;
		//		for(int i=0; i<n-1; i++) {
		//			for(int j=i+1; j<n; j++) {
		//				if(arrT[i] > arrT[j])
		//				{
		//					x = arrT[i];
		//					arrT[i] = arrT[j];
		//					arrT[j] = x;
		//					x = burstT[j];
		//					burstT[j] = burstT[i];
		//					burstT[i] = x;
		//					x = id[i];
		//					id[i] = id[j];
		//					id[j] = x;
		//				}
		//			}
		//		}
		//		int r = 0;
		//		int currT = findMin(arrT);
		//		int maxT = findMax(arrT);
		//		while(currT < maxT) {
		//			if(tq <= burstT[currT])
		//			{
		//				burstT[currT] -= tq;
		//				currT += tq;
		//			}
		//			else
		//			{
		//				burstT[currT] = 0;
		//				currT = currT + burstT[currT];
		//				
		//			}
		//			r++;
		//			while(r < n) {
		//				if(arrT[r] < currT)
		//				{
		//					currT = arrT[r];
		//					break;
		//				}
		//			}
		//		}


		System.out.println("id  arrivalT  burstT  completionT  waitingT");
		for(int i=0; i<n; i++) {
			completionT[i] = waitingT[i] + arrT[i] + burstCopy[i];
			avg += waitingT[i];   
			System.out.println(id[i]+"\t"+arrT[i]+"\t"+burstCopy[i]+"\t"+completionT[i]+"\t\t"+waitingT[i]);
		}
		avg = avg/n;
		scan.close();
		System.out.println("\naverage waiting time: "+avg);
	}
}