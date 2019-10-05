package br.com.siecola.aws_lambda02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

public class SnsEventJob implements RequestHandler<SNSEvent, String> {
    @Override
    public String handleRequest(SNSEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        for(SNSEvent.SNSRecord snsRecord : input.getRecords()) {
            logger.log("SNS message: " + snsRecord.getSNS().getMessage());
        }
        return null;
    }
}

