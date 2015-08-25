package com.zj.menus.action;

import javax.ejb.Local;

@Local
public interface Authenticator {
    boolean authenticate();
}
