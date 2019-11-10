package de.markthome.wheelmap.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import de.markthome.wheelmap.handlers.AccessibilityHandler;
import de.markthome.wheelmap.model.here.Address;
import de.markthome.wheelmap.model.here.BottomRight;
import de.markthome.wheelmap.model.here.HereGeocodingResponse;
import de.markthome.wheelmap.model.here.TopLeft;
import de.markthome.wheelmap.model.here.View;
import de.markthome.wheelmap.model.wheelmap.Node;
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
			WheelmapSearchResponse response = WheelmapSearchService.getInstance().getSearchResponse(13.3417453687,52.505599556059,13.434635631302,52.523390178425, "Krumme Straße");
			
			System.out.println(response.getNodes().get(0));
			
			assertTrue(!response.getNodes().isEmpty());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@org.junit.Test
	public void testHereWithWheelmapSearch() {
		try {
			HereGeocodingResponse hereResponse = HereGeocodingService.getInstance().getGeocodingResponse("Bruchstraße 25 Detmold");
			//HereGeocodingResponse hereResponse = HereGeocodingService.getInstance().getGeocodingResponse("Krumme Straße 48, Detmold");
			
			
			TopLeft hereTopLeft = null;
    			BottomRight hereBottomRight = null;
    			Address hereAddress = null;
    		
	    		if(!hereResponse.getResponse().getView().isEmpty()) {
	    			View view = hereResponse.getResponse().getView().get(0);
	    			if(!view.getResult().isEmpty()) {
	    				hereAddress = view.getResult().get(0).getLocation().getAddress();
	    				hereTopLeft = view.getResult().get(0).getLocation().getMapView().getTopLeft();
	    				hereBottomRight = view.getResult().get(0).getLocation().getMapView().getBottomRight();
	    				
	    				System.out.println("Here top left is: " + hereTopLeft);
	    				System.out.println("Here bottom right is: " + hereBottomRight);
	    				System.out.println("Here address is: " + hereAddress);
	    				
	    			}
	    		}
    		
    		if(hereTopLeft != null && hereBottomRight != null) {
    			// Lookup wheel map data
    			WheelmapSearchResponse wheelmapResponse = WheelmapSearchService.getInstance().getSearchResponse(hereTopLeft.getLongitude(), hereTopLeft.getLatitude(), hereBottomRight.getLongitude(), hereBottomRight.getLatitude(), hereAddress.getStreet());	
    			
    			Node node = WheelmapSearchService.getInstance().findWheelmapNode(hereAddress.getCity(), hereAddress.getStreet(), hereAddress.getHouseNumber(), wheelmapResponse.getNodes());
    			
    			System.out.println(node);
    		
    		}
			
			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
//	@org.junit.Test
//	public void testHandlerInput() {
//		HandlerInput.builder().withRequest(null).build().s
//		
//		AccessibilityHandler handler =  new AccessibilityHandler();
//		handler.handle(handlerInput)
//	}
//
}
