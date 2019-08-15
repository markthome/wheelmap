package de.markthome.wheelmap.utils;

import com.amazon.ask.exception.AskSdkException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;

/**
 * 
 * @author markthome
 *
 */
public class HttpUtils {

	/**
	 * 
	 */
    private static CloseableHttpClient httpClient;

    /**
     * 
     * @return
     */
    private static CloseableHttpClient getHttpClient() throws Exception {
        if (httpClient == null) {
//        	SSLContext sslContext = SSLContexts.custom()
//        		    .useTLS()
//        		    .build();
//
//        		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
//        		    sslContext,
//        		    new String[]{"TLSv1", "TLSv1.1", "TLSv1.2", "TLSv1.3"},   
//        		    null,
//        		    BrowserCompatHostnameVerifier.INSTANCE);
        	
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }

    /**
     */
    private static HttpGet buildGet(Map<String, String> requestParameters, String endpoint) {
        try {
            final URI uri = new URIBuilder(endpoint)
                    .addParameters(requestParameters.entrySet().stream()
                            .map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
                            .collect(Collectors.toList()))
                    .build();
            return new HttpGet(uri);
        } catch (URISyntaxException ex) {
            throw new AskSdkException("Unable to form a valid endpoint with request parameters", ex);
        }
    }
    
    /**
     * 
     * @param requestParameters
     * @param endpoint
     * @return
     * @throws IOException
     */
    public static CloseableHttpResponse doGet(Map<String, String> requestParameters, String endpoint) throws Exception {
        return getHttpClient().execute(buildGet(requestParameters, endpoint));
    }
}
