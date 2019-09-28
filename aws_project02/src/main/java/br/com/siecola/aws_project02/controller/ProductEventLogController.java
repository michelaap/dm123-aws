package br.com.siecola.aws_project02.controller;

import br.com.siecola.aws_project02.model.ProductEventLog;
import br.com.siecola.aws_project02.repository.ProductEventLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductEventLogController {
    private static final Logger log = LoggerFactory.getLogger(
            ProductEventLogController.class);

    private ProductEventLogRepository productEventLogRepository;

    @Autowired
    public ProductEventLogController(
            ProductEventLogRepository productEventLogRepository) {
        this.productEventLogRepository = productEventLogRepository;
    }

    @GetMapping("/events")
    public Iterable<ProductEventLog> getAllEvents() {
        return productEventLogRepository.findAll();
    }

    @GetMapping("/events/{username}")
    public List<ProductEventLog> findById(@PathVariable String username) {
        return productEventLogRepository.findAllByUsername(username);
    }

}


