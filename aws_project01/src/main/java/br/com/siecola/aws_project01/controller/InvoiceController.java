package br.com.siecola.aws_project01.controller;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Value("${aws.s3.bucket.invoice.name}")
    private String bucketName;

    private AmazonS3 amazonS3;
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceController(AmazonS3 amazonS3,
                             InvoiceRepository invoiceRepository) {
        this.amazonS3 = amazonS3;
        this.invoiceRepository = invoiceRepository;
    }

}
