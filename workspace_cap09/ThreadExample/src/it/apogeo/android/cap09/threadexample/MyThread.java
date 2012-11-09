package it.apogeo.android.cap09.threadexample;

/**
 * @author MASSIMO
 *
 */
public class MyThread extends Thread {
	
	private boolean running = false;
	
	public MyThread(){
		super("MyThread");
	}
	
	public void start(){
		running = true;
		super.start();
	}

	@Override
	public void run() {
		while(running){
			// CORPO DEL THREAD			
		}
	}
	
	public void stopThread(){
		running = false;
	}

}
