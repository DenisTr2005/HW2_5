package denistr.hw2_5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongEmployeeNameException extends RuntimeException {
    public WrongEmployeeNameException(String message) {
        super(message);
        this.printStackTrace();
    }
}
