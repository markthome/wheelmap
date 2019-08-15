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
	public WheelmapSearchResponse getSearchResponse(String boundingBox) {
		Map<String, String> requestParams = new HashMap<String, String>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
                put("api_key", API_KEY);
                put("bbox", boundingBox);
            }
        };
		
        try (CloseableHttpResponse response = HttpUtils.doGet(requestParams, SEARCH_URL)) {
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			try {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(responseString, WheelmapSearchResponse.class);
			} catch (Exception exception) {
				throw new AskSdkException("Could not parse wheelmap data response", exception);
			}
		} catch (Exception exception) {
            throw new AskSdkException("Could not fetch wheelmap data response", exception);
        }
	}

}
