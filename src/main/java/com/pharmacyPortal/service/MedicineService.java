package com.pharmacyPortal.service;

import com.pharmacyPortal.entity.Medicine;
import org.hibernate.id.IntegralDataTypeHolder;

import java.util.List;

public interface MedicineService {
    Medicine addMedicine(Medicine md);
    List<Medicine> getAllMedicine();
    Medicine updateMedicine(Medicine md, Integer mId);
    void deleteMedicineById(Integer id);
    Medicine findMedicineById(Integer id);

}
