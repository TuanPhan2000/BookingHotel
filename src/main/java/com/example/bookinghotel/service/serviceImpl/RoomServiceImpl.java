package com.example.bookinghotel.service.serviceImpl;

import com.example.bookinghotel.enity.Room;
import com.example.bookinghotel.repository.RoomRepository;
import com.example.bookinghotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findRoomById(int id) {
        return roomRepository.findRoomById(id);
    }

    @Override
    public Room findRoomByCategoryIdAndSizeId(int categoryId, int sizeId) {
        Room room = roomRepository.findRoomByCategoryIdAndSizeId(categoryId, sizeId);
        if(room.getStock() > 0){
            return room;
        }

        return null;
    }
}
