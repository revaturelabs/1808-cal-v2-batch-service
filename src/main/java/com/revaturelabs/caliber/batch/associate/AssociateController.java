package com.revaturelabs.caliber.batch.associate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
