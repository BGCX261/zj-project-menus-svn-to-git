package com.zj.menus.entity;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("seller")
@Scope(SESSION)
public class Seller implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8599240719517324094L;

    private String name;//用于后台管理登陆
    private String pwd;
    private String displayName;//用于店名
    private String address;
    private String tel;
    private String description;
    private String logoUrl;//商家图标
    private String deliveryKey;//派送范围关键字
    private Date createDate;
    private Date modifyDate;
    private Byte status;

    @Id
    @Size(min = 4, max = 15)
    @Pattern(regexp = "^\\w*$", message = "not a valid username")
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Size(min = 5, max = 10)
    @NotNull
    public String getPwd() {

        return pwd;
    }

    public void setPwd(String pwd) {

        this.pwd = pwd;
    }

    @Size(max = 50)
    @NotNull
    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    @Size(max = 150)
    @NotNull
    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    @Size(max = 11)
    @NotNull
    public String getTel() {

        return tel;
    }

    public void setTel(String tel) {

        this.tel = tel;
    }

    @Column(length = 1024)
    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @Column(length = 256)
    public String getLogoUrl() {

        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {

        this.logoUrl = logoUrl;
    }

    @Column(length = 1024)
    @NotNull
    public String getDeliveryKey() {

        return deliveryKey;
    }

    public void setDeliveryKey(String deliveryKey) {

        this.deliveryKey = deliveryKey;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {

        return createDate;
    }

    public void setCreateDate(Date createDate) {

        this.createDate = createDate;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifyDate() {

        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {

        this.modifyDate = modifyDate;
    }

    @Column(length = 2)
    public Byte getStatus() {

        return status;
    }

    public void setStatus(Byte status) {

        this.status = status;
    }

}
