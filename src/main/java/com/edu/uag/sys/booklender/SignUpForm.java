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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;
import java.util.UUID;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "SignUpForm")
@ViewScoped
public class SignUpForm implements Serializable {

    @EJB
    private UagSysUserFacade ejbFacade;
    private UagSysUser uagSysUser;
    private String passwordConfirmation;
    private String message;
    private String activationCode;

    public SignUpForm() {
        setMessage("");
        setUagSysUser(new UagSysUser());
    }

    public UagSysUser getUagSysUser() {
        return uagSysUser;
    }

    public void setUagSysUser(UagSysUser uagSysUser) {
        this.uagSysUser = uagSysUser;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
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

    public void saveUser() {
        try {
            if (getUagSysUser() == null) {
                setMessage("You need fill all fields");
            } else {
                if (getUagSysUser().getUserEmail().equals("")
                        || getUagSysUser().getUserPassword().equals("")
                        || getUagSysUser().getUserName().equals("")
                        || getUagSysUser().getUserCredentialId() == 0
                        || getUagSysUser().getUserCareer().equals("")) {
                    setMessage("You need complete all fields");
                } else {
                    if (mailVerfication()) {
                        getUagSysUser().setUserActive(false);
                        getUagSysUser().setUserActiveCode(UUID.randomUUID().toString());
                        if (getUagSysUser().getUserPassword().equals(
                                passwordConfirmation)) {
                            if (sendEmail()) {
                                setMessage("Verification mail was sent to ".concat(getUagSysUser().getUserEmail()));
                                ejbFacade.create(uagSysUser);
                            }
                        } else {
                            setMessage("Password verification do not match");
                        }

                    } else {
                        setMessage("Please enter a valid UAG WebMail account");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            setMessage("Internal error, please contact to amg3000@hotmail.com to resolve this problem.");
        } finally {
            systemMessage();
            setUagSysUser(new UagSysUser());
        }
    }

    @SuppressWarnings("finally")
    public boolean mailVerfication() {
        boolean verification = false;
        try {
            String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(PATTERN_EMAIL);
            Matcher matcher = pattern.matcher(getUagSysUser().getUserEmail());
            if (matcher.matches()) {
                String arr[] = getUagSysUser().getUserEmail().split("@");
                if (arr[1].equals("edu.uag.mx")) {
                    verification = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            setMessage("Internal error, please contact to amg3000@hotmail.com to resolve this problem.");
        } finally {
            return verification;
        }
    }

    public Boolean sendEmail() {
        final String username = "systemx-web@outlook.com";
        final String password = "AMGamg04021991";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(
                    "systemx-web@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(getUagSysUser().getUserEmail()));
            message.setSubject("Book Lender System Verification Mail");
            message.setText("Activation Code ".concat(getUagSysUser().getUserActiveCode()));
            Transport.send(message);
            System.out.println("EMAIL SENDING PROCESS DONE!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void systemMessage() {
        if (!getMessage().equals("")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("System Info",
                    getMessage()));
        }

    }
}
