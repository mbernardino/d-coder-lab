package com.ciandt.dcoder.lab;

import java.util.ResourceBundle;

import javax.ws.rs.client.WebTarget;

import com.sun.jersey.api.client.Client;

/**
 * Class that tests Smart Canvas People API
 * @author Daniel Viveiros
 */
public class PeopleAPILab {
	
	/** Property file: d-coder-lab.properties */
	private static ResourceBundle properties;
	
	private WebTarget target;
	private static String BASE_URI;
	
	static {
		properties = ResourceBundle.getBundle( "d-coder-lab");
		BASE_URI = properties.getString("base_uri");
	}
	
	/**
	 * Lists all persons created inside Smart Canvas
	 */
	public String listPersons() {
		Client c = Client.create();
        target = c.target(BASE_URI + "/people/v2");
        String responseMsg = target.path("people").request().get(String.class);
        System.out.println( "List persons response = " + responseMsg);
        return responseMsg;
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
