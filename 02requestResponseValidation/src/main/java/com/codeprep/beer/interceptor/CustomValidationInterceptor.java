package com.codeprep.beer.interceptor;

import javax.xml.namespace.QName;

import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;

public class CustomValidationInterceptor extends PayloadValidatingInterceptor {

	@Override
	public QName getDetailElementName() {
		
		return new QName("http://codepreplabs.com", "error", "my");
	}
	
	
}
