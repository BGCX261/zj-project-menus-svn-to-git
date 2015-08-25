//$Id: ChangePassword.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.action;

import javax.ejb.Local;

@Local
public interface ChangePassword {
    public void changePassword();

    public boolean isChanged();

    public String getVerify();

    public void setVerify(String verify);

    public void destroy();
}