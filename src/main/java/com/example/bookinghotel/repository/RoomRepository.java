package com.example.bookinghotel.repository;

import com.example.bookinghotel.enity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findAll();

    Room findRoomById(int id);

    Room findRoomByCategoryIdAndSizeId(int categoryId, int sizeId);

}
