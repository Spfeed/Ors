package ru.me.Ors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.me.Ors.dto.FacultyDepartmentDTO;
import ru.me.Ors.dto.RoomAreaVolumeDTO;
import ru.me.Ors.models.Room;
import ru.me.Ors.repo.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoomService {

    @Qualifier("datasource1")
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findOne(int id) {
        Optional<Room> findedRoom = roomRepository.findById(id);
        return findedRoom.orElse(null);
    }

    public List<RoomAreaVolumeDTO> calculateAreaAndVolume() {
        List<Object[]> objects = roomRepository.calculateAreaAndVolume();
        return objects.stream().map(this::mapToRoomAreaVolumeDTO).toList();
    }

    @Transactional
    public Room save (Room room) {
        return roomRepository.save(room);
    }

    @Transactional
    public Room update(int id, Room updatedRoom) {
        updatedRoom.setId(id);
        return roomRepository.save(updatedRoom);
    }

    @Transactional
    public void delete(int id) {
        roomRepository.deleteById(id);
    }

    private RoomAreaVolumeDTO mapToRoomAreaVolumeDTO(Object[] object) {
        RoomAreaVolumeDTO roomAreaVolumeDTO = new RoomAreaVolumeDTO();
        roomAreaVolumeDTO.setRoomId((int) object[0]);
        roomAreaVolumeDTO.setCampusName((String) object[1]);
        roomAreaVolumeDTO.setArea((double) object[2]);
        roomAreaVolumeDTO.setVolume((double) object[3]);

        return roomAreaVolumeDTO;
    }

}
