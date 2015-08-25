//$Id: HotelBooking.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.action;

import javax.ejb.Local;

@Local
public interface MenuBooking {

    public void bookMenu();

    public void setBookingDetails();

    public boolean isBookingValid();

    public void confirm();

    public void cancel();

    public void destroy();

}