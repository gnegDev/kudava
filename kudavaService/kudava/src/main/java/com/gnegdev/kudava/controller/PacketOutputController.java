package com.gnegdev.kudava.controller;

import com.gnegdev.kudava.entity.Packet;
import com.gnegdev.kudava.service.PacketManager;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/packet/output")
public class PacketOutputController {
    private final PacketManager packetManager;

    @Autowired
    public PacketOutputController(PacketManager packetManager) {
        this.packetManager = packetManager;
    }

    @GetMapping("/get")
    @CrossOrigin
    @Validated
    public ResponseEntity<?> getPackets(@RequestParam(required = false, name = "from_date") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") Date fromDate,
                                        @RequestParam(required = false, name = "to_date") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") Date toDate,
                                        @RequestParam(defaultValue = "0") @Min(0) int offset,
                                        @RequestParam(defaultValue = "10") @Min(1) int limit) {
//        System.out.println(fromDate + " " + toDate);

        List<Packet> packets;
        if (fromDate == null && toDate == null) {
            packets = packetManager.getPackets(offset, limit).getContent();
        }
        else {
            packets = packetManager.getPackets(fromDate, toDate, offset, limit).getContent();
        }
        return new ResponseEntity<>(packets, HttpStatus.OK);
    }
}

