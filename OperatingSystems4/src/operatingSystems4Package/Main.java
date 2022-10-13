package operatingSystems4Package;

import java.util.Arrays;
import java.util.Scanner;

public class Main extends FrameAllocation{
	public static void main(String args[]) {
		int processNr, totalFrameNr;
		int totalSize = 0;
		int usedFrames = 0;
		boolean isLast = false;
		int minPageVal = 0;
		int maxPageVal = 0;
		int pageNr = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Number of processes: ");
		processNr = scan.nextInt();
		int ProcessArrPNr[] = new int [processNr];
		int ProcessArrMaxP[] = new int [processNr];
		int ProcessArrMinP[] = new int [processNr];
		int sumArr[] = new int [processNr];
		int[][] ProcessReferenceArr = new int[processNr][];  
		System.out.println("Total number of frames: ");
		totalFrameNr = scan.nextInt();
//		System.out.println("Min Page value: ");
//		minPageVal = scan.nextInt();
//		System.out.println("Max Page value: ");
//		maxPageVal = scan.nextInt();
		for(int i=0; i<processNr; i++) {
			System.out.println("nr "+(i+1)+" Number of pages: ");
			ProcessArrPNr[i] = scan.nextInt();
			System.out.println("nr "+(i+1)+" Min Page value: ");
			ProcessArrMinP[i] = scan.nextInt();
			System.out.println("nr "+(i+1)+" Max Page value: ");
			ProcessArrMaxP[i] = scan.nextInt();
			ProcessReferenceArr[i] = getRandArr2(ProcessArrPNr[i], ProcessArrMinP[i], ProcessArrMaxP[i]);
			totalSize += sumEl(ProcessReferenceArr[i]);
			sumArr[i] = 0;

		}
		for(int k=0; k<processNr; k++) {
			System.out.println("Page array nr "+(k+1)+": "+Arrays.toString(ProcessReferenceArr[k])+"\n");
		}

		
		//WorkingSetPage
		
		for(int l=0; l<processNr; l++) {
			System.out.println("Process nr "+(l+1)+" WorkingSetPage, FIFO: "+WorkingSetPage(ProcessReferenceArr[l], 
					Random(totalFrameNr, usedFrames, isLast))+"\n");
			System.out.println("Process nr "+(l+1)+" WorkingSetPage, OPT: "+WorkingSetPage(ProcessReferenceArr[l], 
					Proportional(sumEl(ProcessReferenceArr[l]), totalSize, totalFrameNr, 
							ProcessArrPNr[l], usedFrames, isLast))+"\n");
			System.out.println("Process nr "+(l+1)+" WorkingSetPage, LRU: "+WorkingSetPage(ProcessReferenceArr[l], 
					Proportional(sumEl(ProcessReferenceArr[l]), totalSize, totalFrameNr, 
							ProcessArrPNr[l], usedFrames, isLast))+"\n");
			System.out.println("Process nr "+(l+1)+" WorkingSetPage, RAND: "+WorkingSetPage(ProcessReferenceArr[l], 
					Random(totalFrameNr, usedFrames, isLast))+"\n");
			System.out.println("Process nr "+(l+1)+" WorkingSetPage, ARLU: "+WorkingSetPage(ProcessReferenceArr[l], 
					Random(totalFrameNr, usedFrames, isLast))+"\n");
			usedFrames += Proportional(sumEl(ProcessReferenceArr[l]), totalSize,
					totalFrameNr, ProcessArrPNr[l], usedFrames, isLast);
			if(l == processNr - 2) isLast = true;
		}
		isLast = false;
		usedFrames = 0;
		
		//Proportional
		
		for(int k=0; k<processNr; k++) {
			int x =Proportional(sumEl(ProcessReferenceArr[k]), totalSize, totalFrameNr, 
					ProcessArrPNr[k], usedFrames, isLast);
			System.out.println("Process nr "+(k+1)+" Proportional, FIFO: "+FIFO(ProcessReferenceArr[k], 
					x)+"\n");
			System.out.println("Process nr "+(k+1)+" Proportional, OPT: "+OPT(ProcessReferenceArr[k], 
					x)+"\n");
			System.out.println("Process nr "+(k+1)+" Proportional, LRU: "+LRU(ProcessReferenceArr[k], 
					x)+"\n");
			System.out.println("Process nr "+(k+1)+" Proportional, RAND: "+RAND(ProcessReferenceArr[k], 
					x)+"\n");
			System.out.println("Process nr "+(k+1)+" Proportional, ARLU: "+ARLU(ProcessReferenceArr[k], 
					x)+"\n");
			usedFrames += Proportional(sumEl(ProcessReferenceArr[k]), totalSize,
					totalFrameNr, ProcessArrPNr[k], usedFrames, isLast);
			if(k == processNr - 2) isLast = true;
		}
//		for(int k=0; k<processNr; k++) {
//			System.out.println("Process nr "+(k+1)+" Proportional, OPT: "+OPT(ProcessReferenceArr[k], 
//					Proportional(sumEl(ProcessReferenceArr[k]), totalSize, totalFrameNr, 
//							ProcessArrPNr[k], usedFrames, isLast))+"\n");
//			usedFrames += Proportional(sumEl(ProcessReferenceArr[k]), totalSize,
//					totalFrameNr, ProcessArrPNr[k], usedFrames, isLast);
//			if(k == processNr - 2) isLast = true;
//		}
//		for(int k=0; k<processNr; k++) {
//			System.out.println("Process nr "+(k+1)+" Proportional, LRU: "+LRU(ProcessReferenceArr[k], 
//					Proportional(sumEl(ProcessReferenceArr[k]), totalSize, totalFrameNr, 
//							ProcessArrPNr[k], usedFrames, isLast))+"\n");
//			usedFrames += Proportional(sumEl(ProcessReferenceArr[k]), totalSize,
//					totalFrameNr, ProcessArrPNr[k], usedFrames, isLast);
//			if(k == processNr - 2) isLast = true;
//		}
//		for(int k=0; k<processNr; k++) {
//			System.out.println("Process nr "+(k+1)+" Proportional, RAND: "+RAND(ProcessReferenceArr[k], 
//					Proportional(sumEl(ProcessReferenceArr[k]), totalSize, totalFrameNr, 
//							ProcessArrPNr[k], usedFrames, isLast))+"\n");
//			usedFrames += Proportional(sumEl(ProcessReferenceArr[k]), totalSize,
//					totalFrameNr, ProcessArrPNr[k], usedFrames, isLast);
//			if(k == processNr - 2) isLast = true;
//		}
//		for(int k=0; k<processNr; k++) {
//			System.out.println("Process nr "+(k+1)+" Proportional, ARLU: "+ARLU(ProcessReferenceArr[k], 
//					Proportional(sumEl(ProcessReferenceArr[k]), totalSize, totalFrameNr, 
//							ProcessArrPNr[k], usedFrames, isLast))+"\n");
//			usedFrames += Proportional(sumEl(ProcessReferenceArr[k]), totalSize,
//					totalFrameNr, ProcessArrPNr[k], usedFrames, isLast);
//			if(k == processNr - 2) isLast = true;
//		}
		isLast = false;
		usedFrames = 0;
		
		//Random
		
		for(int j=0; j<processNr; j++) {
			int randomFrame = Random(totalFrameNr, usedFrames, isLast);
			System.out.println("Process nr "+(j+1)+" Random, FIFO: "+FIFO(ProcessReferenceArr[j],randomFrame)+"\n");
			System.out.println("Process nr "+(j+1)+" Random, OPT: "+OPT(ProcessReferenceArr[j],randomFrame)+"\n");
			System.out.println("Process nr "+(j+1)+" Random, LRU: "+LRU(ProcessReferenceArr[j],randomFrame)+"\n");
			System.out.println("Process nr "+(j+1)+" Random, RAND: "+RAND(ProcessReferenceArr[j],randomFrame)+"\n");
			System.out.println("Process nr "+(j+1)+" Random, ARLU: "+ARLU(ProcessReferenceArr[j],randomFrame)+"\n");
//			System.out.println("\n"+randomFrame+"\n");
			usedFrames += Random(totalFrameNr, usedFrames, isLast);
			if(j == processNr - 2) isLast = true;
		}
		
		//Equal
		
//		for(int k=0; k<processNr; k++) {
//			System.out.println("Process nr "+(k+1)+" Equal: "+OPT(ProcessReferenceArr[k], 
//					Equal(processNr, totalFrameNr, usedFrames, isLast))+"\n");
//			System.out.println("\n"+Equal(processNr, totalFrameNr, usedFrames, isLast)+"\n");
//			usedFrames += Equal(processNr, totalFrameNr, usedFrames, isLast);
//			if(k == processNr - 2) isLast = true;
//		}
		
		//PFF
		
		int currFrameNr[] = new int[processNr];
		int testPageF[] = new int[processNr];
		for(int i=0; i<processNr; i++) {
			currFrameNr[i] = (int)Math.round((float)((float)usedFrames/(float)processNr));
			for(int j=0; j<processNr; j++) {
				testPageF[j] = OPT(ProcessReferenceArr[j],currFrameNr[j]);
			}
			int maxPf = findMax(testPageF);
			int minPf = findMin(testPageF);
			currFrameNr[findElemId(maxPf, testPageF)] -= 1;
			currFrameNr[findElemId(minPf, testPageF)] += 1;
		}
		for(int j=0; j<processNr; j++) {
//			System.out.println("\n"+currFrameNr[j]+"\n");
			System.out.println("Process nr "+(j+1)+" PFF, FIFO: "+FIFO(ProcessReferenceArr[j],currFrameNr[j])+"\n");
			System.out.println("Process nr "+(j+1)+" PFF, OPT: "+OPT(ProcessReferenceArr[j],currFrameNr[j])+"\n");
			System.out.println("Process nr "+(j+1)+" PFF, LRU: "+LRU(ProcessReferenceArr[j],currFrameNr[j])+"\n");
			System.out.println("Process nr "+(j+1)+" PFF, RAND: "+RAND(ProcessReferenceArr[j],currFrameNr[j])+"\n");
			System.out.println("Process nr "+(j+1)+" PFF, ARLU: "+ARLU(ProcessReferenceArr[j],currFrameNr[j])+"\n");
		}
		

		

		scan.close();
	}
}
