/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uag.sys.booklender.session;

import com.edu.uag.sys.booklender.entities.UagSysUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Smirnoff
 */
@Stateless
public class UagSysUserFacade extends AbstractFacade<UagSysUser> {

    @PersistenceContext(unitName = "com.edu.uag.sys_BookLender_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UagSysUserFacade() {
        super(UagSysUser.class);
    }
    
}
