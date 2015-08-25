//$Id: User.java 5579 2007-06-27 00:06:49Z gavin $
package com.zj.menus.entity;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("user")
@Scope(SESSION)
@Table(name = "Customer")
public class User implements Serializable {
    private static final long serialVersionUID = 4818188553954060410L;

    private String username;
    private String password;
    private String name;//真实姓名
    private String tel;
    private String address;
    private String description;//用户描述、备注、个人饮食喜好等一些信息

    //    public User(String name, String password, String username) {
    //
    //        this.name = name;
    //        this.password = password;
    //        this.username = username;
    //    }

    public User() {

    }

    @NotNull
    @Size(max = 15)
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @NotNull
    @Size(min = 5, max = 15)
    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Id
    @Size(min = 4, max = 15)
    @Pattern(regexp = "^\\w*$", message = "not a valid username")
    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    @Size(max = 11)
    @NotNull
    public String getTel() {

        return tel;
    }

    public void setTel(String tel) {

        this.tel = tel;
    }

    @Size(max = 150)
    @NotNull
    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    @Column(length = 1024)
    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @Override
    public String toString() {

        return "User(" + username + ")";
    }
}
