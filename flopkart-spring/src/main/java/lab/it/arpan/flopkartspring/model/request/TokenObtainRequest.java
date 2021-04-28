package lab.it.arpan.flopkartspring.model.request;

import lombok.Value;

@Value(staticConstructor = "of")
public class TokenObtainRequest {

    String username;
    String password;
    
}
