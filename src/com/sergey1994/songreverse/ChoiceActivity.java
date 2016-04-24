package com.sergey1994.songreverse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ChoiceActivity extends Activity {
	
	private ImageButton buttonBack;
	private ImageButton buttonPlayRec;
	boolean clicked = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice);
		buttonBack = (ImageButton)findViewById(R.id.imageButtonBack);
		buttonPlayRec = (ImageButton) findViewById(R.id.imageButtonPlayRec);
		
		buttonBack.setOnClickListener(buttonBack_click);
		buttonPlayRec.setOnClickListener(buttonPlayRec_click);			
	}
	
	public OnClickListener buttonBack_click = new OnClickListener() {
		@Override
		public void onClick(View v) {
			goToRecord(v);
		}
	};
	
	public OnClickListener buttonPlayRec_click = new OnClickListener() {
		@Override
		public void onClick(View v) {
			buttonPlayRec.setBackgroundColor(Color.RED);
//			if (!clicked) {
//				buttonPlayRec.setImageResource(R.drawable.pause);
//			} else {
//				buttonPlayRec.setImageResource(R.drawable.play);
//			}
//				clicked = !clicked;	
		}
	};
	
	public void goToRecord(View v) {
		Intent intent = new Intent(this, RecordActivity.class);
		startActivity(intent);
	}	
}
