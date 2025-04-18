package com.gnegdev.kudava.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "packet_analysis_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacketAnalysis {
    @Id
//    @Column(name = "analysis_uuid", updatable = false, nullable = false, unique = true)
//    @GeneratedValue
//    @JsonProperty("analysis_uuid")
//    private UUID analysisUuid;
    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID uuid;

//    @OneToOne(mappedBy = "packet_analysis_results")
//    private Packet packet;

    @Column(name = "analysis_result")
    @JsonProperty("analysis_result")
    private String analysisResult;

    @Column(name = "timestamp")
    private Date timestamp;
}
