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
		LOG.info(json);
        JSONObject jsonFileObject = new JSONObject(json);
        LOG.info(jsonFileObject);
        String xml = "{ \"te\":\"<?xml version=\'1.0\' encoding=\'UTF-8\' ?>"
        				+ "<p:DCTRequest xmlns:p='http://www.dhl.com' xmlns:p1='http://www.dhl.com/datatypes' xmlns:p2='http://www.dhl.com/DCTRequestdatatypes' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.dhl.com DCT-req.xsd '>"
        				+ org.json.XML.toString(jsonFileObject) 
        				+ "</p:DCTRequest>\"}";
        return xml;
    }

}
