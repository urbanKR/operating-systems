package OperatingSystems1Package;

import java.util.Scanner;

public class SJF {
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
		boolean isFinished[] = new boolean[n];
		//int  = new int[n];

		for(int i=0; i<n; i++) {
			System.out.println("enter process nr "+(i+1)+" arrival time:");
			arrT[i] = scan.nextInt();
			System.out.println("enter process "+(i+1)+" burst time:");
			burstT[i] = scan.nextInt();
			id[i] = i + 1;
			isFinished[i] = false;
		}
//		int x = 0;
//		for(int i=0; i<n-1; i++) {
//			for(int j=i+1; j<n; j++) {
//				if(arrT[i] > arrT[j])
//				{
//					x = arr 	T[i];
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
//
//
//		for(int i=0; i<n-1; i++) {
//			for(int j=i+1; j<n; j++) {
//				if(arrT[i] == arrT[j] && burstT[i] > burstT[j])
//				{
//					x = burstT[j];
//					burstT[j] = burstT[i];
//					burstT[i] = x;
//					x = id[i];
//					id[i] = id[j];
//					id[j] = x;
//				}
//			}
//		}

		int j = 0; 
		//int count = 0;
		int generalT = findMin(arrT);
		while(j < n) {
			int l = n;
			int currT = Integer.MAX_VALUE;
			for(int i=0; i<n; i++) {
				if(arrT[i] <= generalT && isFinished[i] == false && burstT[i] < currT)
				{
					currT = burstT[i];
					l = i;
				}
			}
			if(l == n)
			{
				generalT++;
			}
			else
			{
				//count++;
				completionT[l] = generalT + burstT[l];
				generalT += burstT[l];
				waitingT[l] = completionT[l] - arrT[l] - burstT[l];
				isFinished[l] = true;
				j++;
			}
		}
//		int x = 0;
//		for(int i=0; i<n-1; i++) {
//			for(int k=i+1; k<n; k++) {
//				if(arrT[i] > arrT[k])
//				{
//					x = arrT[i];
//					arrT[i] = arrT[k];
//					arrT[k] = x;
//					x = burstT[k];
//					burstT[k] = burstT[i];
//					burstT[i] = x;
//					x = id[i];
//					id[i] = id[k];
//					id[k] = x;
//				}
//			}
//		}

//		x = 0;
//		for(int i=0; i<n; i++) {
//			if(i == 0)
//			{
//				completionT[i] = arrT[i] + burstT[i];
//				x = completionT[i];
//			}
//			else 
//			{
//				completionT[i] = x + burstT[i];
//				x = completionT[i];
//			}
//			waitingT[i] =  completionT[i] - arrT[i] - burstT[i];     
//			avg += waitingT[i];               
//		}
//		int x = 0;
		
		
//		for(int i=0; i<n-1; i++) {
//			for(int o=i+1; o<n; o++) {
//				if(queue[i] > queue[o])
//				{
//					x = arrT[i];
//					arrT[i] = arrT[o];
//					arrT[o] = x;
//					x = burstT[o];
//					burstT[o] = burstT[i];
//					burstT[i] = x;
//					x = id[i];
//					id[i] = id[o];
//					id[o] = x;
//					x = completionT[i];
//					completionT[i] = completionT[o];
//					completionT[o] = x;
//					x = waitingT[i];
//					waitingT[i] = waitingT[o];
//					waitingT[o] = x;
//				}
//			}
//		}
		
		System.out.println("id  arrivalT  burstT  completionT  waitingT");
		for(int i=0; i<n; i++) {
			avg += waitingT[i];
			System.out.println(id[i]+"\t"+arrT[i]+"\t"+burstT[i]+"\t"+completionT[i]+"\t\t"+waitingT[i]);
		}
		avg = avg/n;
		scan.close();
		System.out.println("\naverage waiting time: "+avg);
	}
}
