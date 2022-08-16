package com.example.ec.explore;

import com.example.ec.explore.helper.TourFromFile;
import com.example.ec.explore.service.TourPackageService;
import com.example.ec.explore.service.TourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication   // It tell the java and spring boot , that this is our mircroservice project start - command line parameter and special startup project reside here
public class ExploreApplication implements CommandLineRunner {

    @Value("${ec.importfile}")
    private String importFile;

    @Autowired
    private TourPackageService tourPackageService;
    @Autowired
    private TourService tourService;
    public static void main(String[] args) {
        SpringApplication.run(ExploreApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        createTourPackages();
        long numOfPackage = tourPackageService.total();

        createTours("ExploreCalifornia.json");
        long numOfTours = tourService.total();
    }

    /**
     * Create tour entities from an external file
     */
    private void createTours(String fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(importedTour ->
                tourService.createTour(
                        importedTour.getTitle(),
                        importedTour.getDescription(),
                        importedTour.getBlurb(),
                        importedTour.getPrice(),
                        importedTour.getLength(),
                        importedTour.getBullets(),
                        importedTour.getKeywords(),
                        importedTour.getPackageType(),
                        importedTour.getDifficulty(),
                        importedTour.getRegion()));
    }
    private void createTourPackages(){
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");
    }

}
