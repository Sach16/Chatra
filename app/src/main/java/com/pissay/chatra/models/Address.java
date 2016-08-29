package com.pissay.chatra.models;

/**
 * Created by S.K. Pissay on 28/8/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("alphaStreet")
    @Expose
    private String alphaStreet;
    @SerializedName("betaStreet")
    @Expose
    private String betaStreet;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("zip")
    @Expose
    private Integer zip;

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
     * The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     * The uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The alphaStreet
     */
    public String getAlphaStreet() {
        return alphaStreet;
    }

    /**
     *
     * @param alphaStreet
     * The alphaStreet
     */
    public void setAlphaStreet(String alphaStreet) {
        this.alphaStreet = alphaStreet;
    }

    /**
     *
     * @return
     * The betaStreet
     */
    public String getBetaStreet() {
        return betaStreet;
    }

    /**
     *
     * @param betaStreet
     * The betaStreet
     */
    public void setBetaStreet(String betaStreet) {
        this.betaStreet = betaStreet;
    }

    /**
     *
     * @return
     * The city
     */
    public City getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *
     * @param cityId
     * The cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The zip
     */
    public Integer getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     * The zip
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }

}
