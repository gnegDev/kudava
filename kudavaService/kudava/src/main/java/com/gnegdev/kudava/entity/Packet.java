package com.gnegdev.kudava.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "input_packets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Packet {
    @Id
    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "arrival_time", nullable = false)
    @JsonProperty("arrival_time")
    private Date arrivalTime;

    @Column(name = "src_ip", nullable = false)
    @JsonProperty("src_ip")
    private String srcIP;

    @Column(name = "dst_ip")
    @JsonProperty("dst_ip")
    private String dstIP;

    @Column(name = "protocol")
    private String protocol;

    @Column(name = "additional_data")
    @JsonProperty("additional_data")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode additionalData;

}
