package com.ciandt.dcoder.lab;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.ciandt.dcoder.lab.model.Person;
import com.ciandt.dcoder.lab.model.Profile;
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
    public Person createPerson( Long id, String name, String email ) {
        
        String apiSpecificPath = "/people/v2/people";
        
        Person person = new Person();
        person.setId( id );
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
        
        return person;
    }
    
    /**
     * Create a new profile inside Smart Canvas
     */
    public Profile createProfile( Long id, Person person ) {
        
        String apiSpecificPath = "/people/v2/profiles";
        
        Profile profile = new Profile();
        profile.setId( 456456456456456L );
        profile.setPersonId( person.getId() );
        profile.setBirthdate( person.getBirthdate() );
        profile.setBraggingRights( "I'll win this competition");
        profile.setCoach( "My coach");
        profile.setCoverURL("https://lh6.googleusercontent.com/-xT1MINh2gDM/U4KfNDofBDI/AAAAAAAAJIU/Q15YoAlGqq0/s931-fcrop64=1,00002599e494d11f/IMG_2798.jpg");
        profile.setDisplayName(person.getDisplayName());
        profile.setEmail(person.getEmail());
        profile.setEmployerName(person.getCompany());
        profile.setGender(person.getGender());
        profile.setImageURL("https://lh4.googleusercontent.com/-ylL83Hn8XJ8/VHXg9M4Q-XI/AAAAAAAAKJs/NPJU7dS1KUQ/s895-no/FotoPerfil_Square.jpg");
        profile.setIntroduction("My introduction");
        profile.setJobTitle(person.getPosition());
        profile.setLastUpdated(person.getLastUpdate());
        profile.setLocale(person.getLocale());
        profile.setManager("My manager");
        profile.setMaritalStatus(person.getMaritalStatus());
        profile.setPosition(person.getPosition());
        profile.setProfileURL("https://plus.google.com/118239183782204424177");
        profile.setProviderId("GooglePlus");
        profile.setProviderUserId("118239183782204424177");
        profile.setTagLine("Relevance, Continuously");
        profile.setUsername("test");
        
        Builder builder = APIUtil.createBuilderForPojo(apiSpecificPath);
        ClientResponse response = builder.put(ClientResponse.class, profile);

        System.out.println( "Create profile response:");
        System.out.println( ">> Status = " + response.getStatus());
        System.out.println( ">> Object = " + response);
        
        return profile;
    }
    
    /**
     * Lists all profiles inside Smart Canvas
     */
    public void addRoleToProfile( Long profileId, String roleName ) {
        
        Profile profile = new Profile();
        
        //creates the builder
        String apiSpecificPath = "/people/v2/profiles/" + profileId + "/addrole/" + roleName;
        Builder builder = APIUtil.createBuilderForPojo(apiSpecificPath); 
        
        //invoke the API
        ClientResponse response = builder.put(ClientResponse.class, profile);
        System.out.println( "Add role " + roleName + " to profile " +  profileId + " = " + response.getStatus());
        
        //return response;
    }
    
    /**
     * Lists all profiles by providerid and roles
     */
    public String findProfileByRoleNameAndProviderId( String providerId, String roleName ) {
        //creates the builder
        String apiSpecificPath = "/people/v2/profiles/provider/" + providerId + "/role/" + roleName;
        Builder builder = APIUtil.createBuilder(apiSpecificPath); 
        
        //invoke the API
        String response = builder.get(String.class);
        System.out.println( "Find profiles by provider and role response = " + response);
        
        return response;
    }

	/**
	 * Execute the code
	 * @param args Command line parameters
	 */
	public static void main(String[] args) {
		PeopleAPILab peopleAPILab = new PeopleAPILab();
		
		try {
		    Person person = peopleAPILab.createPerson( 123123123123123123L, "Test", "test@ciandt.com");
		    Profile profile = peopleAPILab.createProfile(456456456456L, person);
		    peopleAPILab.addRoleToProfile(profile.getId(), "MODERATOR");
		    peopleAPILab.addRoleToProfile(profile.getId(), "PUBLISHER");
			peopleAPILab.listPersons();
			peopleAPILab.searchPersonByEmail("test@ciandt.com");
			peopleAPILab.findProfileByRoleNameAndProviderId("GooglePlus", "PUBLISHER");
		} catch ( Exception exc ) {
			exc.printStackTrace();
			System.exit(-1);
		}

		System.exit(0);
	}

}
