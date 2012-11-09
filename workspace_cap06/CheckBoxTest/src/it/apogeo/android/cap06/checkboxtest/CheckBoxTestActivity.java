package it.apogeo.android.cap06.checkboxtest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableLayout;

public class CheckBoxTestActivity extends Activity {

	// Tag per il log
	private final static String CHECK_BOX_TEST_TAG = "CheckBoxTestActivity";

	// Componenti del colore
	private int red;
	private int green;
	private int blue;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al container
		final TableLayout container = (TableLayout) findViewById(R.id.container);
		// Otteniamo il riferimento alle CheckBox
		final CheckBox redBox = (CheckBox) findViewById(R.id.redBox);
		final CheckBox greenBox = (CheckBox) findViewById(R.id.greenBox);
		final CheckBox blueBox = (CheckBox) findViewById(R.id.blueBox);
		// Riferimento alle EditText
		final EditText redText = (EditText) findViewById(R.id.redValue);
		final EditText greenText = (EditText) findViewById(R.id.greenValue);
		final EditText blueText = (EditText) findViewById(R.id.blueValue);
		// Ci registriamo all'evento di selezione
		CompoundButton.OnCheckedChangeListener checkedListener = new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton button, boolean checked) {
				// Leggiamo il componente rosso se selezionata la relativa
				// proprietà
				if (button == redBox) {
					red = (redBox.isChecked()) ? getColorComponent(redText) : 0;
				} else if (button == greenBox) {
					green = (greenBox.isChecked()) ? getColorComponent(greenText)
							: 0;
				} else if (button == blueBox) {
					blue = (blueBox.isChecked()) ? getColorComponent(blueText)
							: 0;
				} else {
					Log.w(CHECK_BOX_TEST_TAG,
							"No checkBox selected. Check the code!");
				}
				// Lo applichiamo al container
				container.setBackgroundColor(Color.argb(0x88, red, green, blue));
			}

		};
		// Registriamo i listener ai checkbox
		redBox.setOnCheckedChangeListener(checkedListener);
		greenBox.setOnCheckedChangeListener(checkedListener);
		blueBox.setOnCheckedChangeListener(checkedListener);
	}

	private int getColorComponent(EditText inputText) {
		// Otteniamo il valore corrispondnete
		String value = inputText.getText().toString();
		try {
			int intValue = Integer.parseInt(value);
			if (intValue < 0) {
				intValue = 0;
				inputText.setText("" + intValue);
			} else if (intValue > 255) {
				intValue = 255;
				inputText.setText("" + intValue);
			}else{
				return intValue;
			}
			return intValue;
		} catch (NumberFormatException nfe) {
			Log.w(CHECK_BOX_TEST_TAG, "Unable to convert in number:" + value);
		}
		return 0;

	}

}