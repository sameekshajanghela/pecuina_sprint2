package com.capgemini.pecunia.accountmgmt.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @Min(value = 6)
    private String addressId;
    private String addressLine;
    private String addressCity;
    private String addressState;
    private String addressCountry;
    @Min(value = 6)
    private String addressZipcode;


    /**
     * setters and getters
     */
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode;
    }


    /**
     * override hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return addressId.hashCode();
    }

    /**
     * check equality of address object
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || !(object instanceof Address))
            return false;
        Address address = (Address) object;
        return this.addressId.equals(address.getAddressId());

    }

    /**
     * @return combine details of address
     */
    @Override
    public String toString() {
        return "Address [addressId=" + addressId + ", addressLine=" + addressLine + ", addressCity=" + addressCity
                + ", addressState=" + addressState + ", addressCountry=" + addressCountry + ", addressZipcode="
                + addressZipcode + "]";
    }

}
