package com.prasad.invoker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.prasad.util.Request;
import com.prasad.util.Response;



public class JSONToXMLInvoker extends AbstractServiceInvoker{
	private static final Logger LOG = LogManager.getLogger(JSONToXMLInvoker.class);

	@Override
	public Response invoke(Request request) throws Exception {
		JsonElement bodyEle = request.getJsonObject().get("body");
		String xmlgen = convert(bodyEle.getAsString(), "root");
		return new Response().withBody(xmlgen);
	}
	
	public static String convert(String json, String root) throws JSONException
    {
        JSONObject jsonFileObject = new JSONObject(json);

        String xml = "{ \"genXML\":\"<?xml version=\'1.0\' encoding=\'UTF-8\' ?>"
        				+ "<"+root + " "
        				+ "<xmlns:p='http://www.test.com' xmlns:p1='http://www.test.com/datatypes' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.test.com DCT-req.xsd '>"
        				+ org.json.XML.toString(jsonFileObject) 
        				+ "</+ root + >\"}";
        return xml;
    }

}
