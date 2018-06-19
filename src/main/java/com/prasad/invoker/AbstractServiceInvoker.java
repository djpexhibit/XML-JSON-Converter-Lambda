package com.prasad.invoker;

import com.prasad.util.Request;
import com.prasad.util.Response;

public abstract class AbstractServiceInvoker {
	
	private static final long serialVersionUID = -1549741813093183142L;

	public abstract Response invoke(final Request request) throws Exception;

}
