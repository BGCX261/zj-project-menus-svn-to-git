//$Id: ChangePasswordAction.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.action;

import static org.jboss.seam.ScopeType.EVENT;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.zj.menus.entity.User;

@Stateful
@Scope(EVENT)
@Name("userSetting")
@Restrict("#{identity.loggedIn}")
public class UserSettingAction implements UserSetting {
    @In
    @Out
    private User user;

    @PersistenceContext
    private EntityManager em;

    private boolean changed;

    @In
    private FacesMessages facesMessages;

    @Override
    public void update() {

        user = em.merge(user);
        facesMessages.add("信息更新成功！");
        changed = true;

    }

    @Override
    public boolean isChanged() {

        return changed;
    }

    @Override
    @Remove
    @Destroy
    public void destroy() {

    }
}
