package com.pissay.chatra.models;

/**
 * Created by S.K. Pissay on 28/8/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodMenu {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("cuisine")
    @Expose
    private String cuisine;
    @SerializedName("name")
    @Expose
    private String name;

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
     * The cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     *
     * @param cost
     * The cost
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     *
     * @return
     * The cuisine
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     *
     * @param cuisine
     * The cuisine
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

}

