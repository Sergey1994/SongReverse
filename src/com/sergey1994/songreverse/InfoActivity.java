package com.sergey1994.songreverse;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		TextView text =(TextView) findViewById(R.id.text);
		text.setText("������ ����� ���������� ����� � �������������� � ������ (������������� �� ����� � ������)." +
				" ������ ����� �������� ������������� �������� ����� �� ����������, ��� ����� ������������. " +
				"����� ��� ������� ��������������� � ��� ��� ������������ ��������, �� �������� ������ ����� �������� ��������," +
				" ����� ����� ���� ����� ������ �������." +
				" � ������ ������� ����� ����� �������� ����� ����� �� ��������� ���������������," +
				" �� ������������ ��� ��� �� ����������. ");
	}
}
