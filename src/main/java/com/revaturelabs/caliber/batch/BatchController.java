package com.revaturelabs.caliber.batch;

import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController("/batch")
public class BatchController {

  private static Logger log = LoggerFactory.getLogger(BatchController.class);

  @Autowired
  private BatchService batchService;

  @Autowired
  private BatchRepository batchRepository;

  @GetMapping
  public List<Batch> getAll() {
    return batchService.getAll();
  }

  @PostMapping
  public void create() {
    Batch b = new Batch("asdfasdf", "Batch NO", Instant.now(), Instant.now(), "Not security", "Vaction", "extra extended");
    batchRepository.save(b);
  }

}
