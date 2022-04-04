package a1_2_Dispatcher;

import java.util.Arrays;

public class Dispatcher extends Thread{
	
	public static int[] execute(F f, int n){
		Result result = new Result(n);
		
		for(int i = 0; i < n; i++) {
			DispatcherThread thread = new DispatcherThread(result, i , f);
			thread.start();
			}

		return result.getEndResult();
	}
	
	public static void main(String[] args) {
		//Arrays.toString sonst wird kein String ausgegeben
		System.out.println(Arrays.toString(execute(new SquareCalc(), 11)));

	}

}
