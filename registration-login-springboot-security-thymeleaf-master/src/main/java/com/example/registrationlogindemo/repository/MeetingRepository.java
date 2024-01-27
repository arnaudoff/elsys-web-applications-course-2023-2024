package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.registrationlogindemo.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer>  {
    Meeting findById(int id);
}
