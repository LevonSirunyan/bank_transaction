package am.bdg.intermediate_group_2_W_S.bank_transaction.dto;

import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private RoleType type;
}
