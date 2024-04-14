package ru.me.Ors.controllers;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.me.Ors.dto.CampusDTO;
import ru.me.Ors.dto.FacultyDepartmentDTO;
import ru.me.Ors.models.Campus;
import ru.me.Ors.services.CampusService;
import ru.me.Ors.util.CampusCreateException;
import ru.me.Ors.util.CampusErrorResponse;
import ru.me.Ors.util.FacultyCreationException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/campuses")
public class CampusController {

    private final CampusService campusService;
    private final ModelMapper modelMapper;

    @Autowired
    public CampusController(CampusService campusService, ModelMapper modelMapper) {
        this.campusService = campusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<CampusDTO> getAllCampuses() {
        return campusService.findAll().stream().map(this::convertToCampusDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CampusDTO findById(@PathVariable int id) {
        return convertToCampusDTO(campusService.findOne(id));
    }

    @GetMapping("/name/{campusName}")
    public List<FacultyDepartmentDTO> findFacultyDepartmentByCampusName(@PathVariable String campusName) {
        return campusService.getFacultiesDepartmentsForCampus(campusName);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create (@RequestBody @Valid CampusDTO campusDTO,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new FacultyCreationException(errorMsg.toString());
        }
        campusService.save(convertToCampus(campusDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Campus> update(@RequestBody Campus campus, int id) {
        Campus updatedCampus = campusService.update(id, campus);
        if (updatedCampus == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCampus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        campusService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    private ResponseEntity<CampusErrorResponse> handleException(CampusCreateException e) {
        CampusErrorResponse campusErrorResponse = new CampusErrorResponse(e.getMessage());
        return new ResponseEntity<>(campusErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private CampusDTO convertToCampusDTO(Campus campus) {
        return modelMapper.map(campus, CampusDTO.class);
    }

    private Campus convertToCampus(CampusDTO campusDTO) {
        return modelMapper.map(campusDTO, Campus.class);
    }
}
