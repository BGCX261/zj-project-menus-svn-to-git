//$Id: BookingList.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.action;

import javax.ejb.Local;

@Local
public interface BookingList {
    public void getBookings();

    public void cancel();

    public void destroy();

    void find();

    void nextPage();

    boolean isNextPageAvailable();

    int getPageSize();

    void setPageSize(int pageSize);

    String getSearchPattern();

    String getSearchString();

    void setSearchString(String searchString);

    void selectBooking();

    void payBooking();

    boolean isBookingNull();
}