package it.apogeo.android.cap09.asynctasktest;

import it.massimocarli.andlib.utility.LogUtil;
import it.massimocarli.andlib.utility.ToastUtil;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class AsyncTaskTestActivity extends Activity {
	
	/*
	 * Riferimento alla ProgressBar
	 */
	private ProgressBar progressBar;
	
	/*
	 * Riferimento alla ProgressBar che ruota
	 */
	private ProgressBar rotatingBar;	
	
	/*
	 * Riferimento al task
	 */
	private MyAsyncTask myAsyncTask;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Riferimento alle ProgressBar
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        rotatingBar = (ProgressBar)findViewById(R.id.rotatingBar);
        rotatingBar.setVisibility(View.GONE);
    }
    
    /**
     * Invocata in corrispondenza dell'avvio del Task
     * @param v Riferimento al Button
     */
    public void startTask(View v){
    	myAsyncTask = new MyAsyncTask();
    	myAsyncTask.execute("input_test");
    }
    
    /**
     * Invocata in corrispondenza dello stop del Task
     * @param v Riferimento al Button
     */    
    public void stopTask(View v){
    	myAsyncTask.cancel(true);
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	if(myAsyncTask!=null){
    		myAsyncTask.cancel(true);
    	}
    }
    
    /*
     * Implementazione di un AsyncTask in grado di eseguire una operazione in background
     * a partire da un parametro di tipo String producendo un risultato di tipo String e 
     * fornendo indicazioni di progress attraverso un intero
     */
    private class MyAsyncTask extends AsyncTask<String, Integer, String>{
    	
    	@Override
    	protected void onPreExecute() {
    		rotatingBar.setVisibility(View.VISIBLE);
    	}    	

    	/*
		@Override
		protected String doInBackground(String... params) {
			for(int i=0;i<100;i++){
				try{Thread.sleep(200);}catch(InterruptedException ie){}
				publishProgress(i);
			}
			return "risultato";
		}
		*/
		
		@Override
		protected String doInBackground(String... params) {
			for(int i=0;i<100;i++){
				if(isCancelled()){
					break;
				}				
				try{Thread.sleep(200);}catch(InterruptedException ie){}
				if(isCancelled()){
					break;
				}
				publishProgress(i);
				if(isCancelled()){
					break;
				}				
			}
			return "risultato";
		}		
		
		@Override
		protected void onPostExecute(String result) {
			rotatingBar.setVisibility(View.GONE);
			ToastUtil.showShort(AsyncTaskTestActivity.this, "Task ended! "+result);
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			progressBar.setProgress(values[0]);
		}
		
		@Override
		protected void onCancelled() {
			LogUtil.d(this, "onCancelled");
			rotatingBar.setVisibility(View.GONE);
		}
    	
    }
    
}