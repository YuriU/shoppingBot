package com.serverless;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import java.io.InputStream;
import java.io.OutputStream;
import com.amazonaws.services.lambda.runtime.Context;
import org.telegram.telegrambots.meta.api.objects.Update;


import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;

public class Handler implements RequestStreamHandler {

	private static final Logger LOG = Logger.getLogger(Handler.class);

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws java.io.IOException {
		Update update;
		try {
			String result = IOUtils.toString(input, StandardCharsets.UTF_8);
			System.out.println(result);
			update = MAPPER.readValue(result, Update.class);
		} catch (Exception e) {
			System.err.println("Failed to parse update: " + e);
			throw new RuntimeException("Failed to parse update!", e);
		}
	}
}
