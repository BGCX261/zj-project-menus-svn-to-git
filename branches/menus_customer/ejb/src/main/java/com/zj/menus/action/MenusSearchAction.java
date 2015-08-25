//$Id: HotelSearchingAction.java 8998 2008-09-16 03:08:11Z shane.bryzak@jboss.com $
package com.zj.menus.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

import com.zj.menus.entity.Menu;

@Stateful
@Name("menusSearch")
@Scope(ScopeType.SESSION)
public class MenusSearchAction implements MenusSearch {
    @PersistenceContext
    private EntityManager em;

    private String searchString;
    private int pageSize = 10;
    private int page;
    private boolean nextPageAvailable;

    @Out(required = false)
    private Menu menu;

    @DataModel
    private List<Menu> menus;

    @Override
    @Factory("menus")
    public void find() {

        page = 0;
        queryMenus();
    }

    @Override
    public void nextPage() {

        page++;
        queryMenus();
    }

    private void queryMenus() {

        List<Menu> results = em.createQuery("select o from Menu o where lower(o.name) like #{pattern_menu} or lower(o.description) like #{pattern_menu}").setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

        nextPageAvailable = results.size() > pageSize;
        if (nextPageAvailable) {
            menus = new ArrayList<Menu>(results.subList(0, pageSize));
        } else {
            menus = results;
        }
    }

    @Override
    public boolean isNextPageAvailable() {

        return nextPageAvailable;
    }

    @Override
    public int getPageSize() {

        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {

        this.pageSize = pageSize;
    }

    @Override
    @Factory(value = "pattern_menu", scope = ScopeType.EVENT)
    public String getSearchPattern() {

        return searchString == null ? "%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
    }

    @Override
    @Begin(join = true)
    public void selectMenu(Menu selectedMenu) {

        this.menu = selectedMenu;
    }

    //此方法用于检查登录重定向时menu里的属性是否都为空
    @Override
    public boolean isMenuNull() {

        boolean result = false;
        if (this.menu == null) {
            result = true;
        } else if (this.menu.getName() == null) {
            result = true;
        }
        return result;
    }

    @Override
    public void cancel() {

    }

    @Override
    public String getSearchString() {

        return searchString;
    }

    @Override
    public void setSearchString(String searchString) {

        this.searchString = searchString;
    }

    @Override
    @Remove
    @Destroy
    public void destroy() {

    }

}
