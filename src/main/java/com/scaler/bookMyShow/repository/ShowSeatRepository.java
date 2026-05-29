package com.scaler.bookMyShow.repository;

import com.scaler.bookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    List<ShowSeat> findByShowId(int showId);

//    @Query("""
//        SELECT ss
//        FROM ShowSeat ss
//        WHERE ss.show.id = :showId
//        """)
//    List<ShowSeat> findByShowId(@Param("showId") int showId);
}

