package com.groupeisi.backendadmin.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestException extends RuntimeException{
    private String message;
    private HttpStatus status;
}
