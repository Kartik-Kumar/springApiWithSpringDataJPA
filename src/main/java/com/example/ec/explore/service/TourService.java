package com.example.ec.explore.service;

import com.example.ec.explore.domain.Difficulty;
import com.example.ec.explore.domain.Region;
import com.example.ec.explore.domain.Tour;
import com.example.ec.explore.domain.TourPackage;
import com.example.ec.explore.repo.TourPackageRepository;
import com.example.ec.explore.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private TourPackageRepository tourPackageRepository;
    private TourRepository tourRepository;

    @Autowired
    public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
        this.tourPackageRepository = tourPackageRepository;
        this.tourRepository = tourRepository;
    }
    /**
     * Create a new Tour Object and persist it to the Database.
     *
     * @param title title
     * @param description description
     * @param blurb blurb
     * @param price price
     * @param duration duration
     * @param bullets bullets
     * @param keywords keywords
     * @param tourPackageName tour package name
     * @param difficulty difficulty
     * @param region region
     * @return Tour Entity
     */
    public Tour createTour(String title, String description, String blurb, Integer price,
                           String duration, String bullets,
                           String keywords, String tourPackageName, Difficulty difficulty, Region region ) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(()->
                new RuntimeException("Tour package does not exist: " + tourPackageName));

        return tourRepository.save(new Tour(title, description,blurb, price, duration,
                bullets, keywords, tourPackage, difficulty, region));
    }
    /**
     * Calculate the number of Tours in the Database.
     *
     * @return the total.
     */
    public long total() {
        return tourRepository.count();
    }
}
