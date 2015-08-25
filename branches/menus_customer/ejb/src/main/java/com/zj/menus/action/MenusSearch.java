//$Id: HotelSearching.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.action;

import javax.ejb.Local;

import com.zj.menus.entity.Menu;

@Local
public interface MenusSearch {
    public int getPageSize();

    public void setPageSize(int pageSize);

    public String getSearchString();

    public void setSearchString(String searchString);

    public String getSearchPattern();

    public void selectMenu(Menu selectedMenu);

    public void find();

    public void nextPage();

    public boolean isNextPageAvailable();

    public void destroy();

    void cancel();

    boolean isMenuNull();

}