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
        				+ "</test>\"}", jsonToXMLInvoker.convert("{test:test}"), "10 x 0 must be 0");

	}

}
