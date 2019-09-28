package br.com.siecola.aws_project02.repository;

import br.com.siecola.aws_project02.model.ProductEventLog;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProductEventLogRepository
        extends CrudRepository<ProductEventLog, String> {
    List<ProductEventLog> findAllByUsername(String username);
}

