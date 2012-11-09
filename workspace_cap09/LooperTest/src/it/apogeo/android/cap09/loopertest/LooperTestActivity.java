package it.apogeo.android.cap09.loopertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LooperTestActivity extends Activity {
	// Tag del log
	private final static String LOG_TAG = "LooperTestActivity";
	/*
	 * Riferimento al Producer per il MainThread
	 */
	private MainProducer mainProducer;
	/*
	 * Riferimento al Producer per il WorkerThread
	 */
	private WorkerProducer workerProducer;
	/*
	 * Riferimento al Consumer
	 */
	private Consumer consumer;
	/*
	 * Riferimento alla TextView di output
	 */
	private TextView output;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView di output
		output = (TextView) findViewById(R.id.outputView);

	}

	public void startThread(View button) {
		if (consumer == null) {
			consumer = new Consumer();
			consumer.running = true;
			consumer.start();
		}
		if (mainProducer == null) {
			mainProducer = new MainProducer();
			mainProducer.running = true;
			mainProducer.start();
		}
		if (workerProducer == null) {
			workerProducer = new WorkerProducer();
			workerProducer.running = true;
			workerProducer.start();
		}
	}

	public void stopThread(View button) {
		if (mainProducer != null) {
			mainProducer.running = false;
			mainProducer = null;
		}
		if (workerProducer != null) {
			workerProducer.running = false;
			workerProducer = null;
		}
		if (consumer != null) {
			consumer.running = false;
			consumer = null;
		}
	}

	private final Handler mainHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			Log.i(LOG_TAG, "mainHandler-> " + msg.obj);
			output.setText("mainHandler-> " + msg.obj);

		}

	};

	private final class MainProducer extends Thread {

		public boolean running;

		public void run() {
			int i = 0;
			while (running) {
				// Creiamo la STRing
				String data = "Producer_" + i++;
				try {
					Thread.sleep(400);
				} catch (InterruptedException ie) {
				}
				Message msg = mainHandler.obtainMessage();
				msg.obj = data;
				mainHandler.sendMessage(msg);
			}

		}

	}

	private final class WorkerProducer extends Thread {

		public boolean running;

		public void run() {
			int i = 0;
			while (running) {
				if (consumer.consumerHandler == null) {
					Log.i(LOG_TAG, "Waiting for consumerHanlder");
					try {
						Thread.sleep(400);
					} catch (InterruptedException ie) {
					}
					continue;
				}
				// Creiamo la STRing
				String data = "Producer_" + i++;
				try {
					Thread.sleep(400);
				} catch (InterruptedException ie) {
				}
				if (consumer != null) {
					Message msg = consumer.consumerHandler.obtainMessage();
					msg.obj = data;
					consumer.consumerHandler.sendMessage(msg);
				}
			}

		}

	}

	private final class Consumer extends Thread {

		public boolean running;

		public Handler consumerHandler;

		public void run() {
			Looper.prepare();
			consumerHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					Log.i(LOG_TAG, "Consumer-> " + msg.obj);
				}
			};
			Looper.loop();

		}

	}

}