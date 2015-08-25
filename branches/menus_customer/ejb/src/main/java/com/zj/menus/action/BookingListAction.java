//$Id: BookingListAction.java 8748 2008-08-20 12:08:30Z pete.muir@jboss.org $
package com.zj.menus.action;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.zj.menus.entity.Booking;
import com.zj.menus.entity.User;

@Stateful
//开始这里被注释掉了，采用默认的scope，发现输入关键字查询后，点击链接获取到的数据不对，没办法，目前还原后问题消失，原因暂时不详
@Scope(SESSION)
@Name("bookingList")
@Restrict("#{identity.loggedIn}")
@TransactionAttribute(REQUIRES_NEW)
public class BookingListAction implements BookingList, Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    private String searchString;
    private int pageSize = 10;
    private int page;
    private boolean nextPageAvailable;

    @In
    private User user;

    @DataModel
    private List<Booking> bookings;
    @DataModelSelection
    @Out(required = false)
    private Booking booking;

    @Logger
    private Log log;

    @Override
    @Factory
    @Observer("bookingConfirmed")
    public void getBookings() {

        //bookings = em.createQuery("select b from Booking b where b.user.username = :username order by b.checkinDate").setParameter("username", user.getUsername()).getResultList();
        this.setSearchString(null);
        find();
    }

    @Override
    @End
    public void payBooking() {

        log.info("Cancel booking: #{bookingList.booking.id} for #{user.username}");
        Booking cancelled = em.find(Booking.class, booking.getId());
        if (cancelled != null)
            //            em.remove(cancelled);
            cancelled.setStatus((byte) 2);

        find();

        FacesMessages.instance().add("Booking cancelled for confirmation number #0", booking.getId());
    }

    @Override
    @End
    public void cancel() {

        log.info("Cancel booking: #{bookingList.booking.id} for #{user.username}");
        Booking cancelled = em.find(Booking.class, booking.getId());
        if (cancelled != null)
            //            em.remove(cancelled);
            cancelled.setStatus((byte) 0);

        find();

        FacesMessages.instance().add("Booking cancelled for confirmation number #0", booking.getId());
    }

    //此方法仅仅是触发一个会话，暂时没有什么作用，参数注入其实通过@DataModelSelection实现的
    @Override
    @Begin(join = true)
    public void selectBooking() {

    }

    //此方法用于检查登录重定向时booking里的属性是否都为空
    @Override
    public boolean isBookingNull() {

        boolean result = false;
        if (this.booking == null) {
            result = true;
        } else if (this.booking.getMenu() == null) {
            result = true;
        } else if (this.booking.getMenu().getName() == null) {
            result = true;
        }
        return result;
    }

    @Override
    public void find() {

        page = 0;
        queryBooks();
    }

    @Override
    public void nextPage() {

        page++;
        queryBooks();
    }

    private void queryBooks() {

        //如果查询语句写成这样，username的条件就会失效，仅仅加个括号就解决问题
        //List<Booking> results = em.createQuery("select o from Booking o where o.user.username = :username and lower(o.menu.name) like #{pattern_booking} or lower(o.menu.description) like #{pattern_booking} or lower(o.menu.seller.displayName) like #{pattern_booking} or lower(o.menu.seller.address) like #{pattern_booking} order by o.checkinDate").setParameter("username", user.getUsername()).setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();
        List<Booking> results = em.createQuery("select o from Booking o where o.user.username = :username and (lower(o.menu.name) like #{pattern_booking} or lower(o.menu.description) like #{pattern_booking} or lower(o.menu.seller.displayName) like #{pattern_booking} or lower(o.menu.seller.address) like #{pattern_booking}) order by o.checkinDate").setParameter("username", user.getUsername()).setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

        nextPageAvailable = results.size() > pageSize;
        if (nextPageAvailable) {
            bookings = new ArrayList<Booking>(results.subList(0, pageSize));
        } else {
            bookings = results;
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
    @Factory(value = "pattern_booking", scope = ScopeType.EVENT)
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
