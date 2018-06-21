package com.prasad;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.prasad.invoker.AbstractServiceInvoker;
import com.prasad.util.InvokerUtil;
import com.prasad.util.Request;
import com.prasad.util.Response;



public class LambdaFunctionHandler implements RequestStreamHandler {
	
	private static final Logger LOG = LogManager.getLogger(LambdaFunctionHandler.class);

    private static final InvokerUtil INVOKER_UTIL = new InvokerUtil();


	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		
		JsonParser parser = new JsonParser();
        JsonObject inputObj;
        
        try {
            inputObj = parser.parse(IOUtils.toString(input,"UTF-8")).getAsJsonObject();	
            
			final Request request = new Request(inputObj);
			LOG.info("Request: " + request.getJsonObject());
			
			final AbstractServiceInvoker invoker= INVOKER_UTIL.getInvoker(request);
			final Response response = invoker.invoke(request);

			response.send(output, context);


        } catch (Exception e) {
        	LOG.info(e.toString());
        }
		
		
        
        
	}

}
