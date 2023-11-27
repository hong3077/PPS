package com.example.planepowerselecter;

/**
 * Represents a product with information such as product name, voltage, power, purpose, and cost.
 * Provides getter methods to access the information.
 */
public class Post {

    private String product_name;

    private String voltage;

    private int power;

    private String purpose;

    private int cost;

    /**
     * Gets the product name.
     *
     * @return The product name.
     */
    public String getProduct_name(){
        return product_name;
    }

    /**
     * Gets the voltage.
     *
     * @return The voltage.
     */
    public String getVoltage(){
        return voltage;
    }

    /**
     * Gets the power.
     *
     * @return The power.
     */
    public int getPower(){
        return power;
    }

    /**
     * Gets the purpose.
     *
     * @return The purpose.
     */
    public String getPurpose(){
        return purpose;
    }

    /**
     * Gets the cost.
     *
     * @return The cost.
     */
    public int getCost(){
        return cost;
    }
}