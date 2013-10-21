package hr.foi.air.g2.vjezbe;

import hr.foi.air.g2.vjezbe.interfaces.IPoiSource;
import hr.foi.air.g2.vjezbe.plugins.PoiSourceWebSource;
import hr.foi.air.g2.vjezbe.types.PoiInfo;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		IPoiSource poiSource = new PoiSourceWebSource();	
		
		List<PoiInfo> pois = poiSource.getPois(2);
		
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.mapFragment);
		GoogleMap map = fm.getMap();
		int n = pois.size();
		for (int i = 0; i < n; i++) {
			PoiInfo poi = pois.get(i);
			MarkerOptions markerOptions = new MarkerOptions();
			LatLng pos = new LatLng(poi.getLocation().getLatitude(), poi
					.getLocation().getLongitude());
			markerOptions.position(pos);
			markerOptions.title(poi.getName());
			markerOptions.snippet(poi.getDescription());
			markerOptions.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.map_icon));
			map.addMarker(markerOptions);
		}
	}

	// private void showPois()

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
			break;
		}
		return true;
	}

}
