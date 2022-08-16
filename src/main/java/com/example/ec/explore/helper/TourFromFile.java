package com.example.ec.explore.helper;

import com.example.ec.explore.domain.Difficulty;
import com.example.ec.explore.domain.Region;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

/**
 * Helper class to import ExploreCalifornia.json
 */
public class TourFromFile {
    //fields
    private String packageType, title, description, blurb, price, length,
            bullets, keywords, difficulty, region;
    //reader
    public static List<TourFromFile> read(String fileToImport) throws IOException {
        return new ObjectMapper().setVisibility(FIELD, ANY).
                readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {});
    }
    protected TourFromFile(){}

    public String getPackageType() { return packageType; }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getBlurb() { return blurb; }

    public Integer getPrice() { return Integer.parseInt(price); }

    public String getLength() { return length; }

    public String getBullets() { return bullets; }

    public String getKeywords() { return keywords; }

    public Difficulty getDifficulty() { return Difficulty.valueOf(difficulty); }

    public Region getRegion() { return Region.findByLabel(region); }
}

