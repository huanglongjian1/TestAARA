package com.android.testmvp.network;

public class ServerException extends Exception {
    private int code;
    public ServerException(String message){
        super(message);
    }

    public ServerException(int code, String mes){
        super(mes);
        this.code=code;
    }

    public int getCode(){
        return code;
    }

    @Override
    public String toString(){
        return "ServerException{"+
                "code="+code+
                '}';
    }
}
