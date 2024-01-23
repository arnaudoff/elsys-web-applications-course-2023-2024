package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.dto.RoomDto;
import com.example.registrationlogindemo.entity.Room;
import com.example.registrationlogindemo.repository.RoomRepository;
import com.example.registrationlogindemo.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void saveRoom(RoomDto roomDto) {
        Room room = new Room();
        room.setName(roomDto.getName());
        room.setCapacity(roomDto.getCapacity());
        room.setDescription(roomDto.getDescription());
        room.setHasProjector(roomDto.isHasProjector());
        room.setHasWhiteboard(roomDto.isHasWhiteboard());
        room.setHasConferenceCall(roomDto.isHasConferenceCall());
        room.setAvailable(roomDto.isAvailable());

        roomRepository.save(room);
    }

    @Override
    public List<RoomDto> findAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : rooms) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId((long) room.getRoom_id());
            roomDto.setName(room.getName());
            roomDto.setCapacity(room.getCapacity());
            roomDto.setDescription(room.getDescription());
            roomDto.setHasProjector(room.isHasProjector());
            roomDto.setHasWhiteboard(room.isHasWhiteboard());
            roomDto.setHasConferenceCall(room.isHasConferenceCall());
            roomDto.setAvailable(room.isAvailable());
            roomDto.setMeetings(room.getMeetings());
            roomDtos.add(roomDto);
        }
        return roomDtos;
    }

    @Override
    public Room findById(int id) {
        return roomRepository.findById(id);
    }
}
