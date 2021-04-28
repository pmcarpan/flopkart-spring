package lab.it.arpan.flopkartspring.datastore;

import java.util.List;

import lab.it.arpan.flopkartspring.model.DropOffPoint;

public interface DropOffPointRepository {
    
    List<DropOffPoint> getDropOffPoints();

    List<DropOffPoint> getDropOffPoints(double sourceLatitude, double sourceLongitude, int maxItems);

}
