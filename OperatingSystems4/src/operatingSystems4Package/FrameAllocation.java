package operatingSystems4Package;

public class FrameAllocation extends PageClass{
	public static int Proportional(int processSize, int totalSize, int totalFrameNr, int pageNr, int usedFrames, boolean last) {
		float frameNr = 0;
		frameNr = (float)(processSize*totalFrameNr/totalSize);
		int result = (int) Math.round(frameNr);
		if(last) return totalFrameNr - usedFrames;
		if(result == 0) return 1;
		return result;
	}
	
//	public static int Equal(int processNr, int totalFrameNr, int usedFrames, boolean last) {
//		float frameNr = 0;
//		frameNr = (int)Math.ceil((float)(Math.ceil((float)totalFrameNr)/Math.ceil((float)processNr)));
//		int result = (int) Math.round(frameNr);
//		if(last) return totalFrameNr - usedFrames;
//		if(result == 0) return 1;
//		return result;
//	}
	
	public static int Random(int totalFrameNr, int usedFrames, boolean last) {
		int frameNr;
		frameNr = getRandomNumber(1, totalFrameNr);
		while(usedFrames + frameNr > totalFrameNr) {
			frameNr = getRandomNumber(1, totalFrameNr);
		}
		if(last) return totalFrameNr - usedFrames;
		return frameNr;
	}
	
//	public static int PFF(int totalFrameNr, int usedFrames, boolean last) {
//		int frameNr;
//		frameNr = getRandomNumber(1, totalFrameNr);
//		while(usedFrames + frameNr > totalFrameNr) {
//			frameNr = getRandomNumber(1, totalFrameNr);
//		}
//		if(last) return totalFrameNr - usedFrames;
//		return frameNr;
//	}
	
	public static int sumEl(int arr[]) {
	    int sum = 0;
	    for(int i=0; i<arr.length; i++) {
	      sum += arr[i];
	    }
	    return sum;
	}
}
