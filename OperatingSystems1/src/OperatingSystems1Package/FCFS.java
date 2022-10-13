package OperatingSystems1Package;

import java.util.*;

public class FCFS {
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

		for(int i=0; i<n; i++) {
			System.out.println("enter process nr "+(i+1)+" arrival time:");
			arrT[i] = scan.nextInt();
			System.out.println("enter process "+(i+1)+" burst time:");
			burstT[i] = scan.nextInt();
			id[i] = i + 1;
		}
		int x = 0;
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				if(arrT[i] > arrT[j])
				{
					x = arrT[i];
					arrT[i] = arrT[j];
					arrT[j] = x;
					x = burstT[j];
					burstT[j] = burstT[i];
					burstT[i] = x;
					x = id[i];
					id[i] = id[j];
					id[j] = x;
				}
				
			}
		}
		
		x = 0;
		for(int i=0; i<n; i++) {
			if(i == 0)
			{
				completionT[i] = arrT[i] + burstT[i];
				x = completionT[i];
			}
			else 
			{
				completionT[i] = x + burstT[i];
				x = completionT[i];
			}
			waitingT[i] =  completionT[i] - arrT[i] - burstT[i];     
			avg += waitingT[i];               
		}
		avg = avg/n;
		System.out.println("id  arrivalT  burstT  completionT  waitingT");
		for(int i=0; i<n; i++) {
			System.out.println(id[i]+"\t"+arrT[i]+"\t"+burstT[i]+"\t"+completionT[i]+"\t\t"+waitingT[i]);
		}
		scan.close();
		System.out.println("\naverage waiting time: "+avg);
	}
}