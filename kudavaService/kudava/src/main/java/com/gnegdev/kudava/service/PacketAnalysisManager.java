package com.gnegdev.kudava.service;

import com.gnegdev.kudava.db.repository.PacketAnalysisRepository;
import com.gnegdev.kudava.entity.PacketAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacketAnalysisManager {
    private final PacketAnalysisRepository packetAnalysisRepository;

    @Autowired
    public PacketAnalysisManager(PacketAnalysisRepository packetAnalysisRepository) {
        this.packetAnalysisRepository = packetAnalysisRepository;
    }

    public PacketAnalysis savePacketAnalysis(PacketAnalysis packetAnalysis) {
        packetAnalysis = packetAnalysisRepository.save(packetAnalysis);
        return packetAnalysis;
    }
}
