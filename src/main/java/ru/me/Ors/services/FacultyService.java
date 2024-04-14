package ru.me.Ors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.me.Ors.models.Faculty;
import ru.me.Ors.repo.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FacultyService {

    private final FacultyRepository facultyRepository;


    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    public Faculty findOne(int id) {
        Optional<Faculty> foundFaculty = facultyRepository.findById(id);

        return foundFaculty.orElse(null);
    }

    public Optional<Faculty> findByName(String name) {
        return facultyRepository.findByName(name);
    }

    @Transactional
    public void save(Faculty faculty) {
        facultyRepository.save(faculty);
    }


    @Transactional
    public Faculty update(int id, Faculty updatedFaculty) {
        updatedFaculty.setId(id);
        return facultyRepository.save(updatedFaculty);
    }

    @Transactional
    public void delete (int id) {
        facultyRepository.deleteById(id);
    }


    //TODO поиск по названию
}
