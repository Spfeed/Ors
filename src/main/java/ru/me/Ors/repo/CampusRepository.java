package ru.me.Ors.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.me.Ors.dto.FacultyDepartmentDTO;
import ru.me.Ors.models.Campus;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Integer> {
    Optional<Campus> findByName(String name);

    @Query(value = "SELECT * FROM get_faculties_departments_for_campus(:campusName)",
            nativeQuery = true)
    List<Object[]> getFacultiesDepartmentsForCampus(String campusName);
}
