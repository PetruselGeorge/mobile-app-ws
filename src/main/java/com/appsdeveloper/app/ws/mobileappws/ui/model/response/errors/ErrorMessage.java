package com.appsdeveloper.app.ws.mobileappws.ui.model.response.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private Date timestamp;
    private String message;

}
