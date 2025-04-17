package com.gnegdev.kudava.db.repository;

import com.gnegdev.kudava.entity.Packet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;

public interface PacketRepository extends JpaRepository<Packet, UUID> {


    Page<Packet> findByArrivalTimeBetween(Date fromDate, Date toData, PageRequest pageRequest);
}
