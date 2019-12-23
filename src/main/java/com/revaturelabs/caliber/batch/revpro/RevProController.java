package com.revaturelabs.caliber.batch.revpro;

import com.revaturelabs.caliber.batch.revpro.dto.AssociateDto;
import com.revaturelabs.caliber.batch.revpro.dto.BatchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Controller("/sync")
@ResponseBody
public class RevProController {

  private static Logger log = LoggerFactory.getLogger(RevProController.class);

  @Autowired
  private RevProService revProService;

  @PostMapping("/sync")
  public void sync() {
    revProService.sync();
//    String data = revProClient.getCreds("{\n" +
//      "  \"password\" : \"127.0.0.1Base\",\n" +
//      "  \"userName\" : \"quintin001.donnelly@revature001.com\"\n" +
//      "}").getData();
//    List<AssociateDto> associates = revProClient.getAssociatesByBatchId("a190P000009zjakQAA", data).getData().getBatchTrainees();
//    List<BatchDto> batches = revProClient.getBatchInTimeRange(null, null,null, null, data).getData();
//    for (AssociateDto associate: associates) {
//      log.debug("associates {}", kv("associate", associate));
//    }
//    for (BatchDto batch: batches) {
//      log.debug("batches {}", kv("batch", batches));
//    }
  }


}
