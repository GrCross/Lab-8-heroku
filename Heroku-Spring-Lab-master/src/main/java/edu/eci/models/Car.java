package edu.eci.models;



import java.io.Serializable; 

public class Car implements Serializable{

    private String licencePlate;
    private String brand;

    public Car() {
        
    }
    
    public Car(String licencePlate, String brand) {
        this.licencePlate = licencePlate;
        this.brand = brand;
    }

    /**
     * @return the licencePlate
     */
    public String getLicencePlate() {
        return licencePlate;
    }

    /**
     * @param licencePlate the licencePlate to set
     */
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

}
