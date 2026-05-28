package com.scaler.bookMyShow.repository;

import com.scaler.bookMyShow.models.Show;
import com.scaler.bookMyShow.models.ShowSeatType;
import com.scaler.bookMyShow.models.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Integer> {

    Optional<ShowSeatType> findByShowAndSeatType(Show show, SeatType seatType);
}
