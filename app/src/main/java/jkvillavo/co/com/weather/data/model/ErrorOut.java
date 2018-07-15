package jkvillavo.co.com.weather.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorOut {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("error")
    @Expose
    private String error;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
