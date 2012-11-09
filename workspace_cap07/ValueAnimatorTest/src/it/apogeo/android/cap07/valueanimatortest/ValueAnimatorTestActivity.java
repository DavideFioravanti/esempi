package it.apogeo.android.cap07.valueanimatortest;

import it.massimocarli.andlib.utility.KeyboardUtils;
import it.massimocarli.andlib.utility.ToastUtil;

import java.util.concurrent.atomic.AtomicBoolean;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ValueAnimatorTestActivity extends Activity {
	
	/*
	 * Riferimento alle EditText
	 */
	private EditText startEditText;
	private EditText endEditText;
	private EditText durationEditText;
	
	/*
	 * TextView per la visualizzazione di output
	 */
	private TextView outputView;
	
	/*
	 * Utilizziamo questo flag per impedire l'esecuzione di piu' animazioni
	 * contemporaneamente
	 */
	private AtomicBoolean running = new AtomicBoolean(false);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Riferimenti alle EditText
        startEditText = (EditText)findViewById(R.id.start_value);
        endEditText = (EditText)findViewById(R.id.end_value);
        durationEditText = (EditText)findViewById(R.id.duration);
        // TextView dell'output
        outputView = (TextView)findViewById(R.id.output_value);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Otteniamo il riferimento al MenuInflater
    	MenuInflater menuInflater = getMenuInflater();
    	// Eseguiamo l'inflate del menu
    	menuInflater.inflate(R.menu.animation_actions, menu);
    	// Ritorniamo il valore di default per l'aggiunta di eventuali
    	// opzioni standard
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	startAnimation();
    	return super.onOptionsItemSelected(item);
    }
    
    /*
     * Metodo che incapsula la logica di gestione dell'animazione
     */
    private void startAnimation(){
    	// Se e' attiva un'altra animazione non facciamo nulla
    	if(running.get()){
    		return;
    	}
    	// Leggiamo i valori di input
    	Editable startValue = startEditText.getText();
    	Editable endValue = endEditText.getText();
    	Editable durationValue = durationEditText.getText();
    	if(TextUtils.isEmpty(startValue)){
    		ToastUtil.showShort(this, R.string.start_value_missing);
    		return;
    	}
    	if(TextUtils.isEmpty(endValue)){
    		ToastUtil.showShort(this, R.string.end_value_missing);
    		return;
    	}    	
    	if(TextUtils.isEmpty(durationValue)){
    		ToastUtil.showShort(this, R.string.duration_value_missing);
    		return;
    	}      	
    	// Li convertiamo in float
    	int startValueInt = Integer.parseInt(startValue.toString());
    	int endValueInt = Integer.parseInt(endValue.toString());
    	int durationValueInt = Integer.parseInt(durationValue.toString());
    	// Creiamo il ValueAnimator
    	ValueAnimator valueAnimator = ValueAnimator.ofInt(startValueInt,endValueInt);
    	// Registriamo il listener per la visualizzazione
    	valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// Aggiorniamo il valore della TextView centrale
				String value = animation.getAnimatedValue().toString();
				outputView.setText(value);
			}
		});
    	// Registriamo il listener sull'animazione in modo da riabilitare il flag
    	// nel caso di terminazione
    	valueAnimator.addListener(new AnimatorListener(){

			@Override
			public void onAnimationCancel(Animator animator) {
			}

			@Override
			public void onAnimationEnd(Animator animator) {
				// L'animazione e' finita per cui possiamo riabilitarne l'esecuzione
				running.set(false);
			}

			@Override
			public void onAnimationRepeat(Animator animator) {
			}

			@Override
			public void onAnimationStart(Animator animator) {
			}
    		
    	});
    	// Impostiamo la durata
    	valueAnimator.setDuration(durationValueInt);
    	// Avviamo l'animazione
    	valueAnimator.start();
    	// Nascondiamo la tastiera
    	KeyboardUtils.hideKeyboard(this, startEditText.getWindowToken());
    	// Modifichiamo il flag relativo alle animazioni
    	running.set(true);
    }
}