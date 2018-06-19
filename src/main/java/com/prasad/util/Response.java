package com.prasad.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Response {
	
	private static final Logger LOG = LogManager.getLogger(Response.class);

    private static final Gson gson = new GsonBuilder().create();

    private int statusCode;
    private String body;
    private Map<String, String> headers = new HashMap<>();


    public Response() {
        this.body = " ";
        this.statusCode = 200;

        this.headers.put("Content-Type","application/json");
        this.headers.put("Access-Control-Allow-Headers","Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,x-guid");
        this.headers.put("Access-Control-Allow-Methods", "*");
        this.headers.put("Access-Control-Allow-Origin", "*");
    }


    public Response withHeader(String name, String content) {
        this.headers.put(name, content);
        return this;
    }

    public Response withBody(Object body) {
    	LOG.info(body);
        this.body = gson.toJson(body);
        LOG.info(this.body);
        return this;
    }
    
    public Response withBody(JSONObject jsonObject){
    	LOG.info(jsonObject);
    	this.body = jsonObject.toString();
    	return this;
    }

//    public Response withResult(Result<?> result) {
//        if (result.getError().isPresent()) {
//            withError(result.getError().get());
//        } else {
//            withBody(result.get());
//        }
//        return this;
//    }

    public Response withBody(Object body, Gson customGson) {
        if (customGson == null) throw new IllegalStateException("Custom gson can not be null");
        this.body = customGson.toJson(body);
        return this;
    }
    
    public Response withBody(String body){
    	this.body = body;
    	return this;
    }

    public Response withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

//    public Response withError(Error error) {
//        this.body = gson.toJson(error);
//        this.statusCode = error.getCode();
//        return this;
//    }

    public String toJSON() {
    	LOG.info(this);
        return gson.toJson(this);
    }

    public void send(OutputStream out, Context context) throws IOException{
    	LOG.info(this.toJSON());
        try (OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8")) {
            writer.write(this.toJSON());
        }catch (Exception e) {
            //probably a malformed json
            LOG.error(e.getMessage(), e);
            try (OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8")) {
               // Error error = new Error("Unexpected error",Error.Reason.INTERNAL_SERVER_ERROR, context);
                //writer.write(new Response().withError(error).toJSON());
            }catch (Exception e2) {
                LOG.error(e2.getMessage(), e2);
            }
        }
    }
    
    public void sendRaw(OutputStream out, Context context) throws IOException{
    	try(OutputStreamWriter writer = new OutputStreamWriter(out,"UTF-8")){
    		writer.write(this.getBody().toString());
    	} catch (Exception e){
    		LOG.error(e.getMessage(),e);
    	}
    } 


    public int getStatusCode() {
        return statusCode;
    }

    void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }



    public Map<String, String> getHeaders() {
        return headers;
    }

    void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Object getBody() {
        return body;
    }

    void setBody(String body) {
        this.body = body;
    }
}
