package com.example.bookinghotel.service;

import com.example.bookinghotel.enity.Room;

import java.util.List;

public interface RoomService {

    List<Room> findAll();
    Room findRoomById(int id);

    Room findRoomByCategoryIdAndSizeId(int categoryId, int sizeId);

}
