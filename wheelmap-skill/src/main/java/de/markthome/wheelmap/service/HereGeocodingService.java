/**
 * 
 */
package de.markthome.wheelmap.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.amazon.ask.exception.AskSdkException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.markthome.wheelmap.model.here.HereGeocodingResponse;
import de.markthome.wheelmap.utils.HttpUtils;

/**
 * @author markthome
 *
 */
public class HereGeocodingService {
	
	/**
	 * 
	 */
	private static final String GEOCODE_URL = "https://geocoder.api.here.com/6.2/geocode.json";
	
	/**
	 * 
	 */
	private static final String APP_ID = "6DGm90AZVyy952eD9G7z";
	
	/**
	 * 
	 */
	private static final String APP_CODE = "x2bvEcu4ECyL3dSsFwAoeQ";

	/**
	 * 
	 */
	private static HereGeocodingService cInstance;
	
	/**
	 * 
	 */
	private HereGeocodingService() {
	}
	
	/**
	 * 
	 * @return
	 */
	public static HereGeocodingService getInstance() {
		if(cInstance == null) {
			cInstance = new HereGeocodingService();
		}
		return cInstance;
	}
	
	/**
	 * 
	 * @param searchAddress
	 * @return
	 */
	public HereGeocodingResponse getGeocodingResponse(String searchText) {
		Map<String, String> requestParams = new HashMap<String, String>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
                put("app_id", APP_ID);
                put("app_code", APP_CODE);
                put("searchtext", searchText);
            }
        };
		
		try (CloseableHttpResponse response = HttpUtils.doGet(requestParams, GEOCODE_URL)) {
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			try {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(responseString, HereGeocodingResponse.class);
			} catch (Exception exception) {
				throw new AskSdkException("Could not parse here geolocation data response", exception);
			}
		} catch (Exception exception) {
            throw new AskSdkException("Could not fetch here gelocation data response", exception);
        }
	}

}
