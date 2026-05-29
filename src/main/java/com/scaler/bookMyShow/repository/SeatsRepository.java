package com.scaler.bookMyShow.repository;

import com.scaler.bookMyShow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findAllByScreenId(int screenId);
}
