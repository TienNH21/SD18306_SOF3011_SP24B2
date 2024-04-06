package repositories;

import entities.MauSac;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class MauSacRepository {
    private Session hSession;

    public MauSacRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
    }

    public void insert(MauSac ms)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(ms);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public void update(MauSac ms)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(ms);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public void delete(MauSac ms)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.remove(ms);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public List<MauSac> findAll()
    {
        // SELECT * FROM MauSac
        String hql = "SELECT entity FROM MauSac entity";
        Query q = this.hSession.createQuery(hql, MauSac.class);
        List<MauSac> result = q.getResultList();

        return result;
    }

    public MauSac findById(int id)
    {
        return this.hSession.find(MauSac.class, id);
    }

    public MauSac getByIdAndMa(Integer id, String ma) {
        String hql = "SELECT entity FROM MauSac entity WHERE entity.id <> ?1 AND entity.ma = ?2";
        TypedQuery<MauSac> q = this.hSession.createQuery(hql, MauSac.class);
        q.setParameter(1, id);
        q.setParameter(2, ma);
        return q.getSingleResult();
    }
}
