package com.revaturelabs.caliber.batch;

import com.revaturelabs.caliber.batch.category.CategoryClient;
import com.revaturelabs.caliber.batch.revpro.RevProClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController("/batch")
public class BatchController {

    private static Logger log = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    CategoryClient categoryClient;
    @Autowired
    RevProClient revProClient;

    @Autowired
    private BatchService batchService;

    @Autowired
    private BatchRepository batchRepository;

    @GetMapping("/batch")
    public List<Batch> getAll() {
        return batchService.getAll();
    }

    @PostMapping("/batch")
    public void create() {
        Batch b = new Batch("qq", "Batch NO", LocalDate.now(), LocalDate.now(), "Not security", "Vaction", "extra extended");
        batchRepository.save(b);
    }

}
