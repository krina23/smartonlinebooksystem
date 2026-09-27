/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author patel
 */
@Entity
@Table(name = "user_addresses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAddresses.findAll", query = "SELECT u FROM UserAddresses u"),
    @NamedQuery(name = "UserAddresses.findByAddressId", query = "SELECT u FROM UserAddresses u WHERE u.addressId = :addressId"),
    @NamedQuery(name = "UserAddresses.findByFullAddress", query = "SELECT u FROM UserAddresses u WHERE u.fullAddress = :fullAddress"),
    @NamedQuery(name = "UserAddresses.findByCity", query = "SELECT u FROM UserAddresses u WHERE u.city = :city"),
    @NamedQuery(name = "UserAddresses.findByState", query = "SELECT u FROM UserAddresses u WHERE u.state = :state"),
    @NamedQuery(name = "UserAddresses.findByPincode", query = "SELECT u FROM UserAddresses u WHERE u.pincode = :pincode")})
public class UserAddresses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "address_id")
    private Integer addressId;
    @Size(max = 255)
    @Column(name = "full_address")
    private String fullAddress;
    @Size(max = 100)
    @Column(name = "city")
    private String city;
    @Size(max = 100)
    @Column(name = "state")
    private String state;
    @Column(name = "pincode")
    private Integer pincode;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;
    @OneToMany(mappedBy = "addressId")
    private Collection<Orders> ordersCollection;

    public UserAddresses() {
    }

    public UserAddresses(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAddresses)) {
            return false;
        }
        UserAddresses other = (UserAddresses) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserAddresses[ addressId=" + addressId + " ]";
    }
    
}
