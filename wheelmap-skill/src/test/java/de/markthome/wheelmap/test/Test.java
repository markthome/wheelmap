package de.markthome.wheelmap.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.markthome.wheelmap.model.here.HereGeocodingResponse;
import de.markthome.wheelmap.model.wheelmap.WheelmapSearchResponse;
import de.markthome.wheelmap.service.HereGeocodingService;
import de.markthome.wheelmap.service.WheelmapSearchService;

public class Test {

	@org.junit.Test
	public void testHereGeocoding() {
		try {
			HereGeocodingResponse response = HereGeocodingService.getInstance().getGeocodingResponse("Bremker Str. 107 32758 Detmold Deutschland");
			assertTrue(response.getResponse().getView().get(0).getResult().get(0).getLocation().getAddress().getCounty().equals("Lippe"));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@org.junit.Test
	public void testWheelmapSearch() {
		try {
			WheelmapSearchResponse response = WheelmapSearchService.getInstance().getSearchResponse("13.3417453687,52.505599556059,13.434635631302,52.523390178425");
			
			System.out.println(response.getNodes().get(0));
			
			assertTrue(!response.getNodes().isEmpty());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}

}
