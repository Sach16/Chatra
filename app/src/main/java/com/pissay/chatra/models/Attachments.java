package com.pissay.chatra.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S.K. Pissay on 30/8/16.
 */
public class Attachments {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("type")
    @Expose
    private String type;

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


}

