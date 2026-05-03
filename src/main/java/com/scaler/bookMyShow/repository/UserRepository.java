package com.scaler.bookMyShow.repository;

import com.scaler.bookMyShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// even if annotation was not written it will work, because JpaRepository already contains it
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // JpaRepository<Entity, WrapperDatatype(Id))
}
