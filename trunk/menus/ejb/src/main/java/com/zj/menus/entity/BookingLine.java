/*
 * JBoss, Home of Professional Open Source
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package com.zj.menus.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.jboss.seam.annotations.Name;

@Entity
@Name("bookingLine")
public class BookingLine implements Serializable {
    private static final long serialVersionUID = 1472993083855636956L;
    private long id;
    private Menu menu;
    private int quantity;
    private Date bookingDate;
    private Booking booking;

    @Id
    @GeneratedValue
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @ManyToOne
    public Booking getBooking() {

        return booking;
    }

    public void setBooking(Booking order) {

        this.booking = order;
    }

    @ManyToOne
    public Menu getMenu() {

        return menu;
    }

    public void setMenu(Menu product) {

        this.menu = product;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public void addQuantity(int howmany) {

        quantity += howmany;
    }
}
