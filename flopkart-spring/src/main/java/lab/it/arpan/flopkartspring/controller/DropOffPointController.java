package lab.it.arpan.flopkartspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lab.it.arpan.flopkartspring.datastore.DropOffPointRepository;
import lab.it.arpan.flopkartspring.model.DropOffPoint;

@RestController
@CrossOrigin
public class DropOffPointController {
    
    private final DropOffPointRepository dropOffPointRepository;

    @Autowired
    public DropOffPointController(DropOffPointRepository dropOffPointRepository) {
        this.dropOffPointRepository = dropOffPointRepository;
    }

    @GetMapping(path = "/drop-off-points", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DropOffPoint>> listProducts(
            @RequestParam(required = true) double sourceLatitude, 
            @RequestParam(required = true) double sourceLongitude,
            @RequestParam(defaultValue = "10") int maxItems) {
        return ResponseEntity.ok(dropOffPointRepository.getDropOffPoints(sourceLatitude, sourceLongitude, maxItems));
    }

}
