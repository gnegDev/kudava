package com.gnegdev.kudava.service;

import com.gnegdev.kudava.db.repository.PacketRepository;
import com.gnegdev.kudava.entity.Packet;
import com.gnegdev.kudava.entity.PacketAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PacketManager {
    private final PacketRepository packetRepository;
    private final AnalysisClient analysisClient;
    private final PacketAnalysisManager packetAnalysisManager;

    @Autowired
    public PacketManager(PacketRepository packetRepository, AnalysisClient analysisClient, PacketAnalysisManager packetAnalysisManager) {
        this.packetRepository = packetRepository;
        this.analysisClient = analysisClient;
        this.packetAnalysisManager = packetAnalysisManager;
    }

    public Packet savePacket(Packet packet) {
        packet = packetRepository.save(packet);

        PacketAnalysis packetAnalysis = analysisClient.analyzePacket(packet);
        packetAnalysis = packetAnalysisManager.savePacketAnalysis(packetAnalysis);

        packet.setAnalysisResult(packetAnalysis);
        return packet;
    }

    public Page<Packet> getPackets(int offset, int limit) {
        Page<Packet> packets = packetRepository.findAll(PageRequest.of(offset, limit));
        return packets;
    }

    public Page<Packet> getPackets(Date fromDate, Date toData, int offset, int limit) {
        Page<Packet> packets = packetRepository.findByArrivalTimeBetween(fromDate, toData, PageRequest.of(offset, limit));
        return packets;
    }
}
