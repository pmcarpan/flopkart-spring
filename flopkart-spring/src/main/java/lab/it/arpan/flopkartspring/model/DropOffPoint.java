package lab.it.arpan.flopkartspring.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class DropOffPoint {
    Double latitude;
    Double longitude;
    String address;
    String city;
    String state;
    String country;
    String zipCode;
    String contact;
}
