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
		String xmlgen = convert(bodyEle.getAsString());
		return new Response().withBody(xmlgen);
	}
	
	public static String convert(String json) throws JSONException
    {
        JSONObject jsonFileObject = new JSONObject(json);

        String xml = "{ \"genXML\":\"<?xml version=\'1.0\' encoding=\'UTF-8\' ?>"
        				+ org.json.XML.toString(jsonFileObject) 
        				+ "\"}";
        return xml;
    }

}
