package com.edu.uag.sys.booklender.jsf;

import com.edu.uag.sys.booklender.entities.UagSysInventory;
import com.edu.uag.sys.booklender.jsf.util.JsfUtil;
import com.edu.uag.sys.booklender.jsf.util.JsfUtil.PersistAction;
import com.edu.uag.sys.booklender.session.UagSysInventoryFacade;

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

@Named("uagSysInventoryController")
@SessionScoped
public class UagSysInventoryController implements Serializable {

    @EJB
    private com.edu.uag.sys.booklender.session.UagSysInventoryFacade ejbFacade;
    private List<UagSysInventory> items = null;
    private UagSysInventory selected;

    public UagSysInventoryController() {
    }

    public UagSysInventory getSelected() {
        return selected;
    }

    public void setSelected(UagSysInventory selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UagSysInventoryFacade getFacade() {
        return ejbFacade;
    }

    public UagSysInventory prepareCreate() {
        selected = new UagSysInventory();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UagSysInventoryCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UagSysInventoryUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UagSysInventoryDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UagSysInventory> getItems() {
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

    public UagSysInventory getUagSysInventory(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<UagSysInventory> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UagSysInventory> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<UagSysInventory> getItemsEbooks() {
        return getFacade().getEm().createNamedQuery("UagSysInventory.findByResourceType").setParameter("resourceType", "EBook").getResultList();
    }

    public List<UagSysInventory> getItemsBooks() {
        return getFacade().getEm().createNamedQuery("UagSysInventory.findByResourceType").setParameter("resourceType", "Book").getResultList();
    }

    @FacesConverter(forClass = UagSysInventory.class)
    public static class UagSysInventoryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UagSysInventoryController controller = (UagSysInventoryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "uagSysInventoryController");
            return controller.getUagSysInventory(getKey(value));
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
            if (object instanceof UagSysInventory) {
                UagSysInventory o = (UagSysInventory) object;
                return getStringKey(o.getResourceId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UagSysInventory.class.getName()});
                return null;
            }
        }

    }

}
