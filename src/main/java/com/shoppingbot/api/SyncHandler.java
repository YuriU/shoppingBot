package com.shoppingbot.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.Map;

public class SyncHandler
		extends BotHandler
		implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private static final Logger LOG = Logger.getLogger(SyncHandler.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		if (input.containsKey("body")) {
			LOG.info("receivedType: " + input.get("body").getClass());
			LOG.info("received: " + input.get("body"));
			try {
				Update update = MAPPER.readValue((String) input.get("body"), org.telegram.telegrambots.meta.api.objects.Update.class);
				getBot().onWebhookUpdateReceived(update);
			} catch (com.fasterxml.jackson.core.JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}

		Response responseBody = new Response("Go Serverless v1.x! Your function executed successfully!", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}
}
