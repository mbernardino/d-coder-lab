package com.ciandt.dcoder.lab;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.ciandt.dcoder.lab.model.Person;
import com.ciandt.dcoder.lab.util.APIUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

/**
 * Class that tests Smart Canvas People API
 * @author Daniel Viveiros
 */
public class PeopleAPILab {
	
	/**
	 * Lists all persons created inside Smart Canvas
	 */
	public String listPersons() {
	    
	    //creates the builder
	    String apiSpecificPath = "/people/v2/people";
		Builder builder = APIUtil.createBuilder(apiSpecificPath); 
		
		//invoke the API
        String response = builder.get(String.class);
		System.out.println( "List persons response = " + response);
        
        return response;
	}
	
	/**
     * Search for Smart Canvas persons
     */
    public String searchPersonByEmail(String email) {
        
        String apiSpecificPath = "/people/v2/people/search";
        
        //Parameter
        if ( !StringUtils.isEmpty(email) ) {
            apiSpecificPath += "?email=" + email;
        } 
        
        //creates the builder
        Builder builder = APIUtil.createBuilder(apiSpecificPath);
        
        //invoke the API
        String response = builder.get(String.class);
        System.out.println( "Search person response = " + response);
        
        return response;
    }
    
    /**
     * Create a new person inside Smart Canvas
     */
    public void createPerson( String name, String email ) {
        
        String apiSpecificPath = "/people/v2/people";
        
        Person person = new Person();
        person.setDisplayName(name);
        person.setEmail(email);
        person.setActive(true);
        person.setBirthdate( new Date() );
        person.setCompany( "CI&T" );
        person.setGender( "Male" );
        person.setLastUpdate( new Date() );
        person.setLocale( "pt-BR");
        person.setMaritalStatus( "Married" );
        person.setPosition( "Developer" );
        person.setType( "USER" );
        
        Builder builder = APIUtil.createBuilderForPojo(apiSpecificPath);
        ClientResponse response = builder.put(ClientResponse.class, person);

        System.out.println( "Create person response:");
        System.out.println( ">> Status = " + response.getStatus());
        System.out.println( ">> Object = " + response);
        
    }

	/**
	 * Execute the code
	 * @param args Command line parameters
	 */
	public static void main(String[] args) {
		PeopleAPILab peopleAPILab = new PeopleAPILab();
		
		try {
		    peopleAPILab.createPerson("Test", "test@ciandt.com");
			peopleAPILab.listPersons();
			peopleAPILab.searchPersonByEmail("test@ciandt.com");
		} catch ( Exception exc ) {
			exc.printStackTrace();
			System.exit(-1);
		}

		System.exit(0);
	}

}
