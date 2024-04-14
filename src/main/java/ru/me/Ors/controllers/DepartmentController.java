package ru.me.Ors.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.me.Ors.dto.DepartmentDTO;
import ru.me.Ors.models.Department;
import ru.me.Ors.models.Faculty;
import ru.me.Ors.services.DepartmentService;
import ru.me.Ors.services.FacultyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;
    private final FacultyService facultyService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper, FacultyService facultyService) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
        this.facultyService = facultyService;
    }

    @GetMapping("/all")
    public List<DepartmentDTO> getAll() {
        return departmentService.findAll().stream().map(this::convertToDepartmentDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartment(int id) {
        return convertToDepartmentDTO(departmentService.findOne(id));
    }

    @PostMapping("/add")
    public Department save(@RequestBody @Valid DepartmentDTO departmentDTO) {
        Faculty faculty = facultyService.findByName(departmentDTO.getFaculty_name())
                .orElseThrow(() -> new RuntimeException("Факульетет не найден"));

        Department department = convertToDepartment(departmentDTO);
        department.setFaculty(faculty);
        return departmentService.save(department);
    }

    @PutMapping()
    public ResponseEntity<Department> update(@RequestBody @Valid DepartmentDTO departmentDTO, int id) {
        Faculty faculty = facultyService.findByName(departmentDTO.getFaculty_name())
                .orElseThrow(() -> new RuntimeException("Факультет не найден"));

        Department updatedDepartmnet = convertToDepartment(departmentDTO);
        updatedDepartmnet.setFaculty(faculty);
        Department updatedDepartment1 = departmentService.update(id, updatedDepartmnet);
        if(updatedDepartment1==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedDepartment1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private DepartmentDTO convertToDepartmentDTO(Department department) {
        return modelMapper.map(department, DepartmentDTO.class);
    }

    private Department convertToDepartment(DepartmentDTO departmentDTO) {
        return modelMapper.map(departmentDTO, Department.class);
    }
}
