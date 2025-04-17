package com.gnegdev.kudava.service;

import com.gnegdev.kudava.db.repository.PacketRepository;
import com.gnegdev.kudava.entity.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PacketManager {
    private final PacketRepository packetRepository;

    @Autowired
    public PacketManager(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    public Packet savePacket(Packet packet) {
        packet = packetRepository.save(packet);
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
