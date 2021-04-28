package lab.it.arpan.flopkartspring.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class User {
    
    Long id;
    String username;
    String password;

}
