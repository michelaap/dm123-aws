package br.com.siecola.aws_project01.controller;

import br.com.siecola.aws_project01.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        log.info("Michel controller - name: {}", name);
        log.info("Is matilde? {}", testService.isUserMatilde(name));

        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping("/dogcolor/{name}")
    public ResponseEntity<?> dogColor(@PathVariable String name) {
        log.info("Dog color - name: {}", name);
        return ResponseEntity.ok("Always black!");
    }
}
