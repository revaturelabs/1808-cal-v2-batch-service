package com.revaturelabs.caliber.batch.associate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("associate")
public class AssociateController {
  @Autowired
  AssociateService associateService;

  @GetMapping
  List<Associate> getAll() {
    return associateService.getAll();
  }

  @PostMapping
  Associate create(@RequestBody Associate associate) { return associateService.create(associate);}
}
