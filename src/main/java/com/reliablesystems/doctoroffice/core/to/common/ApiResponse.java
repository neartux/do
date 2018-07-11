package com.reliablesystems.doctoroffice.core.to.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private boolean error;
    private String message;
    private Object data;

    public ApiResponse(boolean error, Object data){
        this.error = error;
        this.data = data;
    }

    public ApiResponse(boolean error, String message){
        this.error = error;
        this.message = message;
    }
}
