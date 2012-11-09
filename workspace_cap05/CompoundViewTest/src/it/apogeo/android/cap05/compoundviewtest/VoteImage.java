package it.apogeo.android.cap05.compoundviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;


/**
 * @author Utente
 *
 */
public class VoteImage extends FrameLayout {

	public VoteImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// Leggiamo i parametri associati al componente. Otteniamo quindi
		// il riferimento ai parametri
		TypedArray attrValues = context.obtainStyledAttributes(attrs,R.styleable.VoteImage);
		// Eseguiamo l'inflate del layout associato al componente
		View root = LayoutInflater.from(context).inflate(R.layout.vote_image_layout, this, false);
		// Leggiamo i valori relativi all'immagine inizializzandone le proprietà
		ImageView imageView = (ImageView)root.findViewById(R.id.backgroundView);
		Integer imageId = attrValues.getResourceId(R.styleable.VoteImage_imageSrc, -1);
		if(imageId<0){
			throw new IllegalStateException("Image mandatory!");
		}
		imageView.setImageResource(imageId);
		// Seguiamo lo stesso preocedimento per le label dei pulsanti. Se non presenti
		// il pulsante viene nascosto senza influire sul layout
		Button button = (Button)root.findViewById(R.id.yesButton);
		CharSequence label = attrValues.getText(R.styleable.VoteImage_yesLabel);
		if(label==null){
			button.setVisibility(View.GONE);
		}
		button.setText(label);
		button = (Button)root.findViewById(R.id.noButton);
		label = attrValues.getText(R.styleable.VoteImage_noLabel);
		if(label==null){
			button.setVisibility(View.GONE);
		}
		button.setText(label);
		button = (Button)root.findViewById(R.id.maybeButton);
		label = attrValues.getText(R.styleable.VoteImage_maybeLabel);
		if(label==null){
			button.setVisibility(View.GONE);
		}
		button.setText(label);		
		// Sistemiamo gli attributi
		attrValues.recycle();
		// Aggiungiamo la root al layout corrente
		addView(root);
	}

}
