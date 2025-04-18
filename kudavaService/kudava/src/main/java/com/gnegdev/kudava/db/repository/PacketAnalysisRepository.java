package com.gnegdev.kudava.db.repository;

import com.gnegdev.kudava.entity.PacketAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacketAnalysisRepository extends JpaRepository<PacketAnalysis, UUID> {

}
