package com.gnegdev.kudava.controller;

import com.gnegdev.kudava.entity.Packet;
import com.gnegdev.kudava.service.PacketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/packet/input")
public class PacketInputController {
    private final PacketManager packetManager;

    @Autowired
    public PacketInputController(PacketManager packetManager) {
        this.packetManager = packetManager;
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePacket(@RequestBody Packet packet) {
        packet = packetManager.savePacket(packet);
        System.out.println(packet.toString());
        return new ResponseEntity<>(packet, HttpStatus.OK);
    }
}
