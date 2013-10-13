package hr.foi.air.g2.vjezbe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MapActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.map, menu);
		return true;
	}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		switch (item.getItemId()) {
			case R.id.optMapRefresh:
				Toast.makeText(this, "Ovo je refresh", Toast.LENGTH_SHORT).show();
				break;		
			case R.id.optMapSettings:
				Intent i = new Intent(this, SettingsActivity.class);
				startActivity(i);
				//Toast.makeText(this, "Ovo je settings", Toast.LENGTH_SHORT).show();
				break;
		}	
		return true;
	}
	
}
