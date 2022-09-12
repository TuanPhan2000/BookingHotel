package com.example.bookinghotel.service.serviceImpl;

import com.example.bookinghotel.enity.Booking;
import com.example.bookinghotel.enity.Room;
import com.example.bookinghotel.repository.BookingRepository;
import com.example.bookinghotel.repository.RoomRepository;
import com.example.bookinghotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public boolean booking(Booking booking, int roomId) {
        Room room = roomRepository.findRoomById(roomId);
        if(room.getStock() > 0){
            bookingRepository.save(booking);
            room.setStock(room.getStock() - 1);
            roomRepository.save(room);
            return true;
        }
        return false;
    }
}
