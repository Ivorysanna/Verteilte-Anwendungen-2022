package a1_2_Dispatcher;

public class DispatcherThread extends Thread{
	Result threadResult;
	int threadIndex;
	F f;
	
	public void run() {
		threadResult.setSingleResult(threadIndex, f.f(threadIndex));
	}
	
	public DispatcherThread(Result result, int n, F f) {
		threadResult = result;
		threadIndex = n;
		this.f = f;
	}
}
