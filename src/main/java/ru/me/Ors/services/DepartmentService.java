package ru.me.Ors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.me.Ors.models.Department;
import ru.me.Ors.repo.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findOne(int id) {
        Optional<Department> foundDepartment = departmentRepository.findById(id);
        return foundDepartment.orElse(null);
    }

    @Transactional
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public Department update(int id, Department updatedDepartment) {
        updatedDepartment.setId(id);
        return departmentRepository.save(updatedDepartment);
    }

    @Transactional
    public void delete(int id) {
        departmentRepository.deleteById(id);
    }
}
