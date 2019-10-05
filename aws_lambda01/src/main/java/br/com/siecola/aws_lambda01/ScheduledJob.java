package br.com.siecola.aws_lambda01;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;

public class ScheduledJob implements RequestHandler<ScheduledEvent, String> {
    @Override
    public String handleRequest(ScheduledEvent input, Context context) {
        LambdaLogger logger = context.getLogger();

        logger.log("Event source: " + input.getSource());

        return null;
    }
}
