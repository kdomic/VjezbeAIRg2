package hr.foi.air.g2.vjezbe;

import hr.foi.air.g2.db.PoisAdapter;
import hr.foi.air.g2.vjezbe.types.PoiInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DbActivity extends Activity {

	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acrivity_db);
		context = this;
		showData();
	}
	
	private void showData()
	{
		PoisAdapter poisAdapter = new PoisAdapter(this);
		List<PoiInfo> pois = poisAdapter.getAllPois();
		//Log.d("AIR", "Size: " + pois.size());
		
		final ListView lv = (ListView) findViewById(R.id.local_data);
		
		HashMap<String, String> map;
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		
		Iterator<PoiInfo> itr = pois.iterator();
		while(itr.hasNext())
		{
			PoiInfo poi = itr.next();
			map = new HashMap<String, String>();
			map.put("id", ""+poi.getId());
			map.put("name", poi.getName());
			map.put("desc", poi.getDescription());
			
			data.add(map);
		}
		
		String[] from = new String[]{"id", "name", "desc"};
		int[] to = new int[]{R.id.poi_id_item,R.id.poi_name_item,R.id.poi_desc_item};
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item_local_data, from, to);
		
		lv.invalidateViews();
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Toast.makeText(context, ""+lv.getItemAtPosition(position), Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
}
