package OperatingSystems1Package;

import java.util.Scanner;

public class SJFPreemptive {
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
		int burstCopy[] = new int[n];

		for(int i=0; i<n; i++) {
			id[i] = i + 1;
			isFinished[i] = false;
			System.out.println("enter process nr "+(i+1)+" arrival time:");
			arrT[i] = scan.nextInt();
			System.out.println("enter process "+(i+1)+" burst time:");
			burstT[i] = scan.nextInt();
			burstCopy[i] = burstT[i];
		}
		
		int j = 0; 
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
				burstT[l]--;
				generalT++;
				if(burstT[l] == 0)
				{
					completionT[l] = generalT;
					isFinished[l] = true;
					j++;
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			waitingT[i] = completionT[i] - arrT[i] - burstCopy[i];
		}

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
