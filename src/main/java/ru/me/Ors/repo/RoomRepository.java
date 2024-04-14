package ru.me.Ors.repo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.me.Ors.dto.RoomAreaVolumeDTO;
import ru.me.Ors.models.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "SELECT r.id AS room_id, c.name AS campus_name, (r.width * r.length) AS area, (r.width * r.length * r.height) AS volume " +
            "FROM Room r JOIN Campus c ON r.campus_id = c.id", nativeQuery = true)
    List<Object[]> calculateAreaAndVolume();

}
