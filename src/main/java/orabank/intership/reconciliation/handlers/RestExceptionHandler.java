package orabank.intership.reconciliation.handlers;


import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.Exception.InvalidEntityException;
import orabank.intership.reconciliation.Exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDAO> handleException(EntityNotFoundException exception, WebRequest webRequest){
        final HttpStatus notfound= HttpStatus.NOT_FOUND;
        final ErrorDAO errorDAO=ErrorDAO.builder()
                .errorCodes(exception.getErrorCodes())
                .httpCode(notfound.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDAO,notfound);
    }
    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDAO> handleException(InvalidEntityException exception, WebRequest webRequest){
        final HttpStatus badRequest= HttpStatus.BAD_REQUEST;
        final ErrorDAO errorDAO=ErrorDAO.builder()
                .errorCodes(exception.getErrorCodes())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity<>(errorDAO,badRequest);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDAO> handleException(InvalidOperationException exception, WebRequest webRequest) {
        final HttpStatus serverError = HttpStatus.valueOf(500);
        final ErrorDAO errorDto = ErrorDAO.builder()
                .errorCodes(exception.getErrorCodes())
                .httpCode(serverError.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity<>(errorDto, serverError);
    }
}
