package com.example.admin.service;

import com.example.admin.model.Staff;
import com.example.admin.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaffService {

    @Autowired
    StaffRepository staffRepository;
    public Staff add(Staff staff) {
        return staffRepository.save(staff);
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);
    }

    public List<Staff> getAll() {
        Iterable<Staff> staff = staffRepository.findAll();
        List<Staff> staffList = new ArrayList<>();
        staff.forEach(staffList::add);
        return staffList;
    }
}
