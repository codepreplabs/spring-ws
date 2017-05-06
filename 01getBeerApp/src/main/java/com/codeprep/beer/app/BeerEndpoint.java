package com.codeprep.beer.app;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.codeprep.beer.Beer;
import com.codeprep.beer.GetBeerRequest;
import com.codeprep.beer.GetBeerResponse;

@Endpoint
public class BeerEndpoint {
	
	public static final String NAMESPACE_URI = "http://codeprep.com/beer";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBeerRequest")
	@ResponsePayload
	public GetBeerResponse getBeer(@RequestPayload GetBeerRequest request){
		
		GetBeerResponse response = new GetBeerResponse();
		Beer beer = new Beer();
		beer.setId(request.getId());
		beer.setName("Budwiser");
		response.setBeer(beer);
		return response;
	}
}
