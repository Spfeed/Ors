package ru.me.Ors.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.me.Ors.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
