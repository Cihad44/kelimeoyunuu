package net.codecanyon.trimax.android.wordsearch.intro;


import net.codecanyon.trimax.android.wordsearch.R;
import net.codecanyon.trimax.android.wordsearch.game.GameActivity;
import net.codecanyon.trimax.android.wordsearch.info.InfoActivity;
import net.codecanyon.trimax.android.wordsearch.prefs.Constants;
import net.codecanyon.trimax.android.wordsearch.prefs.Settings;
import net.codecanyon.trimax.android.wordsearch.prefs.SettingsActivity;


import android.content.res.Configuration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

import at.markushi.ui.CircleButton;

public class IntroActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String langCode = Settings.getStringValue(this, getResources().getString(R.string.pref_key_language), null);

		if(langCode == null){
			Configuration config = getResources().getConfiguration();
			Locale locale = config.locale;
			langCode = locale.getLanguage();


			//Check if this language is within supported languages
			String[] builtInLanguages = getResources().getStringArray(R.array.language_codes);
			boolean found = false;

			for(String code : builtInLanguages){
				if(code.equals(langCode)){
					found = true;
					break;
				}
			}

			if(!found){//make en default
				langCode = Constants.DEFAULT_LANGUAGE;
			}

			Settings.saveStringValue(this, getResources().getString(R.string.pref_key_language), langCode);
		}


		setContentView(R.layout.activity_intro);

		CircleButton btn_info = (CircleButton) findViewById(R.id.btn_info);
		btn_info.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IntroActivity.this, InfoActivity.class);
				startActivity(intent);
			}
		});


		CircleButton btn_start_game = (CircleButton) findViewById(R.id.btn_start_game);
		btn_start_game.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IntroActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});


		CircleButton btn_settings = (CircleButton) findViewById(R.id.btn_settings);
		btn_settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IntroActivity.this, SettingsActivity.class);
				startActivity(intent);
			}
		});



		
		
	}


}
