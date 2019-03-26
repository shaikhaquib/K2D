package com.abatechnology.kirana2door.models;

public class DefaultResponse {

    private boolean error;
    private String errormsg;

    public DefaultResponse(boolean error, String errormsg) {
        this.error = error;
        this.errormsg = errormsg;
    }

    public boolean isError() {
        return error;
    }

    public String getErrormsg() {
        return errormsg;
    }
}
