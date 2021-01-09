package am.bdg.intermediate_group_2_W_S.bank_transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@Builder
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    private String name;

    private ContactDto contactDto;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<RoleDto> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactDto {
        private String address;
        private String telNumber;
        private String email;
    }
}
