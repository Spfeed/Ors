package ru.me.Ors.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.me.Ors.dto.RoomDepartmentDTO;
import ru.me.Ors.models.Department;
import ru.me.Ors.models.Room;
import ru.me.Ors.models.RoomDepartment;
import ru.me.Ors.models.RoomDepartmentId;
import ru.me.Ors.services.DepartmentService;
import ru.me.Ors.services.RoomDepartmentService;
import ru.me.Ors.services.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roomDepartment")
public class RoomDepartmentController {

    private final RoomDepartmentService roomDepartmentService;
    private final ModelMapper modelMapper;
    private final RoomService roomService;
    private final DepartmentService departmentService;

    @Autowired
    public RoomDepartmentController(RoomDepartmentService roomDepartmentService, ModelMapper modelMapper, RoomService roomService, DepartmentService departmentService) {
        this.roomDepartmentService = roomDepartmentService;
        this.modelMapper = modelMapper;
        this.roomService = roomService;
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public List<RoomDepartmentDTO> getAll() {
        return roomDepartmentService.findAll().stream().map(this::convertToRoomDepartmentDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{roomId}/{departmentId}")
    RoomDepartmentDTO getOne(int roomId, int departmentId) {
        return convertToRoomDepartmentDTO(roomDepartmentService.findOne(roomId, departmentId));
    }

    @PostMapping("/add")
    public RoomDepartmentDTO create(@RequestBody @Valid RoomDepartmentDTO roomDepartmentDTO) {
        RoomDepartment roomDepartment = new RoomDepartment();
        RoomDepartmentId roomDepartmentId = new RoomDepartmentId(roomDepartmentDTO.getRoomId(),
                roomDepartmentDTO.getDepartmentId());
        roomDepartment.setId(roomDepartmentId);
        return convertToRoomDepartmentDTO(roomDepartmentService.create(roomDepartment));
    }

    @PutMapping()
    public ResponseEntity<RoomDepartment> update(@RequestBody @Valid RoomDepartmentDTO roomDepartmentDTO, int roomId, int departmentId) {
        RoomDepartmentId roomDepartmentId = new RoomDepartmentId(roomDepartmentDTO.getDepartmentId(),roomDepartmentDTO.getRoomId());
        Room room = roomService.findOne(roomDepartmentDTO.getRoomId());
        Department department = departmentService.findOne(roomDepartmentDTO.getDepartmentId());
        RoomDepartment roomDepartment = new RoomDepartment(room, department);
        RoomDepartment updatedRoomDepartment = roomDepartmentService.update(roomId,departmentId, roomDepartment);
        if (updatedRoomDepartment == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedRoomDepartment);
    }

    @DeleteMapping("/{roomId}/{departmentId}")
    public ResponseEntity<Void> delete(@PathVariable int roomId, @PathVariable int departmentId) {
        roomDepartmentService.delete(roomId, departmentId);
        return ResponseEntity.noContent().build();
    }

    private RoomDepartmentDTO convertToRoomDepartmentDTO(RoomDepartment roomDepartment) {
        return modelMapper.map(roomDepartment, RoomDepartmentDTO.class);
    }

    private RoomDepartment convertToRoomDepartment(RoomDepartmentDTO roomDepartmentDTO) {
        return modelMapper.map(roomDepartmentDTO, RoomDepartment.class);
    }
}
