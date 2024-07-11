package com.mountana.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebAPIResponseModel<T> {
    private boolean status;
    private String message;
    private T data;

    public WebAPIResponseModel<T> setSuccess(String message){
        this.status = true;
        this.message = message;

        return this;
    }

    public WebAPIResponseModel<T> setSuccess(T data){
        this.status = true;
        this.data = data;

        return this;
    }

    public WebAPIResponseModel<T> setSuccess(String message, T data){
        this.status = true;
        this.message = message;
        this.data = data;

        return this;
    }

    public WebAPIResponseModel<T> setError(String errorMessage){
        this.status = false;
        this.message = errorMessage;

        return this;
    }

    public WebAPIResponseModel<T> setError(String errorMessage, T data){
        this.status = false;
        this.message = errorMessage;
        this.data = data;

        return this;
    }
}
