package ru.me.Ors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.me.Ors.models.Room;
import ru.me.Ors.models.RoomDepartment;
import ru.me.Ors.models.RoomDepartmentId;
import ru.me.Ors.repo.RoomDepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomDepartmentService {

    private final RoomDepartmentRepository roomDepartmentRepository;

    @Autowired
    public RoomDepartmentService(RoomDepartmentRepository roomDepartmentRepository) {
        this.roomDepartmentRepository = roomDepartmentRepository;
    }

    public List<RoomDepartment> findAll() {
        return roomDepartmentRepository.findAll();
    }

    public RoomDepartment findOne(int roomId, int departmentId) {
        Optional<RoomDepartment> foundRoomDepartment = roomDepartmentRepository.findByRoomIdAndDepartmentId(roomId, departmentId);
        return foundRoomDepartment.orElse(null);
    }

    @Transactional
    public RoomDepartment create(RoomDepartment roomDepartment) {
        return roomDepartmentRepository.save(roomDepartment);
    }

    @Transactional
    public RoomDepartment update(int roomId, int departmentId, RoomDepartment roomDepartment) {
        RoomDepartment existingRoomDepartment = roomDepartmentRepository.
                findByRoomIdAndDepartmentId(roomId, departmentId).orElse(null);
        if (existingRoomDepartment == null) {
            return null;
        }
        existingRoomDepartment.setRoom(roomDepartment.getRoom());
        existingRoomDepartment.setDepartment(roomDepartment.getDepartment());
        return roomDepartmentRepository.save(existingRoomDepartment);

    }

    @Transactional
    public void delete(int roomId, int departmentId) {
        RoomDepartmentId roomDepartmentId = new RoomDepartmentId(roomId, departmentId);
        roomDepartmentRepository.deleteById(roomDepartmentId);
    }

}
