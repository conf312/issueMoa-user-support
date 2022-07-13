package com.issuemoa.user.support.message;

import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestMessage {

    private HttpStatus status;
    private List<String> errors;
    private Object data;

    public RestMessage(HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public RestMessage(HttpStatus status, Object data) {
        super();
        this.status = status;
        this.data = data;
    }

    public RestMessage(HttpStatus status, Object data, String error) {
        super();
        this.status = status;
        this.errors = Arrays.asList(error);
        this.data = data;
    }
}
