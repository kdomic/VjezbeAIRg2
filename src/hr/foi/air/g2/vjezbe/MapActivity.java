package hr.foi.air.g2.vjezbe;

import hr.foi.air.g2.vjezbe.core.MySupportMapFragment;
import hr.foi.air.g2.vjezbe.core.OnMyTouchListener;
import hr.foi.air.g2.vjezbe.interfaces.IPoiSource;
import hr.foi.air.g2.vjezbe.plugins.PoiSourceWebSource;
import hr.foi.air.g2.vjezbe.types.PoiInfo;

import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMyTouchListener {

	private long startTime;
	private long stopTime;
	private long WAIT_TIME = 1500;
	private LatLng clickedPoint;
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		IPoiSource poiSource = new PoiSourceWebSource();

		List<PoiInfo> pois = poiSource.getPois(1);

		MySupportMapFragment fm = (MySupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.mapFragment);
		map = fm.getMap();

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

	private Handler handler = new Handler();

	private Runnable task = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Dialog myDialog = new Dialog(getApplicationContext());
			myDialog.setContentView(R.layout.dialog_add_poi);
			myDialog.setTitle(R.string.poi_add);
			myDialog.setCancelable(true);
			myDialog.show();
		}
	};

	@Override
	public void onMyTouchListener(MotionEvent event) {
		// TODO Auto-generated method stub
		Point p = new Point((int) event.getX(), (int) event.getY());
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startTime = event.getEventTime();
			clickedPoint = map.getProjection().fromScreenLocation(p);
			handler.postDelayed(task, WAIT_TIME);
			break;
		case MotionEvent.ACTION_UP:
			stopTime = event.getEventTime();
			if (stopTime - startTime < WAIT_TIME) {
				handler.removeCallbacks(task);
			}
		case MotionEvent.ACTION_MOVE:
			stopTime = event.getEventTime();
			startTime = event.getEventTime();
			clickedPoint = map.getProjection().fromScreenLocation(p);
			handler.postDelayed(task, WAIT_TIME);
			break;
		default:
			break;
		}

	}

}
