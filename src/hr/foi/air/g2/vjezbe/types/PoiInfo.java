package hr.foi.air.g2.vjezbe.types;

import com.google.android.gms.maps.model.LatLng;

public class PoiInfo {
	private String name;
	private String description;
	private GeoPoint location;
	private int id;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public GeoPoint getLocation() {
		return location;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public PoiInfo(String name, String description, LatLng location) {
		this.name = name;
		this.description = description;
		this.location = new GeoPoint(location.latitude, location.longitude);
	}
}
