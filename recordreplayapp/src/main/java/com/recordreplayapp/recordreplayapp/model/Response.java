package com.recordreplayapp.recordreplayapp.model;

public class Response {
    private int id;
    private String http_reponse;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Response(int id, String http_reponse) {
        this.id = id;
        this.http_reponse = http_reponse;
    }
    public String getHttp_reponse() {
        return http_reponse;
    }
    public void setHttp_reponse(String http_reponse) {
        this.http_reponse = http_reponse;
    }
    
}
