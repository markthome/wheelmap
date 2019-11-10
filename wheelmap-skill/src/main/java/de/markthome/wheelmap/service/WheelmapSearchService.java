/**
 * 
 */
package de.markthome.wheelmap.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.amazon.ask.exception.AskSdkException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.markthome.wheelmap.model.wheelmap.Node;
import de.markthome.wheelmap.model.wheelmap.WheelmapSearchResponse;
import de.markthome.wheelmap.utils.HttpUtils;

/**
 * @author markthome
 *
 */
public class WheelmapSearchService {
	
	/**
	 * 
	 */
	private static final String SEARCH_URL = "https://wheelmap.org/api/nodes";
	
	/**
	 * 
	 */
	private static final String API_KEY = "HLow-gDe8oSVE29ZpC9Q";
	
	/**
	 * 
	 */
	private static WheelmapSearchService cInstance;
	
	/**
	 * 
	 */
	private WheelmapSearchService() {
	}
	
	/**
	 * 
	 * @return
	 */
	public static WheelmapSearchService getInstance() {
		if(cInstance == null) {
			cInstance = new WheelmapSearchService();
		}
		return cInstance;
	}
	
	/**
	 * 
	 * @param searchAddress
	 * @return
	 */
	public WheelmapSearchResponse getSearchResponse(double topLeftLongitude, double topLeftLatitude, double bottomRightLongitude, double bottomRightLatitude, String street) {
		Map<String, String> requestParams = new HashMap<String, String>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
                put("api_key", API_KEY);
                put("bbox", String.join(",", String.valueOf(topLeftLongitude), String.valueOf(topLeftLatitude), String.valueOf(bottomRightLongitude), String.valueOf(bottomRightLatitude)));
                put("q", street + "*");
			}
        };
		
        try (CloseableHttpResponse response = HttpUtils.doGet(requestParams, SEARCH_URL)) {
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			try {
				ObjectMapper mapper = new ObjectMapper();
				WheelmapSearchResponse wheelmapSearchResponse = mapper.readValue(responseString, WheelmapSearchResponse.class);
				
				System.out.println("WheelmapSearchResponse is: " + wheelmapSearchResponse);
				
				return wheelmapSearchResponse;
			} catch (Exception exception) {
				throw new AskSdkException("Could not parse wheelmap data response", exception);
			}
		} catch (Exception exception) {
            throw new AskSdkException("Could not fetch wheelmap data response", exception);
        }
	}
	
	/**
	 * 
	 * @param city
	 * @param street
	 * @param housenumber
	 * @return
	 */
	public Node findWheelmapNode(String city, String street, String housenumber, List<Node> nodes) {
		System.out.println("Find wheelmap node with " + city + " " + street + " " + housenumber + " ");
		
		Node bestMatchNode = null;
		Node fallbackMatchNode = null;
		
		for(Node node : nodes) {
			//System.out.println("Checking node city: " + node.getCity());
			
			if(node.getCity() != null && city.equalsIgnoreCase(node.getCity().toString())) {
				//System.out.println("Checking node street: " + node.getStreet());
				
				if(node.getStreet() != null && street.equalsIgnoreCase(node.getStreet().toString())) {
					//System.out.println("Checking node housenumber: " + node.getHousenumber());
					
					fallbackMatchNode = node;
					
					if(node.getHousenumber() != null) {
						
						String[] housenumbers = node.getHousenumber().toString().split("-");
						
						for(String splitHousenumber : housenumbers) {
							if(splitHousenumber.equals(housenumber)) {
								System.out.println("Best match node: " + node);
								bestMatchNode = node;
							}
						}
					}
				}
			}
		}
		
		if(bestMatchNode != null) {
			return bestMatchNode;
		} else {
			return fallbackMatchNode;
		}
	}

}
