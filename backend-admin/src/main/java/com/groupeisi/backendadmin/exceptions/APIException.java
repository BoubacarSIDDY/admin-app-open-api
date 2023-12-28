package com.groupeisi.backendadmin.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class APIException {
    String message;
    HttpStatus status;
    LocalDateTime timestamp;
}
