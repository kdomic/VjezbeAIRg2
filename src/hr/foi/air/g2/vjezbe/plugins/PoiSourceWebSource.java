package hr.foi.air.g2.vjezbe.plugins;

import hr.foi.air.g2.vjezbe.core.ServiceAsyncTask;
import hr.foi.air.g2.vjezbe.interfaces.IPoiSource;
import hr.foi.air.g2.vjezbe.types.PoiInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PoiSourceWebSource implements IPoiSource {

	@Override
	public List<PoiInfo> getPois(int groupId) {
		ServiceAsyncTask asyncTask = new ServiceAsyncTask();

		Integer[] params = new Integer[] { groupId };
		asyncTask.execute(params);

		List<PoiInfo> result = null;

		try {
			result = asyncTask.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String getName() {
		return "Web services poi loader";
	}

	@Override
	public String geVersion() {
		return "1.0";
	}

}
