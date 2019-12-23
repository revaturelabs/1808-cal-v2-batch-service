package com.revaturelabs.caliber.batch.revpro;

import com.revaturelabs.caliber.batch.revpro.dto.BatchDto;
import com.revaturelabs.caliber.batch.revpro.dto.RevProResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "revpro", url = "https://dev3.revature.com/caliber")
public interface RevProClient {
  @PostMapping(value = "/authentication/login", produces = "application/json", consumes = "application/json")
  Creds getCreds(@RequestBody String body);

  @GetMapping(value = "/secure/batch/{batchId}/associates", consumes = "application/json")
  RevProResponse<BatchDto> getAssociatesByBatchId(@PathVariable("batchId") String batchId, @RequestHeader("encryptedToken") String token);

  @GetMapping(value = "/secure/batches", consumes = "application/json")
  RevProResponse<List<BatchDto>> getBatchInTimeRange(@RequestParam("startDateAfer") String startDateAfter, @RequestParam(value = "startDateBefore", required = false) String startDateBefore, @RequestParam(value = "startDateAfer", required = false) String endDateAfter, @RequestParam(value = "endDateBefore", required = false) String endDateBefore, @RequestHeader(value = "encryptedToken", required = true) String token);

  class Creds {
    private String data;

    public String getData() {
      return data;
    }

    public void setData(String data) {
      this.data = data;
    }


  }

}
