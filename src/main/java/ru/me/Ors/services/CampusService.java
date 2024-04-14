package ru.me.Ors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.me.Ors.dto.FacultyDepartmentDTO;
import ru.me.Ors.models.Campus;
import ru.me.Ors.repo.CampusRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CampusService {

    private final CampusRepository campusRepository;

    @Autowired
    public CampusService(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    public List<Campus>findAll() {
       return campusRepository.findAll();
    }

    public Campus findOne(int id) {
        Optional<Campus>foundCampus = campusRepository.findById(id);

        return foundCampus.orElse(null);//TODO execption
    }

    public Optional<Campus> findByName(String name) {
        return campusRepository.findByName(name);
    }

    public List<FacultyDepartmentDTO> getFacultiesDepartmentsForCampus(String name) {
        if (findByName(name).isEmpty())
            throw new RuntimeException("Введено неверное название корпуса");

        List<Object[]> objects = campusRepository.getFacultiesDepartmentsForCampus(name);
        return objects.stream().map(this::mapTpFacultyDepartmentDTO).toList();
    }

    @Transactional
    public void save(Campus campus) {
        campusRepository.save(campus);
    }

    @Transactional
    public Campus update (int id, Campus updatedCampus) {
        updatedCampus.setId(id);
        return campusRepository.save(updatedCampus);
    }


    @Transactional
    public void delete (int id) {
        campusRepository.deleteById(id);
    }

    private FacultyDepartmentDTO mapTpFacultyDepartmentDTO(Object[] object) {
        FacultyDepartmentDTO facultyDepartmentDTO = new FacultyDepartmentDTO();
        facultyDepartmentDTO.setFacultyName((String) object[0]);
        facultyDepartmentDTO.setFacultyDean((String) object[1]);
        facultyDepartmentDTO.setDepartmentName((String) object[2]);
        facultyDepartmentDTO.setDepartmentChairperson((String) object[3]);

        return facultyDepartmentDTO;
    }


}
