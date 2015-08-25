//$Id: HotelSearchingAction.java 8998 2008-09-16 03:08:11Z shane.bryzak@jboss.com $
package com.zj.menus.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;

import com.zj.menus.entity.Seller;

@Stateful
@Name("sellersSearch")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class SellersSearchingAction implements SellersSearching {
    @PersistenceContext
    private EntityManager em;

    private String searchString;
    private int pageSize = 10;
    private int page;
    private boolean nextPageAvailable;

    @DataModel
    private List<Seller> sellers;

    @Override
    public void find() {

        page = 0;
        querySellers();
    }

    @Override
    public void nextPage() {

        page++;
        querySellers();
    }

    private void querySellers() {

        List<Seller> results = em.createQuery("select s from Seller s where lower(s.name) like #{pattern_seller} or lower(s.address) like #{pattern_seller} or lower(s.deliveryKey) like #{pattern_seller}").setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

        nextPageAvailable = results.size() > pageSize;
        if (nextPageAvailable) {
            sellers = new ArrayList<Seller>(results.subList(0, pageSize));
        } else {
            sellers = results;
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
    @Factory(value = "pattern_seller", scope = ScopeType.EVENT)
    public String getSearchPattern() {

        return searchString == null ? "%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
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
