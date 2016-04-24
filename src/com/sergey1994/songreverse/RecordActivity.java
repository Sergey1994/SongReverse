package com.sergey1994.songreverse;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class RecordActivity extends Activity {

	 private Chronometer chron;
	 private ImageButton recButton;
	 private ImageButton imageButtonPlay;
	 private Button buttonNext;
	  boolean recording;
	  boolean recState = false;
	 Short []myArray;
	
	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record);
		imageButtonPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
		recButton = (ImageButton) findViewById(R.id.imageButtonRec);
		buttonNext = (Button) findViewById(R.id.buttonNext);
		chron = (Chronometer) findViewById(R.id.chronometer);		
		chron.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

			@Override
			public void onChronometerTick(Chronometer chron) {
				long elapsedMillis = SystemClock.elapsedRealtime() - chron.getBase();
			}
		});
		
		buttonNext.setEnabled(false);
		imageButtonPlay.setEnabled(false);
	}
	  
	


	public void onPlayClick(View v) {
		playRecord();
		imageButtonPlay.setBackgroundColor(Color.RED);
	}
	
	
	
	public void onStartClick(View v) {
		if (!recState) {
			chron.setBase(SystemClock.elapsedRealtime());
			chron.start();
			recButton.setBackgroundColor(Color.GREEN);
			Thread recordThread = new Thread(new Runnable(){

				@Override
				public void run() {
					recording = true;
					startRecord();	
				}	
			});
			recordThread.start();
		} else {
			
			chron.stop();
			recButton.setBackgroundColor(Color.GRAY);
			recording = false;
			buttonNext.setEnabled(true);
			imageButtonPlay.setEnabled(true);

		}
		
		recState = !recState;
	}
	
	public void goToChoiceActivity(View v) {
		Intent intent = new Intent(this, ChoiceActivity.class);
		startActivity(intent);
	}
	

	
	private void startRecord() {
		File file = new File(Environment.getExternalStorageDirectory(), "test.pcm");
		int sampleFreq = 16000;
		ArrayList<Short> currentAudio = new ArrayList<Short>();
		int minBufferSize = AudioRecord.getMinBufferSize(sampleFreq,
			AudioFormat.CHANNEL_CONFIGURATION_MONO,
			AudioFormat.ENCODING_PCM_16BIT);
		short[] audioData = new short[minBufferSize];
		
		AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
				sampleFreq,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT,
				minBufferSize);
		audioRecord.startRecording();
		
		while(recording){
			int numberOfShort = audioRecord.read(audioData, 0, minBufferSize);
			for(int i = 0; i < numberOfShort; i++) {
				currentAudio.add(audioData[i]);
			}
		}
		
		audioRecord.stop();
		
		myArray = currentAudio.toArray(new Short[currentAudio.size()]);
		for(int i = 0; i <= myArray.length/2; i++) {
			Short k = myArray[i];
			myArray[i] = myArray[myArray.length - i - 1];
			myArray[myArray.length - 1 - i] = k;
		}
		
		try{
			file.createNewFile();
			
			OutputStream os = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			DataOutputStream dos = new DataOutputStream(bos);
			for(int i = 0; i <myArray.length; i++) {
				dos.writeShort(myArray[i]);
				dos.flush();
			}
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void playRecord(){
		File file = new File(Environment.getExternalStorageDirectory(),"test.pcm");
		int shortSizeInBytes = Short.SIZE/Byte.SIZE;
		int bufferSizeInBytes = (int)(file.length()/shortSizeInBytes);
		short[] audioData = new short[bufferSizeInBytes];
		
		try{
			InputStream is = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(is);
			DataInputStream dis = new DataInputStream(bis);
			
			int i = 0;
			while(dis.available() > 0){
				audioData[i] = dis.readShort();
				i++;
			}
			dis.close();
			
			int sampleFreq = 16000;
			AudioTrack audioTrack = new AudioTrack(
					AudioManager.STREAM_MUSIC,
					sampleFreq,
					AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT,
					bufferSizeInBytes,
					AudioTrack.MODE_STREAM);
			
			audioTrack.play();
			audioTrack.write(audioData, 0, bufferSizeInBytes);
			
					
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
			 
}
