package it.apogeo.android.cap09.threadexample;
/**
 * @author MASSIMO
 *
 */
public class MyRunnableThread implements Runnable {
	
	private Thread thread;
	
	private boolean running;
	
	public void run() {
		while(running){
			// CORPO DEL THREAD			
		}
	}
	
	public void start(){
		if(!running){
			running = true;
			thread = new Thread(this,"MyRunnableThread");
			thread.start();
		}
	}
	
	public void stop(){
		if(running){
			running = false;
			thread = null;
		}
	}

}
