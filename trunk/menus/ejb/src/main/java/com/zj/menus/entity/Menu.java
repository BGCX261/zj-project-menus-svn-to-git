package com.zj.menus.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.seam.annotations.Name;

@Entity
@Name("menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = -7077526062824323362L;
    private Long id;
    private String name;
    private String description;

    private String imgUrl;
    private Date insertDate;
    private Date modifyDate;
    private Byte status;
    private BigDecimal price;

    private Seller seller;

    @Id
    @GeneratedValue
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Size(max = 50)
    @NotNull
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Column(length = 1024)
    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @Column(length = 2)
    public Byte getStatus() {

        return status;
    }

    public void setStatus(byte status) {

        this.status = status;
    }

    @Column(precision = 6, scale = 2)
    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    @Column(length = 256)
    public String getImgUrl() {

        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getInsertDate() {

        return insertDate;
    }

    public void setInsertDate(Date datetime) {

        this.insertDate = datetime;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifyDate() {

        return modifyDate;
    }

    public void setModifyDate(Date datetime) {

        this.modifyDate = datetime;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    public Seller getSeller() {

        return seller;
    }

    public void setSeller(Seller seller) {

        this.seller = seller;
    }

    @Override
    public String toString() {

        return "Menu(" + name + "," + description + "," + price + "," + seller.getName() + ")";
    }

}
