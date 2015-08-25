//$Id: Booking.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.jboss.seam.annotations.Name;

@Entity
@Name("booking")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private User user;
    private Menu menu;
    private Date checkinDate;
    private Date checkoutDate;
    private String creditCard = "1234567890123456";
    private String creditCardName;
    private int creditCardExpiryMonth;
    private int creditCardExpiryYear;
    private boolean smoking;
    private int beds;
    private Byte status;

    public Booking() {

    }

    public Booking(Menu menu, User user) {

        this.menu = menu;
        this.user = user;
    }

    @Transient
    public BigDecimal getTotal() {

        return menu.getPrice().multiply(new BigDecimal(getNights()));
    }

    @Transient
    public int getNights() {

        return (int) (checkoutDate.getTime() - checkinDate.getTime()) / 1000 / 60 / 60 / 24;
    }

    @Id
    @GeneratedValue
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @NotNull
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCheckinDate() {

        return checkinDate;
    }

    public void setCheckinDate(Date datetime) {

        this.checkinDate = datetime;
    }

    @ManyToOne
    @NotNull
    public Menu getMenu() {

        return menu;
    }

    public void setMenu(Menu menu) {

        this.menu = menu;
    }

    @ManyToOne
    @NotNull
    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    public Date getCheckoutDate() {

        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {

        this.checkoutDate = checkoutDate;
    }

    @NotNull(message = "Credit card number is required")
    @Size(min = 16, max = 16, message = "Credit card number must 16 digits long")
    @Pattern(regexp = "^\\d*$", message = "Credit card number must be numeric")
    public String getCreditCard() {

        return creditCard;
    }

    public void setCreditCard(String creditCard) {

        this.creditCard = creditCard;
    }

    @Transient
    public String getDescription() {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return menu == null ? null : menu.getName() + ", " + df.format(getCheckinDate()) + " to " + df.format(getCheckoutDate());
    }

    public boolean isSmoking() {

        return smoking;
    }

    public void setSmoking(boolean smoking) {

        this.smoking = smoking;
    }

    public int getBeds() {

        return beds;
    }

    public void setBeds(int beds) {

        this.beds = beds;
    }

    public Byte getStatus() {

        return status;
    }

    public void setStatus(Byte status) {

        this.status = status;
    }

    @NotNull(message = "Credit card name is required")
    @Size(min = 3, max = 70, message = "Credit card name is required")
    public String getCreditCardName() {

        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {

        this.creditCardName = creditCardName;
    }

    public int getCreditCardExpiryMonth() {

        return creditCardExpiryMonth;
    }

    public void setCreditCardExpiryMonth(int creditCardExpiryMonth) {

        this.creditCardExpiryMonth = creditCardExpiryMonth;
    }

    public int getCreditCardExpiryYear() {

        return creditCardExpiryYear;
    }

    public void setCreditCardExpiryYear(int creditCardExpiryYear) {

        this.creditCardExpiryYear = creditCardExpiryYear;
    }

    @Override
    public String toString() {

        return "Booking(" + user + "," + menu + ")";
    }

}
