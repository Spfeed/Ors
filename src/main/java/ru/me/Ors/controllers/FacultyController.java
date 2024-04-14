package ru.me.Ors.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.me.Ors.dto.FacultyDTO;
import ru.me.Ors.models.Faculty;
import ru.me.Ors.services.FacultyService;
import ru.me.Ors.util.FacultyCreationException;
import ru.me.Ors.util.FacultyErrorResponse;
import ru.me.Ors.util.FacultyNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;
    private final ModelMapper modelMapper;

    @Autowired
    public FacultyController(FacultyService facultyService, ModelMapper modelMapper) {
        this.facultyService = facultyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<FacultyDTO> getAll() {
        return facultyService.findAll().stream().map(this::convertToFacultyDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FacultyDTO getOne(int id) {
        return convertToFacultyDTO(facultyService.findOne(id));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid FacultyDTO facultyDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError fieldError : errors){
                errorMsg.append(fieldError.getField())
                        .append(" - ").append(fieldError.getDefaultMessage())
                        .append(";");
            }
            throw new FacultyCreationException(errorMsg.toString());
        }

        facultyService.save(convertToFaculty(facultyDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty, int id) {
        Faculty updatedFaculty = facultyService.update(id, faculty);
        if (updatedFaculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        facultyService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler
    private ResponseEntity<FacultyErrorResponse> handleException(FacultyCreationException e) {
        FacultyErrorResponse response= new FacultyErrorResponse(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<FacultyErrorResponse> handleException(FacultyNotFoundException e) {
        FacultyErrorResponse response = new FacultyErrorResponse(
                "Факультета с таким id не существует"
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    private FacultyDTO convertToFacultyDTO(Faculty faculty) {
        return modelMapper.map(faculty, FacultyDTO.class);
    }

    private Faculty convertToFaculty(FacultyDTO facultyDTO) {
        return modelMapper.map(facultyDTO, Faculty.class);
    }
}
