//$Id: Register.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.action;

import javax.ejb.Local;

@Local
public interface Register {
    public void register();

    public void registerSeller();

    public void invalid();

    public String getVerify();

    public void setVerify(String verify);

    public boolean isRegistered();

    public void destroy();

    boolean isRegisteredSeller();
}