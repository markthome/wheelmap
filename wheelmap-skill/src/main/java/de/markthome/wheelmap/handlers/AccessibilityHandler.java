/**
 * 
 */
package de.markthome.wheelmap.handlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.exception.AskSdkException;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.alexa.presentation.apl.RenderDocumentDirective;
import com.amazon.ask.request.RequestHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.markthome.wheelmap.model.here.Address;
import de.markthome.wheelmap.model.here.BottomRight;
import de.markthome.wheelmap.model.here.HereGeocodingResponse;
import de.markthome.wheelmap.model.here.TopLeft;
import de.markthome.wheelmap.model.here.View;
import de.markthome.wheelmap.model.wheelmap.Node;
import de.markthome.wheelmap.model.wheelmap.WheelmapSearchResponse;
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
	}

	 /* (non-Javadoc)
	 * @see com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler#canHandle(com.amazon.ask.dispatcher.request.handler.HandlerInput, com.amazon.ask.model.IntentRequest)
	 */
	@Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("AccessibilityIntent");
    }

    /* (non-Javadoc)
     * @see com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler#handle(com.amazon.ask.dispatcher.request.handler.HandlerInput, com.amazon.ask.model.IntentRequest)
     */
    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        
    		
    		try {
    			RequestHelper requestHelper = RequestHelper.forHandlerInput(handlerInput);
    			
    			
    			// Check for APL support on the user's device
    			boolean supportsApl = false;
    	        if (requestHelper.getSupportedInterfaces().getAlexaPresentationAPL() != null) {
    	        		supportsApl = true;
    	        }
      
    			// Use a helper method to get the slot value wrapped in an Optional.
		    Optional<String> street = requestHelper.getSlotValue("street");
		    Optional<String> city = requestHelper.getSlotValue("city");
		    Optional<String> country = requestHelper.getSlotValue("country");
		    Optional<String> housenumber = requestHelper.getSlotValue("housenumber");
		      
		      String searchText = street.orElse("") + " " + housenumber.orElse("") + " " + city.orElse("") + " " + country.orElse("");
		      
		      System.out.println("Search text is: " + searchText);
  	
				// Lookup address to retrieve bounding box 
				HereGeocodingResponse hereResponse = HereGeocodingService.getInstance().getGeocodingResponse(searchText);
				
				if(hereResponse.getResponse() == null || hereResponse.getResponse().getView().isEmpty()) {
					return handlerInput.getResponseBuilder()
							.withSpeech(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.error.address-not-found"))
				            .withReprompt(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.error.address-not-found"))
				            .build();
				}
				
				TopLeft hereTopLeft = null;
				BottomRight hereBottomRight = null;
				Address hereAddress = null;
				
				if(!hereResponse.getResponse().getView().isEmpty()) {
					View view = hereResponse.getResponse().getView().get(0);
					if(!view.getResult().isEmpty()) {
						hereTopLeft = view.getResult().get(0).getLocation().getMapView().getTopLeft();
						hereBottomRight = view.getResult().get(0).getLocation().getMapView().getBottomRight();
						hereAddress = view.getResult().get(0).getLocation().getAddress();
						
						System.out.println("Here top left is: " + hereTopLeft);
						System.out.println("Here bottom right is: " + hereBottomRight);
						System.out.println("Here address is: " + hereAddress);
					}
				}
				
				if(hereTopLeft != null && hereBottomRight != null) {
					// Lookup wheel map data
					WheelmapSearchResponse wheelmapSearchResponse = WheelmapSearchService.getInstance().getSearchResponse(hereTopLeft.getLongitude(), hereTopLeft.getLatitude(), hereBottomRight.getLongitude(), hereBottomRight.getLatitude(), hereAddress.getStreet());	
					Node wheelmapNode = WheelmapSearchService.getInstance().findWheelmapNode(hereAddress.getCity(), hereAddress.getStreet(), hereAddress.getHouseNumber(), wheelmapSearchResponse.getNodes());
					String hereAddressText = hereAddress.getStreet() + " " + hereAddress.getHouseNumber() + ", " + hereAddress.getCity();
					
					
					if(wheelmapNode != null) {
						if("yes".equals(wheelmapNode.getWheelchair())) {
							if(supportsApl) {
							return handlerInput.getResponseBuilder()
									.addDirective(createRenderDocumentDirectivy(handlerInput, hereAddressText, wheelmapNode))
									.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.accessibility.yes"), hereAddressText, Optional.ofNullable(wheelmapNode.getName()).orElse("")))
					                .build();
							} else {
								return handlerInput.getResponseBuilder()
									.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.accessibility.yes"), hereAddressText, Optional.ofNullable(wheelmapNode.getName()).orElse("")))
						            .build();
							}
						} else if("no".equals(wheelmapNode.getWheelchair())) {
							if(supportsApl) {
								return handlerInput.getResponseBuilder()
										.addDirective(createRenderDocumentDirectivy(handlerInput, hereAddressText, wheelmapNode))
										.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.accessibility.no"), hereAddressText, Optional.ofNullable(wheelmapNode.getName()).orElse("")))
					    	                .build();
							} else {
								return handlerInput.getResponseBuilder()
										.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.accessibility.no"), hereAddressText, Optional.ofNullable(wheelmapNode.getName()).orElse("")))
										.build();
							}
						} else if("limited".equals(wheelmapNode.getWheelchair())) {
							if(supportsApl) {
								return handlerInput.getResponseBuilder()
										.addDirective(createRenderDocumentDirectivy(handlerInput, hereAddressText, wheelmapNode))
										.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.accessibility.limited"), hereAddressText, Optional.ofNullable(wheelmapNode.getName()).orElse("")))
					    	                .build();
							} else {
								return handlerInput.getResponseBuilder()
										.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.accessibility.limited"), hereAddressText, Optional.ofNullable(wheelmapNode.getName()).orElse("")))
					    	                .build();
							}
						} else {
							if(supportsApl) {
								return handlerInput.getResponseBuilder()
										.addDirective(createRenderDocumentDirectivy(handlerInput, hereAddressText, wheelmapNode))
										.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.accessibility.unknown"), hereAddressText, Optional.ofNullable(wheelmapNode.getName()).orElse("")))
					    	                .build();
							} else {
								return handlerInput.getResponseBuilder()
										.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.accessibility.unknown"), hereAddressText, Optional.ofNullable(wheelmapNode.getName()).orElse("")))
					    	                .build();
							}
						}
						
						
					} else {
						return handlerInput.getResponseBuilder()
								.withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.accessibilityhandler.nonode"), searchText))
				                .build();
					}
				
				} else {
					return handlerInput.getResponseBuilder()
				            .withSpeech(String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("speech.error.address-not-found"), searchText))
				            .build();
				}
			} catch (Exception exception) {
				System.out.println(exception);
				throw exception;
			}
    }
    
    /**
     * 
     * @param searchText
     * @param wheelmapNode
     * @return
     */
    private RenderDocumentDirective createRenderDocumentDirectivy(HandlerInput handlerInput, String searchText, Node wheelmapNode) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			TypeReference<HashMap<String, Object>> deviceMapType = new TypeReference<HashMap<String, Object>>() {
			};

			Map<String, Object> document = mapper.readValue(new File("views/accessibilityIntentView.json"), deviceMapType);

			JsonNode dataSources = mapper.readTree(new File("views/accessibilityIntentViewData.json"));
			ObjectNode deviceTemplateTitle = (ObjectNode) dataSources.get("bodyTemplate1Data");
			deviceTemplateTitle.put("title", wheelmapNode.getName());
			ObjectNode deviceTemplateText = (ObjectNode) dataSources.get("bodyTemplate1Data").get("textContent").get("primaryText");
			
			ObjectNode deviceTemplateBackgroundImage = (ObjectNode) dataSources.get("bodyTemplate1Data").get("backgroundImage").get("sources").get(0);
			if("yes".equals(wheelmapNode.getWheelchair())) {
				deviceTemplateText.put("text", String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("apl.accessibilityhandler.accessibility.yes"), searchText));
				deviceTemplateBackgroundImage.put("url", "https://s3-eu-west-1.amazonaws.com/markthome-wheelmap/icon-wheelmap-green.png");
			} else if("no".equals(wheelmapNode.getWheelchair())) {
				deviceTemplateText.put("text", String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("apl.accessibilityhandler.accessibility.no"), searchText));
				deviceTemplateBackgroundImage.put("url", "https://s3-eu-west-1.amazonaws.com/markthome-wheelmap/icon-wheelmap-red.png");
			} else if("limited".equals(wheelmapNode.getWheelchair())) {
				deviceTemplateText.put("text", String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("apl.accessibilityhandler.accessibility.limited"), searchText));
				deviceTemplateBackgroundImage.put("url", "https://s3-eu-west-1.amazonaws.com/markthome-wheelmap/icon-wheelmap-orange.png");
			} else {
				deviceTemplateText.put("text", String.format(SkillUtils.getResourceBundle(handlerInput, "Messages").getString("apl.accessibilityhandler.accessibility.unknown"), searchText));
			}

			RenderDocumentDirective documentDirective = RenderDocumentDirective.builder().withDocument(document)
					.withDatasources(mapper.convertValue(dataSources, new TypeReference<Map<String, Object>>() {
					})).build();
			
			return documentDirective;
		} catch (IllegalArgumentException | IOException exception) {
			throw new AskSdkException("Unable to read or deserialize device accessibility intent view", exception);
		}
    }

}
