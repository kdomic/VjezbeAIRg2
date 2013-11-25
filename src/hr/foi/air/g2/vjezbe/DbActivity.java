package hr.foi.air.g2.vjezbe;

import hr.foi.air.g2.db.PoisAdapter;
import hr.foi.air.g2.vjezbe.types.PoiInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DbActivity extends Activity {

	Context context;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acrivity_db);
		context = this;
		showData();
	}

	private void showData() {
		PoisAdapter poisAdapter = new PoisAdapter(this);
		List<PoiInfo> pois = poisAdapter.getAllPois();
		// Log.d("AIR", "Size: " + pois.size());

		lv = (ListView) findViewById(R.id.local_data);

		HashMap<String, String> map;
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

		Iterator<PoiInfo> itr = pois.iterator();
		while (itr.hasNext()) {
			PoiInfo poi = itr.next();
			map = new HashMap<String, String>();
			map.put("id", "" + poi.getId());
			map.put("name", poi.getName());
			map.put("desc", poi.getDescription());

			data.add(map);
		}

		String[] from = new String[] { "id", "name", "desc" };
		int[] to = new int[] { R.id.poi_id_item, R.id.poi_name_item,
				R.id.poi_desc_item };
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.item_local_data, from, to);

		lv.invalidateViews();
		lv.setAdapter(adapter);

		registerForContextMenu(lv);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.setHeaderTitle(R.string.db_context_menu_header);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.db_context_menu, menu);
		// super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		final View listElement = info.targetView;
		final int id = Integer.parseInt(((TextView) listElement
				.findViewById(R.id.poi_id_item)).getText().toString());
		TextView txtName = (TextView) listElement
				.findViewById(R.id.poi_name_item);
		TextView txtDesc = (TextView) listElement
				.findViewById(R.id.poi_desc_item);

		String oldName = txtName.getText().toString();
		String oldDesc = txtDesc.getText().toString();

		switch (item.getItemId()) {
		case R.id.db_contex_menu_delete:
			/*
			AlertDialog.Builder adb = new AlertDialog.Builder(context);
			adb.setView(lv);
			adb.setTitle("Da li ste sigurni?");
			adb.setIcon(android.R.drawable.ic_dialog_alert);
			adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {*/
					PoisAdapter poiAdapter = new PoisAdapter(context);
					poiAdapter.deletePoi(id);
					Toast.makeText(context, "POI Deleted", Toast.LENGTH_LONG)
							.show();
					showData();
				/*}
			});
			adb.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							finish();
						}
					});
			adb.show();*/

			break;
		case R.id.db_contex_menu_update:
			final Dialog myDialog = new Dialog(context);
			myDialog.setContentView(R.layout.dialog_add_poi);
			myDialog.setTitle(R.string.poi_update);
			myDialog.setCancelable(true);
			myDialog.show();
			final EditText name = (EditText) myDialog
					.findViewById(R.id.txtName);
			final EditText desc = (EditText) myDialog
					.findViewById(R.id.txtDescription);
			name.setText(oldName);
			desc.setText(oldDesc);
			Button btnAdd = (Button) myDialog.findViewById(R.id.btnAdd);
			Button btnCancel = (Button) myDialog.findViewById(R.id.btnCancel);

			btnAdd.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					PoisAdapter poiAdapter = new PoisAdapter(context);
					String newName = name.getText().toString();
					String newDesc = desc.getText().toString();
					poiAdapter.updatePoi(id, newName, newDesc);
					Toast.makeText(context, "POI Updated", Toast.LENGTH_LONG)
							.show();
					myDialog.dismiss();
					showData();
				}
			});
			btnCancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					myDialog.dismiss();
				}
			});
			break;
		default:
			break;
		}

		return true;
		// return super.onContextItemSelected(item);
	}

}
