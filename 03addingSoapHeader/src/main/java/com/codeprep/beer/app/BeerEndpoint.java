package com.codeprep.beer.app;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import com.codeprep.beer.Authentication;
import com.codeprep.beer.Beer;
import com.codeprep.beer.GetBeerRequest;
import com.codeprep.beer.GetBeerResponse;

@Endpoint
public class BeerEndpoint {
	
	public static final String NAMESPACE_URI = "http://codeprep.com/beer";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBeerRequest")
	@ResponsePayload
	public GetBeerResponse getBeer(@RequestPayload GetBeerRequest request,  @SoapHeader("{" + Authentication.AUTH_NS + "}authentication") SoapHeaderElement auth){
		
		
		Authentication authentication = getAuthentication(auth);
		System.out.println("username = "+authentication.getUsername());
		GetBeerResponse response = new GetBeerResponse();
		Beer beer = new Beer();
		beer.setId(request.getId());
		beer.setName("Budwiser");
		response.setBeer(beer);
		return response;
	}
	
	private Authentication getAuthentication(SoapHeaderElement header){
        Authentication authentication = null;
        try {

            JAXBContext context = JAXBContext.newInstance(Authentication.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            authentication = (Authentication) unmarshaller.unmarshal(header.getSource());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return authentication;
    }
}
