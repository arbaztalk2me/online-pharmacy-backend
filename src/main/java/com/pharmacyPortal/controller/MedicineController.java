package com.pharmacyPortal.controller;

import com.pharmacyPortal.entity.Medicine;
import com.pharmacyPortal.payloads.ApiResponse;
import com.pharmacyPortal.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/addMedicine")
    ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine){
        return new ResponseEntity<>(this.medicineService.addMedicine(medicine), HttpStatus.CREATED);
    }

    @PutMapping("/updateMedicine/{id}")
    ResponseEntity<Medicine>updateMedicine(@RequestBody Medicine medicine,@PathVariable Integer id){
        return new ResponseEntity<>(this.medicineService.updateMedicine(medicine,id), HttpStatus.OK);
    }

    @GetMapping("/getMedicineById/{id}")
    ResponseEntity<Medicine>getMedicineById(@PathVariable Integer id){
        return new ResponseEntity<>(this.medicineService.findMedicineById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllMedicine")
    ResponseEntity<List<Medicine>>getAllMedicine(){
        return new ResponseEntity<>(this.medicineService.getAllMedicine(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteMedicine/{id}")
    ResponseEntity<ApiResponse>deleteMedicineByIds(@PathVariable Integer id){
        this.medicineService.deleteMedicineById(id);
        return new ResponseEntity<>(new ApiResponse("Medicine Deleted Successfully with id "+id,true), HttpStatus.OK);
    }

}
