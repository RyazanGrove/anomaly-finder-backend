package com.ryazangrove.anomaly_finder_backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "imageInfo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String type;

    private int imageWidth;

    private int imageHeight;
 
    @Embedded
    private TargetArea target;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class TargetArea {

        public int xMin;

        public int xMax;

        public int yMin;

        public int yMax;
    }
}