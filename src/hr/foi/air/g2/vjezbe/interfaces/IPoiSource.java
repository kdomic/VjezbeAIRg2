package hr.foi.air.g2.vjezbe.interfaces;

import hr.foi.air.g2.vjezbe.types.PoiInfo;

import java.util.List;

/*
 * Describes the connection to plugins getting information on POI
 * @author
 * 
 * 
 * */

public interface IPoiSource {
	public List<PoiInfo> getPois(int groupId);

	public String getName();

	public String geVersion();
}
