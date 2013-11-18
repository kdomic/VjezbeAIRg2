package hr.foi.air.g2.db;

import hr.foi.air.g2.vjezbe.types.PoiInfo;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PoisAdapter {

	private static String DATABASE_NAME = "air.db";
	private static int DATABASE_VERSION = 1;
	private static String TABLE = "pois";
	private static String KEY = "id";

	private DBHelper dbHelper;
	private SQLiteDatabase db;

	public PoisAdapter(Context c) {
		dbHelper = new DBHelper(c, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void openToRead() {
		db = dbHelper.getReadableDatabase();
	}

	private void openToWrite() {
		db = dbHelper.getWritableDatabase();
	}

	private void close() {
		dbHelper.close();
	}

	/**
	 * Insert new poi into database
	 * 
	 * @param poi
	 *            Information about POI structured into PoiInfo type.
	 * @return -1 error or inserted id
	 */
	public long insertPoi(PoiInfo poi) {
		// id INTEGER PRIMARY KEY, name TEXT, description TEXT, latitude
		// NUMERIC, longitude NUMERIC
		ContentValues valuse = new ContentValues();
		valuse.put("name", poi.getName());
		valuse.put("description", poi.getDescription());
		valuse.put("latitude", poi.getLocation().getLatitude());
		valuse.put("longitude", poi.getLocation().getLongitude());

		openToWrite();
		long result = db.insert(TABLE, null, valuse);
		close();
		return result;
	}

	/**
	 * Gets List of all PoiInfo objects stored in local database
	 * 
	 * @return Information structured into List of PoiInfo objects,
	 */
	public List<PoiInfo> getAllPois() {
		List<PoiInfo> pois = new ArrayList<PoiInfo>();
		String[] columns = new String[] { KEY, "name", "description",
				"latitude", "longitude" };
		openToRead();
		Cursor c = db.query(TABLE, columns, null, null, null, null, null);
		for (c.moveToFirst(); !(c.isAfterLast()); c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("name"));
			String desc = c.getString(c.getColumnIndex("description"));
			Double lat = c.getDouble(c.getColumnIndex("latitude"));
			Double lng = c.getDouble(c.getColumnIndex("longitude"));
			int id = c.getInt(c.getColumnIndex(KEY));
			PoiInfo poi = new PoiInfo(name, desc, new LatLng(lat, lng));
			poi.setId(id);
			pois.add(poi);
		}
		close();
		return pois;
	}

}
