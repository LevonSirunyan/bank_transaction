package am.bdg.intermediate_group_2_W_S.bank_transaction.dto;

import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.TransactionStatus;
import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class TransactionDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    private BankAccountDto bankAccountDto;

    @PastOrPresent
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createdDate;

    @ApiModelProperty(dataType = "am.bdg.intermediate_group_2_W_S.bank_transaction.enums.TransactionType",
            allowableValues = "DEPOSIT, WITHDRAWAL")
    private TransactionType type;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TransactionStatus status;

    @Digits(integer = 16, fraction = 2)
    private BigDecimal amount;//if(type=DEPOSIT)amount>0 else amount<0

}
