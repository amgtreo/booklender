/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uag.sys.booklender;

/**
 *
 * @author Smirnoff
 */
import com.edu.uag.sys.booklender.entities.UagSysUser;
import com.edu.uag.sys.booklender.session.UagSysUserFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "index")
@SessionScoped
public class Index implements Serializable {

    @EJB
    private UagSysUserFacade ejbFacade;
    private String user;
    private String password;
    private String page;
    private String message;
    private String activationCode;
    private UagSysUser uagUser;

    public Index() {
        setUser(new String(""));
        setPassword(new String(""));
        setPage(new String(""));
        setMessage(new String(""));
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UagSysUserFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UagSysUserFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String authentification() {
        Boolean verified = false;
        try {
            if (user != null && password != null) {
                if (user.equals("") && password.equals("")) {
                    setMessage("e-mail and password required.");
                } else {

                    List<UagSysUser> listUagUser = getEjbFacade().findAll();
                    if (listUagUser.isEmpty()) {
                        setMessage("No users found");
                    } else {
                        uagUser = listUagUser.get(0);
                        if (uagUser.getUserEmail().equals(user) && uagUser.getUserPassword().equals(password)) {
                            if (uagUser.getUserActive()) {
                                setPage("template.xhtml?faces-redirect=true");
                            } else {
                                setPage("activation.xhtml?faces-redirect=true");
                            }
                        } else {
                            setMessage("Wrong user");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            setMessage("Internal error, please contact to amg3000@hotmail.com to resolve this problem.");
        } finally {
            systemMessage();
            //setUser(new String(""));
            //setPassword(new String(""));
            setMessage(new String(""));
            return getPage();
        }
    }

    public void systemMessage() {
        if (!getMessage().equals("")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("System Info",
                    getMessage()));
        }

    }

    public String verifyActivationCode() {
        if (uagUser.getUserActiveCode().equals(activationCode)) {
            uagUser.setUserActive(true);
            ejbFacade.edit(uagUser);
            setPage("template.xhtml?faces-redirect=true");
        } else {
            setMessage("Wrong activation code.");
            setPage("");
            systemMessage();
        }
        return getPage();
    }

    public String goBackToIndex() {
        return "index.xhtml?faces-redirect=true";
    }
}
