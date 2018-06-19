package com.prasad.invoker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.prasad.util.Request;
import com.prasad.util.Response;



public class XMLToJSONInvoker extends AbstractServiceInvoker {
	
    private static final Gson gson =  new GsonBuilder().create();
	private static final Logger LOG = LogManager.getLogger(XMLToJSONInvoker.class);



	@Override
	public Response invoke(Request request) throws Exception {
		JsonElement bodyEle = request.getJsonObject().get("body");
		JsonParser parser = new JsonParser();
		String bodyStr = bodyEle.getAsString();
		LOG.info(bodyStr);
		JsonObject bodyObj = parser.parse(bodyEle.getAsString()).getAsJsonObject();
		JsonElement xmlEle = bodyObj.get("xml");
		
		LOG.info(xmlEle);
		
		String xmlMsg = gson.fromJson(xmlEle.toString(), String.class);
		JSONObject xmlJsonObj = XML.toJSONObject(xmlMsg);
		
		LOG.info(xmlJsonObj);
		return new Response().withBody(xmlJsonObj);
	}

}
