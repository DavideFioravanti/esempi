package it.apogeo.android.cap05.gallerytest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.SpinnerAdapter;

public class GalleryTestActivity extends Activity {

	/*
	 * Chiavi per il SimpleAdapter
	 */
	private final static Integer[] IMAGE_IDS = {
		R.drawable.image_0,
		R.drawable.image_1,
		R.drawable.image_2,
		R.drawable.image_3,
		R.drawable.image_4,
		R.drawable.image_5
	};
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Definiamo un SimpleAdapter
        SpinnerAdapter adapter = new ImageSpinnerAdapter(this,IMAGE_IDS);
        // Otteniamo il riferimento alla Gallery
        Gallery gallery = (Gallery)findViewById(R.id.myGallery);
        // Associamo l'adapter alla Gallery
        gallery.setAdapter(adapter);
    }
	
	
}