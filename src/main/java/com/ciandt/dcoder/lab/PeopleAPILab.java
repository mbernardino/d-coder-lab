package com.ciandt.dcoder.lab;

import java.util.ResourceBundle;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

/**
 * Class that tests Smart Canvas People API
 * @author Daniel Viveiros
 */
public class PeopleAPILab {
	
	/** Property file: d-coder-lab.properties */
	private static ResourceBundle properties;
	
	private static String BASE_URI;
	
	static {
		properties = ResourceBundle.getBundle( "d-coder-lab");
		BASE_URI = properties.getString("base_uri");
	}
	
	/**
	 * Lists all persons created inside Smart Canvas
	 */
	public String listPersons() {
	    String apiPath = BASE_URI + "/people/v2/people";
	    System.out.println( "List persons API Path = " + apiPath );
	    
		Client client = Client.create();
		client.setReadTimeout(0); //infinity
		client.setFollowRedirects(false);
		WebResource webResource = client.resource( apiPath );
		Builder builder = webResource.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_XML).type(
                MediaType.APPLICATION_JSON);
		String clientId = properties.getString("client_id");
        String apiKey = properties.getString("api_key");
        builder.header("CLIENT_ID", clientId);
        builder.header("API_KEY", apiKey);
		
        String response = builder.get(String.class);
		System.out.println( "List persons response = " + response);
        
        return response;
	}

	/**
	 * Execute the code
	 * @param args Command line parameters
	 */
	public static void main(String[] args) {
		PeopleAPILab peopleAPILab = new PeopleAPILab();
		
		try {
			peopleAPILab.listPersons();
		} catch ( Exception exc ) {
			exc.printStackTrace();
			System.exit(-1);
		}

		System.exit(0);
	}

}
