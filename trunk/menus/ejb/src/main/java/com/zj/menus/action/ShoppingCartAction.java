/*
 * JBoss, Home of Professional Open Source
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package com.zj.menus.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.zj.menus.entity.Booking;
import com.zj.menus.entity.BookingLine;
import com.zj.menus.entity.Menu;

@Stateful
@Name("cart")
@Scope(SESSION)
public class ShoppingCartAction implements ShoppingCart, Serializable {
    private static final long serialVersionUID = -3103437005432866062L;

    @PersistenceContext
    EntityManager em;

    Booking cartOrder = new Booking();
    Map<Menu, Boolean> cartSelection = new HashMap<Menu, Boolean>();

    @Override
    public List<BookingLine> getCart() {

        return cartOrder.getBookingLines();
    }

    @Override
    public boolean getIsEmpty() {

        return cartOrder.isEmpty();
    }

    @Override
    public void addProduct(Menu product, int quantity) {

        cartOrder.addProduct(product, quantity);
        cartOrder.calculateTotals();
    }

    @Override
    public int getTotalQuantity() {

        return cartOrder.getTotalQuantity();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map getCartSelection() {

        return cartSelection;
    }

    @Override
    public BigDecimal getTotal() {

        return cartOrder.getTotalAmount();
    }

    @Override
    public void updateCart() {

        List<BookingLine> newLines = new ArrayList<BookingLine>();

        for (BookingLine line : cartOrder.getBookingLines()) {
            if (line.getQuantity() > 0) {
                Boolean selected = cartSelection.get(line);
                if (selected == null || !selected) {
                    newLines.add(line);
                }
            }
        }
        cartOrder.setBookingLines(newLines);
        cartOrder.calculateTotals();

        cartSelection = new HashMap<Menu, Boolean>();
    }

    @Override
    public void resetCart() {

        cartOrder = new Booking();
    }

    @Override
    @Remove
    @Destroy
    public void destroy() {

    }

}
