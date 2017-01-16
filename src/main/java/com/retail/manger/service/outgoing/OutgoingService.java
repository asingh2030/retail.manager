package com.retail.manger.service.outgoing;

import java.util.Map;

public interface OutgoingService {
	
	public Map<String, Object> getResponse(Object... args) throws Exception;

}
