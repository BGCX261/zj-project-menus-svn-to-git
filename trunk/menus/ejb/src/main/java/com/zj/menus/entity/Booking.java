//$Id: Booking.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    private List<BookingLine> bookingLines = new ArrayList<BookingLine>();
    private BigDecimal netAmount = BigDecimal.ZERO;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    private int totalQuantity;

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

    @Transient
    public boolean isEmpty() {

        return (bookingLines == null) || (bookingLines.size() == 0);
    }

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    public List<BookingLine> getBookingLines() {

        return bookingLines;
    }

    @Column(name = "NETAMOUNT", nullable = false, precision = 12, scale = 2)
    public BigDecimal getNetAmount() {

        return netAmount;
    }

    public void setNetAmount(BigDecimal amount) {

        this.netAmount = amount;
    }

    @Column(name = "TOTALAMOUNT", nullable = false, precision = 12, scale = 2)
    public BigDecimal getTotalAmount() {

        return totalAmount;
    }

    public void setTotalAmount(BigDecimal amount) {

        this.totalAmount = amount;
    }

    public void setBookingLines(List<BookingLine> lines) {

        this.bookingLines = lines;
    }

    public void addProduct(Menu product, int quantity) {

        for (BookingLine line : bookingLines) {
            if (product.getId() == line.getMenu().getId()) {
                line.addQuantity(quantity);
                return;
            }
        }

        BookingLine line = new BookingLine();
        line.setMenu(product);
        line.setQuantity(quantity);
        line.setBooking(this);

        bookingLines.add(line);
    }

    public void removeProduct(Menu product) {

        for (BookingLine line : bookingLines) {
            if (product.getId() == line.getMenu().getId()) {
                bookingLines.remove(line);
                return;
            }
        }
    }

    @Transient
    //获取餐车中所有菜谱的数量
    public int getTotalQuantity() {

        return this.totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {

        this.totalQuantity = totalQuantity;
    }

    public void calculateTotals() {

        BigDecimal total = BigDecimal.ZERO;
        int totalQuantity = 0;

        //int index = 1;
        for (BookingLine line : bookingLines) {
            //line.setPosition(index++);
            total = total.add(line.getMenu().getPrice().multiply(new BigDecimal(line.getQuantity())));
            totalQuantity += line.getQuantity();
        }

        setNetAmount(total);

        //setTax(round(getNetAmount().multiply(TAX_RATE)));
        setTotalAmount(getNetAmount());
        setTotalQuantity(totalQuantity);
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
