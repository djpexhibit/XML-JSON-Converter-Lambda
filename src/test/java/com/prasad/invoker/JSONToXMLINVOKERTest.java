package com.prasad.invoker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class JSONToXMLINVOKERTest {
	
	@Test
	public void simpleJSONToXML(){
		JSONToXMLInvoker jsonToXMLInvoker = new JSONToXMLInvoker();
		
        assertEquals("{ \"genXML\":\"<?xml version=\'1.0\' encoding=\'UTF-8\' ?>"
        				+ "<test>"
        				+ "test" 
        				+ "</test>\"}", jsonToXMLInvoker.convert("{test:test}"), "Valid JSON with XML string should be generated");

	}

}
