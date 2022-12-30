package com.example.phongpt176.services;

import com.example.phongpt176.models.Bills;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.dto.BillDto;
import java.util.ArrayList;

public interface IBillService {
  ResponseObject<ArrayList<Bills>> getHistoryByUser(Long userId);

  ResponseObject<ArrayList<Bills>> getAllBill();

  ResponseObject<Bills> createBill(BillDto billDto);

  ResponseObject<Bills> getBill(Long id);

  ResponseObject<Bills> updateBill(Long id, BillDto updateBillDto);

}
