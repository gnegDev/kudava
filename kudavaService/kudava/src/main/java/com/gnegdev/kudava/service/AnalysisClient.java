package com.gnegdev.kudava.service;

import com.gnegdev.kudava.entity.Packet;
import com.gnegdev.kudava.entity.PacketAnalysis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalysisClient {
    @Value("${api.analysis.url}")
    private String url;
    public PacketAnalysis analyzePacket(Packet packet) {
//        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();

        // Устанавливаем заголовки
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Создаем запрос с телом и заголовками
        HttpEntity<Packet> request = new HttpEntity<>(packet, headers);

        // Отправляем POST-запрос с телом
        ResponseEntity<PacketAnalysis> response = restTemplate.exchange(
                url + "/api/analyze",
                HttpMethod.POST,
                request,
                PacketAnalysis.class
        );

        return response.getBody();
    }
}
