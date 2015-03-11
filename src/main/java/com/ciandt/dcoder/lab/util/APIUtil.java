package com.ciandt.dcoder.lab.util;

import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Utility class with helper methods to deal with APIs
 * 
 * @author <a href="mailto:viveiros@ciandt.com">Daniel Viveiros</a>
 */
public class APIUtil {
    
    /** Property file: d-coder-lab.properties */
    private static ResourceBundle properties;
    private static String BASE_URI;
    
    static {
        properties = ResourceBundle.getBundle( "d-coder-lab");
        BASE_URI = properties.getString("base_uri");
    }
    
    /**
     * Creates the Builder (Jersey - java framework to invoke and create APIs) 
     */
    public static Builder createBuilder( String apiSpecificPath ) {
        return APIUtil.createBuilder(apiSpecificPath, null);
    }
    
    /**
     * Creates the Builder (Jersey - java framework to invoke and create APIs) 
     */
    public static Builder createBuilder( String apiSpecificPath, MultivaluedMap<String,String> queryParams ) {
        
        String apiPath = BASE_URI + apiSpecificPath;
        System.out.println( "Creating builder for API path = " + apiPath );
        
        Client client = Client.create();
        client.setReadTimeout(0); //infinity
        client.setFollowRedirects(false);
        WebResource webResource = client.resource( apiPath );
        if (queryParams != null) {
            webResource.queryParams( queryParams );
        }
        
        Builder builder = webResource.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_XML).type(
                MediaType.APPLICATION_JSON);
        String clientId = properties.getString("client_id");
        String apiKey = properties.getString("api_key");
        builder.header("CLIENT_ID", clientId);
        builder.header("API_KEY", apiKey);
        
        return builder;
    }
    
    /**
     * Creates the Builder (Jersey - java framework to invoke and create APIs) 
     */
    public static Builder createBuilderForPojo(String apiSpecificPath) {
        String apiPath = BASE_URI + apiSpecificPath;
        System.out.println( "Creating builder for API path = " + apiPath );
        
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(
                JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        
        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(apiPath);
        Builder builder = webResource.accept("application/json").type("application/json");
        String clientId = properties.getString("client_id");
        String apiKey = properties.getString("api_key");
        builder.header("CLIENT_ID", clientId);
        builder.header("API_KEY", apiKey);
        
        return builder;
    }

}
