package hr.foi.air.g2.vjezbe.core;

import hr.foi.air.g2.vjezbe.types.PoiInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

public class ServiceAsyncTask extends AsyncTask<Integer, Void, List<PoiInfo>> {

	@Override
	protected List<PoiInfo> doInBackground(Integer... params) {
		Integer groupId = (Integer)params[0];

		List<PoiInfo> pois = new ArrayList<PoiInfo>();

		String result = null;

		HttpClient httpClient = new DefaultHttpClient();
		//HttpGet request = new HttpGet("http://arka.foi.hr/~kdomic/ws_"+groupId+".json");
		HttpGet request = new HttpGet("http://analizafoi.co.nf/pois.php?method=getPois&groupId="+groupId);

		ResponseHandler<String> handler = new BasicResponseHandler();

		try {
			result = httpClient.execute(request, handler);
			if (result != null) {
				int length = new JSONArray(result).length();
				for (int i = 0; i < length; i++) {
					JSONObject jpoi = new JSONArray(result).getJSONObject(i);
					PoiInfo poi = new PoiInfo(jpoi.getString("name"),
							jpoi.getString("name"), new LatLng(
									jpoi.getDouble("latitude"),
									jpoi.getDouble("longitude")));
					pois.add(poi);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		httpClient.getConnectionManager().shutdown();
		return pois;
	}

}
