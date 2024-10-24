package kad.dev.customerservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CustomerDTO {
    Long id;
    @NotEmpty()
    @Size(min = 2)
    String firstName;
    @NotEmpty()
    @Size(min = 2)
    String lastName;
    @NotEmpty()
    @Size(min = 8)
    String email;
}
