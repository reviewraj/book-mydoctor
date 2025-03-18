package com.bookmydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmydoctor.entity.Appointment;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
