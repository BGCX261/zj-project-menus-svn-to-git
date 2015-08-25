//$Id: RegisterAction.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.action;

import static org.jboss.seam.ScopeType.EVENT;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import com.zj.menus.entity.Seller;
import com.zj.menus.entity.User;

@Stateful
@Scope(EVENT)
@Name("register")
public class RegisterAction implements Register {
    @In(required = false)
    private User user;

    @In(required = false)
    private Seller seller;

    @PersistenceContext
    private EntityManager em;

    @In
    private FacesMessages facesMessages;

    private String verify;

    private boolean registered;

    private boolean registeredSeller;

    @Override
    public void register() {

        if (user.getPassword().equals(verify)) {
            List existing = em.createQuery("select u.username from User u where u.username=#{user.username}").getResultList();
            if (existing.size() == 0) {
                em.persist(user);
                facesMessages.add("Successfully registered as #{user.username}");
                registered = true;
            } else {
                facesMessages.addToControl("username", "Username #{user.username} already exists");
            }
        } else {
            facesMessages.addToControl("verify", "Re-enter your password");
            verify = null;
        }
    }

    @Override
    public void registerSeller() {

        if (seller.getPwd().equals(verify)) {
            List existing = em.createQuery("select u.name from Seller u where u.name=#{seller.name}").getResultList();
            if (existing.size() == 0) {
                em.persist(seller);
                facesMessages.add("Successfully registered as #{seller.name}");
                registeredSeller = true;
            } else {
                facesMessages.addToControl("username", "name #{seller.name} already exists");
            }
        } else {
            facesMessages.addToControl("verify", "Re-enter your password");
            verify = null;
        }
    }

    @Override
    public void invalid() {

        facesMessages.add("Please try again");
    }

    @Override
    public boolean isRegistered() {

        return registered;
    }

    @Override
    public boolean isRegisteredSeller() {

        return registeredSeller;
    }

    @Override
    public String getVerify() {

        return verify;
    }

    @Override
    public void setVerify(String verify) {

        this.verify = verify;
    }

    @Override
    @Remove
    @Destroy
    public void destroy() {

    }
}
