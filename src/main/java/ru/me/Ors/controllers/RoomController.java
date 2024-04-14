package ru.me.Ors.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.me.Ors.dto.RoomAreaVolumeDTO;
import ru.me.Ors.dto.RoomDTO;
import ru.me.Ors.models.Campus;
import ru.me.Ors.models.Room;
import ru.me.Ors.services.CampusService;
import ru.me.Ors.services.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final ModelMapper modelMapper;
    private final CampusService campusService;

    @Autowired
    public RoomController(RoomService roomService, ModelMapper modelMapper, CampusService campusService) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
        this.campusService = campusService;
    }

    @GetMapping("/all")
    public List<RoomDTO> getAll() {
        return roomService.findAll().stream().map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoomDTO getOne(int id) {
        return convertToRoomDTO(roomService.findOne(id));
    }

    @GetMapping("/areaAndVolume")
    public List<RoomAreaVolumeDTO> roomAreaVolume() {
        return roomService.calculateAreaAndVolume();
    }

    @PostMapping("/add")
    public Room create(@RequestBody @Valid RoomDTO roomDTO) {
        Campus campus = campusService.findByName(roomDTO.getCampusName())
                .orElseThrow(()->new RuntimeException("Корпус с таким названием не найден"));
        Room room = convertToRoom(roomDTO);
        room.setCampus(campus);
        return roomService.save(room);
    }

    @PutMapping()
    public ResponseEntity<Room> update(@RequestBody @Valid RoomDTO roomDTO, int id) {
        Campus campus = campusService.findByName(roomDTO.getCampusName())
                .orElseThrow(()->new RuntimeException("Корпус с таким названием не найден"));
        Room room =convertToRoom(roomDTO);
        room.setCampus(campus);
        Room updatedRoom = roomService.update(id, room);
        if (updatedRoom == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private RoomDTO convertToRoomDTO(Room room) {
        return modelMapper.map(room, RoomDTO.class);
    }

    private Room convertToRoom(RoomDTO roomDTO) {
        return modelMapper.map(roomDTO, Room.class);
    }
}
