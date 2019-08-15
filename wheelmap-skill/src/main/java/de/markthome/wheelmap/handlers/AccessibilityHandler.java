/**
 * 
 */
package de.markthome.wheelmap.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import de.markthome.wheelmap.model.here.HereGeocodingResponse;
import de.markthome.wheelmap.service.HereGeocodingService;
import de.markthome.wheelmap.service.WheelmapSearchService;
import de.markthome.wheelmap.utils.SkillUtils;

/**
 * @author markthome
 *
 */
public class AccessibilityHandler implements IntentRequestHandler {

	/**
	 * 
	 */
	public AccessibilityHandler() {
		// TODO Auto-generated constructor stub
	}

	 /* (non-Javadoc)
	 * @see com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler#canHandle(com.amazon.ask.dispatcher.request.handler.HandlerInput, com.amazon.ask.model.IntentRequest)
	 */
	@Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("AboutIntent");
    }

    /* (non-Javadoc)
     * @see com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler#handle(com.amazon.ask.dispatcher.request.handler.HandlerInput, com.amazon.ask.model.IntentRequest)
     */
    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        
    		// Lookup address to retrieve bounding box 
    		HereGeocodingResponse hereResponse = HereGeocodingService.getInstance().getGeocodingResponse(searchText);
    		
    		if(hereResponse.getResponse() == null || hereResponse.getResponse().getView().isEmpty()) {
    			return handlerInput.getResponseBuilder()
    	                .withSpeech(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.error.address-not-found"))
    	                .withReprompt(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.error.address-not-found"))
    	                .build();
    		}
    		
    		
    		// Lookup wheel map data
    		WheelmapSearchService.getInstance().getSearchResponse(hereResponse.getResponse().get);
    		
    	
    	
    		return handlerInput.getResponseBuilder()
                .withSpeech(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.info.about"))
                .build();
    }

}
