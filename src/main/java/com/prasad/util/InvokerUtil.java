package com.prasad.util;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prasad.invoker.AbstractServiceInvoker;
import com.prasad.invoker.JSONToXMLInvoker;
import com.prasad.invoker.XMLToJSONInvoker;



public class InvokerUtil implements Serializable {
	
	private static final long serialVersionUID = 5981045686367086741L;
	private static final Logger LOG = LogManager.getLogger(InvokerUtil.class);

	private final AbstractServiceInvoker getJSONToXMLInvoker;
	private final AbstractServiceInvoker getXMLToJSONInvoker;


	public InvokerUtil(){

		getJSONToXMLInvoker=new JSONToXMLInvoker();
		getXMLToJSONInvoker=new XMLToJSONInvoker();
		
	}

	public  AbstractServiceInvoker getInvoker(Request request) throws Exception{	

		final String resource=(request.getJsonObject()!=null?
				request.getJsonObject().get("resource")!=null?request.getJsonObject().get("resource").getAsString():null:null);

		final String httpMethod=(request.getJsonObject()!=null?
				request.getJsonObject().get("httpMethod")!=null?request.getJsonObject().get("httpMethod").getAsString():null:null);

		if(StringUtils.isEmpty(resource) || StringUtils.isEmpty(httpMethod)){
			throw new  IllegalArgumentException(String.format("Missing mandatory attributes 'resources' %s 'operation' %s",resource,httpMethod));
		}

		if(resource.equals("/v1/json-to-xml")){

			if(httpMethod.equals("GET")){
				return null;
			}
			else if(httpMethod.equals("POST")){
				return getJSONToXMLInvoker;
			}		
		}
		else if(resource.equals("/v1/xml-to-json")){
			if(httpMethod.equals("GET")){
				return null;
			}	
			else if(httpMethod.equals("POST")){
				return getXMLToJSONInvoker;
			}			
		}
		throw new Exception(String.format("No invoker implemented for the 'resources' %s 'operation' %s",resource,httpMethod));
	}
}


