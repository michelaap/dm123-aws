package br.com.siecola.aws_lambda03;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class S3EventJob implements RequestHandler<S3Event, String> {
    @Override
    public String handleRequest(S3Event input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Starting execution...");
        for (S3EventNotification.S3EventNotificationRecord record :
                input.getRecords()) {
            String key = record.getS3().getObject().getKey();
            logger.log("Object key: " + key);
            try {
                String objectContent = downloadObject(record.getS3().getBucket()
                        .getName(), key);
                ObjectMapper objectMapper = new ObjectMapper();
                Invoice invoice = objectMapper.readValue(objectContent,
                        Invoice.class);
                saveInvoice(invoice);
                logger.log("Invoice saved");
            } catch (IOException e) {
                logger.log("Failed to download object - " + e.getMessage());
            }
        }
        return null;
    }


    private void saveInvoice(Invoice invoice) {
        final AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(
                new EnvironmentVariableCredentialsProvider());
        ddbClient.withRegion(Regions.EU_CENTRAL_1);
        DynamoDB dynamoDB = new DynamoDB(ddbClient);
        Table table = dynamoDB.getTable("invoice-lambda");
        table.putItem(new PutItemSpec().withItem(new Item()
                .withString("id", UUID.randomUUID().toString())
                .withString("invoiceNumber", invoice.getInvoiceNumber())
                .withString("customerName", invoice.getCustomerName())
                .withFloat("totalValue", invoice.getTotalValue())
                .withLong("productId", invoice.getProductId())
                .withInt("quantity", invoice.getQuantity())));
    }

    private String downloadObject(String bucketName, String objectKey)
            throws IOException {
        final AmazonS3Client amazonS3Client = new AmazonS3Client(
                new EnvironmentVariableCredentialsProvider());
        amazonS3Client.withRegion(Regions.EU_CENTRAL_1);
        S3Object s3Object = amazonS3Client.getObject(bucketName, objectKey);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(s3Object.getObjectContent()));
        String content = null;
        while ((content = bufferedReader.readLine()) != null) {
            stringBuilder.append(content);
        }
        return stringBuilder.toString();
    }

}
