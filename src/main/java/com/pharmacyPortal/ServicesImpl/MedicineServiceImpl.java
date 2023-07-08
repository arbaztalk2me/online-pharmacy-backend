package com.pharmacyPortal.ServicesImpl;

import com.pharmacyPortal.Exception.MedicineNotFoundException;
import com.pharmacyPortal.entity.Medicine;
import com.pharmacyPortal.repository.MedicineRepository;
import com.pharmacyPortal.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine addMedicine(Medicine md) {
        return this.medicineRepository.save(md);
    }

    @Override
    public List<Medicine> getAllMedicine() {
        return this.medicineRepository.findAll();
    }

    @Override
    public Medicine updateMedicine(Medicine md, Integer mId) {
        Medicine medicine = this.medicineRepository.findById(mId).orElseThrow(() -> new MedicineNotFoundException("Medicine not found with id " + mId));
        medicine.setMedicineName(md.getMedicineName());
        medicine.setQuantity(md.getQuantity());
        medicine.setMedicinePrice(md.getMedicinePrice());
        return this.medicineRepository.save(medicine);
    }

    @Override
    public void deleteMedicineById(Integer id) {
        Medicine medicine = this.medicineRepository.findById(id).orElseThrow(() -> new MedicineNotFoundException("Medicine not found with id " + id));
        this.medicineRepository.delete(medicine);
    }

    @Override
    public Medicine findMedicineById(Integer id) {
        return this.medicineRepository.findById(id).orElseThrow(() -> new MedicineNotFoundException("Medicine not found with id " + id));

    }
}
