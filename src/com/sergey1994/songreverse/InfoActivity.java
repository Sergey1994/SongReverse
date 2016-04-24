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
		text.setText("Первый игрок записывает песню и переворачивает её запись (воспроизводит от конца к началу)." +
				" Второй игрок пытается воспроизвести обратную песню по фрагментам, что также записывается. " +
				"Потом эта попытка воспроизводится в ещё раз перевернутом варианте, по которому второй игрок пытается отгадать," +
				" какая песня была спета первым игроком." +
				" В редких случаях игрок может отгадать песню сразу по обратному воспроизведению," +
				" не воспроизводя его сам по фрагментам. ");
	}
}
