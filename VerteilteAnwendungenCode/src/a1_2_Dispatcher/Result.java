package a1_2_Dispatcher;

public class Result {
	int amountOfValues;
	int remainingCalls;
	private int[] resultData;
	
	public Result(int amountOfValues) {
		this.amountOfValues = amountOfValues;
		resultData = new int[amountOfValues];
		remainingCalls = amountOfValues;
	}
	
	
	public synchronized int[] getEndResult() {
		if (remainingCalls > 0) {
			try {
				wait();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		return resultData;
	}
	
	public synchronized void setSingleResult(int resultIndex, int resultValue) {
		resultData[resultIndex] = resultValue;
		remainingCalls --;
		if(remainingCalls == 0) {
			notify();
		}
	}
}

