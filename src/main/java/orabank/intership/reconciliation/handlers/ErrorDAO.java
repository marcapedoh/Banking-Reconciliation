package orabank.intership.reconciliation.handlers;


import lombok.*;
import orabank.intership.reconciliation.Exception.ErrorCodes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDAO {
    private Integer httpCode;
    private ErrorCodes errorCodes;
    private String message;
    private List<String> errors= new ArrayList<>();
}
