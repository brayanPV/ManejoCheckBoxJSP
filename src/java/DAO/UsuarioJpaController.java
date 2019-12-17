/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Prueba;
import DTO.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stive
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getPruebaList() == null) {
            usuario.setPruebaList(new ArrayList<Prueba>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Prueba> attachedPruebaList = new ArrayList<Prueba>();
            for (Prueba pruebaListPruebaToAttach : usuario.getPruebaList()) {
                pruebaListPruebaToAttach = em.getReference(pruebaListPruebaToAttach.getClass(), pruebaListPruebaToAttach.getId());
                attachedPruebaList.add(pruebaListPruebaToAttach);
            }
            usuario.setPruebaList(attachedPruebaList);
            em.persist(usuario);
            for (Prueba pruebaListPrueba : usuario.getPruebaList()) {
                Usuario oldIdusuarioOfPruebaListPrueba = pruebaListPrueba.getIdusuario();
                pruebaListPrueba.setIdusuario(usuario);
                pruebaListPrueba = em.merge(pruebaListPrueba);
                if (oldIdusuarioOfPruebaListPrueba != null) {
                    oldIdusuarioOfPruebaListPrueba.getPruebaList().remove(pruebaListPrueba);
                    oldIdusuarioOfPruebaListPrueba = em.merge(oldIdusuarioOfPruebaListPrueba);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCorreo()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCorreo());
            List<Prueba> pruebaListOld = persistentUsuario.getPruebaList();
            List<Prueba> pruebaListNew = usuario.getPruebaList();
            List<String> illegalOrphanMessages = null;
            for (Prueba pruebaListOldPrueba : pruebaListOld) {
                if (!pruebaListNew.contains(pruebaListOldPrueba)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prueba " + pruebaListOldPrueba + " since its idusuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Prueba> attachedPruebaListNew = new ArrayList<Prueba>();
            for (Prueba pruebaListNewPruebaToAttach : pruebaListNew) {
                pruebaListNewPruebaToAttach = em.getReference(pruebaListNewPruebaToAttach.getClass(), pruebaListNewPruebaToAttach.getId());
                attachedPruebaListNew.add(pruebaListNewPruebaToAttach);
            }
            pruebaListNew = attachedPruebaListNew;
            usuario.setPruebaList(pruebaListNew);
            usuario = em.merge(usuario);
            for (Prueba pruebaListNewPrueba : pruebaListNew) {
                if (!pruebaListOld.contains(pruebaListNewPrueba)) {
                    Usuario oldIdusuarioOfPruebaListNewPrueba = pruebaListNewPrueba.getIdusuario();
                    pruebaListNewPrueba.setIdusuario(usuario);
                    pruebaListNewPrueba = em.merge(pruebaListNewPrueba);
                    if (oldIdusuarioOfPruebaListNewPrueba != null && !oldIdusuarioOfPruebaListNewPrueba.equals(usuario)) {
                        oldIdusuarioOfPruebaListNewPrueba.getPruebaList().remove(pruebaListNewPrueba);
                        oldIdusuarioOfPruebaListNewPrueba = em.merge(oldIdusuarioOfPruebaListNewPrueba);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getCorreo();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCorreo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Prueba> pruebaListOrphanCheck = usuario.getPruebaList();
            for (Prueba pruebaListOrphanCheckPrueba : pruebaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Prueba " + pruebaListOrphanCheckPrueba + " in its pruebaList field has a non-nullable idusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
