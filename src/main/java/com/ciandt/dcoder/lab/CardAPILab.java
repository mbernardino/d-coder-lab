package com.ciandt.dcoder.lab;

import java.util.Date;

import com.ciandt.dcoder.lab.model.Card;
import com.ciandt.dcoder.lab.model.Person;
import com.ciandt.dcoder.lab.util.APIUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public class CardAPILab {
    
    /**
     * Create a new person inside Smart Canvas
     */
    public Card createCard( Long id, String title, String summary, String content ) {
        
        String apiSpecificPath = "/card/v2/cards";
        
        Card card = new Card();
        
        //Basic info
        card.setId(id);
        card.setAuthorDisplayName( "Daniel Viveiros" );
        card.setAuthorEmail( "viveiros@ciandt.com");
        card.setAuthorId(5639445604728832L);
        card.setAuthorImageURL("https://lh3.googleusercontent.com/-tr003KjHJYk/AAAAAAAAAAI/AAAAAAAAKQ4/4wSwmn3j46c/photo.jpg?sz=50");
        card.setAutoModerated(true);
        card.setCreatedAt(new Date());
        card.setIsFeatured(false);
        card.setMnemonic( "daniel-viveiros-test");
        card.setProviderUserId("118239183782204424177");
        card.setUpdated(null);
        card.setExpirationDate(null);
        card.setPublishingDate(null);
        card.setSecurityLevel(0);
        card.addCategory("teste");
        card.addCategory("d-coder");
        
        //i18n
        card.addLanguage("pt");
        card.addLanguage("us");
        card.addRegion("BR");
        card.addRegion("US");
        
        //Content info
        card.setTitle(title);
        card.setDescription(summary);
        card.setContent(content);
        card.setProviderContentId( "123" );
        card.setProviderContentURL( "http://danielviveiros.com");
        card.setProviderId( "TestProviderId" );
        card.setProviderUpdated( new Date() );
        card.setProviderPublished( new Date() );
        
        //Community
        //card.setCommunity("Community Test");
        //card.setCommunityDisplayName("Community Display Name Test");
        
        Builder builder = APIUtil.createBuilderForPojo(apiSpecificPath);
        ClientResponse response = builder.put(ClientResponse.class, card);

        System.out.println( "Create card response:");
        System.out.println( ">> Status = " + response.getStatus());
        System.out.println( ">> Object = " + response);
        
        return card;
    }

    
    /**
     * Execute the code
     * @param args Command line parameters
     */
    public static void main(String[] args) {
        CardAPILab cardAPILab = new CardAPILab();
        
        
        try {
            cardAPILab.createCard(123L, "This is the title", "This is the summary", "This is the <b>content</b>");
        } catch ( Exception exc ) {
            exc.printStackTrace();
            System.exit(-1);
        }

        System.exit(0);
    }
}