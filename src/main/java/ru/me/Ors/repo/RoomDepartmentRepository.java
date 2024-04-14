package ru.me.Ors.repo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.me.Ors.models.RoomDepartment;
import ru.me.Ors.models.RoomDepartmentId;

import java.util.Optional;

@Repository
public interface RoomDepartmentRepository extends JpaRepository<RoomDepartment, Integer> {
    Optional<RoomDepartment> findByRoomIdAndDepartmentId(int roomId, int departmentId);
    void deleteById(RoomDepartmentId id);
}
