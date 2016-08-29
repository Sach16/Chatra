package com.pissay.chatra.models;

/**
 * Created by S.K. Pissay on 28/8/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedAt {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("timezoneType")
    @Expose
    private Integer timezoneType;
    @SerializedName("timezone")
    @Expose
    private String timezone;

    /**
     *
     * @return
     * The id
     */
    public Object getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Object id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The timezoneType
     */
    public Integer getTimezoneType() {
        return timezoneType;
    }

    /**
     *
     * @param timezoneType
     * The timezoneType
     */
    public void setTimezoneType(Integer timezoneType) {
        this.timezoneType = timezoneType;
    }

    /**
     *
     * @return
     * The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}
