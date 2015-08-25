/*
 * JBoss, Home of Professional Open Source
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package com.zj.menus.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zj.menus.entity.BookingLine;
import com.zj.menus.entity.Menu;

public interface ShoppingCart {
    public boolean getIsEmpty();

    public void addProduct(Menu menu, int quantity);

    public List<BookingLine> getCart();

    @SuppressWarnings("unchecked")
    public Map getCartSelection();

    public BigDecimal getTotal();

    public void updateCart();

    public void resetCart();

    public void destroy();

    int getTotalQuantity();
}
