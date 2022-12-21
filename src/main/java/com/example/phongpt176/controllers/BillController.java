package com.example.phongpt176.controllers;

import com.example.phongpt176.models.Bills;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.dto.BillDto;
import com.example.phongpt176.services.impl.BillService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/bill")
public class BillController {

  @Autowired
  private BillService billService;

  @GetMapping()
  public ResponseEntity<ResponseObject<ArrayList<Bills>>> getHistoryByUser(
      @RequestParam(name = "userId") String userId) {
    return ResponseEntity.ok().body(billService.getHistoryByUser(Long.parseLong(userId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject<Bills>> getBill(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(billService.getBill(id));
  }

  @PostMapping()
  public ResponseEntity<ResponseObject<Bills>> createBill(@RequestBody BillDto bill) {
    return ResponseEntity.ok().body(billService.createBill(bill));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ResponseObject<Bills>> updateBill(@PathVariable("id") Long id ,@RequestBody BillDto bill) {
    return ResponseEntity.ok().body(billService.updateBill(id, bill));
  }
}
