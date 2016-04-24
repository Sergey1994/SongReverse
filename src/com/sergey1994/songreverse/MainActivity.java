package com.sergey1994.songreverse;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
		final Button buttonInfo = (Button) findViewById(R.id.buttonInfo);
		final Button buttonStat = (Button) findViewById(R.id.buttonStat);
		buttonPlay.setOnClickListener(buttonPlay_click);
		buttonInfo.setOnClickListener(buttonInfo_click);
	}
	
	public OnClickListener buttonPlay_click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			goToRecord(v);
		}
	};
	
	public OnClickListener buttonInfo_click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			goToStat(v);
			
		}
	};
	
	
	
	public void goToRecord(View v) {
		Intent intent = new Intent(this, RecordActivity.class);
		
		startActivity(intent);
		
	}
	
	public void goToStat(View v) {
		Intent intent = new Intent(this, InfoActivity.class);
		startActivity(intent);
	}
	

	
	
}
