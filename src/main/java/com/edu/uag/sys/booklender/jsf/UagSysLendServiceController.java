package com.edu.uag.sys.booklender.jsf;

import com.edu.uag.sys.booklender.entities.UagSysLendService;
import com.edu.uag.sys.booklender.jsf.util.JsfUtil;
import com.edu.uag.sys.booklender.jsf.util.JsfUtil.PersistAction;
import com.edu.uag.sys.booklender.session.UagSysLendServiceFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("uagSysLendServiceController")
@SessionScoped
public class UagSysLendServiceController implements Serializable {

    @EJB
    private com.edu.uag.sys.booklender.session.UagSysLendServiceFacade ejbFacade;
    private List<UagSysLendService> items = null;
    private UagSysLendService selected;

    public UagSysLendServiceController() {
    }

    public UagSysLendService getSelected() {
        return selected;
    }

    public void setSelected(UagSysLendService selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UagSysLendServiceFacade getFacade() {
        return ejbFacade;
    }

    public UagSysLendService prepareCreate() {
        selected = new UagSysLendService();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UagSysLendServiceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UagSysLendServiceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UagSysLendServiceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UagSysLendService> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UagSysLendService getUagSysLendService(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<UagSysLendService> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UagSysLendService> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UagSysLendService.class)
    public static class UagSysLendServiceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UagSysLendServiceController controller = (UagSysLendServiceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "uagSysLendServiceController");
            return controller.getUagSysLendService(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UagSysLendService) {
                UagSysLendService o = (UagSysLendService) object;
                return getStringKey(o.getLendServiceId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UagSysLendService.class.getName()});
                return null;
            }
        }

    }

}
