package com.appsdeveloper.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "AddressEntity")
@Table(name = "addresses")
@Getter
@Setter
public class AddressEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2608695836937709519L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "address_id", length = 30, nullable = false)
    private String addressId;
    @Column(name = "city", length = 20, nullable = false)
    private String city;
    @Column(name ="country",length = 20, nullable = false)
    private String country;
    @Column(name = "street_name",length = 70, nullable = false)
    private String streetName;
    @Column(name="postal_code",length = 12, nullable = false)
    private String postalCode;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

}
