package am.bdg.intermediate_group_2_W_S.bank_transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;


@Builder
@Data
public class BankAccountDto {
    private long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto userDto;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String accountNumber;

    @Digits(integer = 16, fraction = 2)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance;
}
