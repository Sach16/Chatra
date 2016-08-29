package com.pissay.chatra.models;

/**
 * Created by S.K. Pissay on 28/8/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Evnts {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("crowd")
    @Expose
    private Integer crowd;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("parking")
    @Expose
    private Integer parking;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("foodMenu")
    @Expose
    private List<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
    @SerializedName("createdAt")
    @Expose
    private CreatedAt createdAt;
    @SerializedName("updatedAt")
    @Expose
    private UpdatedAt updatedAt;

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The crowd
     */
    public Integer getCrowd() {
        return crowd;
    }

    /**
     *
     * @param crowd
     * The crowd
     */
    public void setCrowd(Integer crowd) {
        this.crowd = crowd;
    }

    /**
     *
     * @return
     * The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @param imageUrl
     * The imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     * The parking
     */
    public Integer getParking() {
        return parking;
    }

    /**
     *
     * @param parking
     * The parking
     */
    public void setParking(Integer parking) {
        this.parking = parking;
    }

    /**
     *
     * @return
     * The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The foodMenu
     */
    public List<FoodMenu> getFoodMenu() {
        return foodMenu;
    }

    /**
     *
     * @param foodMenu
     * The foodMenu
     */
    public void setFoodMenu(List<FoodMenu> foodMenu) {
        this.foodMenu = foodMenu;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The createdAt
     */
    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updatedAt
     */
    public void setUpdatedAt(UpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }

}
