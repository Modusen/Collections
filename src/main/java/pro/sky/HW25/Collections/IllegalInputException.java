package pro.sky.HW25.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalInputException extends  NullPointerException{
    public IllegalInputException() {
    }

    public IllegalInputException(String s) {
        super(s);
    }
}
