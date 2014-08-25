package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Adv;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository
public class AdvDaoImpl implements AdvDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOG = LoggerFactory.getLogger(AdvDaoImpl.class);


    /**
     * get quantity of advs in DB
     *
     * @return quantity of Adv
     */
    @Override
    public int getQuantity() {
        LOG.debug("get Quantity of Advs");
        return ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from Adv ").uniqueResult()).intValue();
    }


    /**
     * add new adv in DB
     *
     * @param adv - adv to add
     * @return id of new adv
     */
    @Override
    public Long add(Adv adv) {             // add adv in the DB
        LOG.debug("Adding adv");
        return (Long) sessionFactory.getCurrentSession().save(adv);
    }

    /**
     * find adv in DB by id
     *
     * @param advId adv's id
     * @return Adv
     */
    @Override
    public Adv findById(Long advId) {
        LOG.debug("find Adv By Ip dao");
        return (Adv) sessionFactory.getCurrentSession().get(Adv.class, advId);
    }


    /**
     * get some adv from DB using first adv's number and quantity
     *
     * @param pageNumber     number of page
     * @param quantityOnPage quantity on page
     * @return List of advs
     */
    @Override
    public List<Adv> getAll(int pageNumber, int quantityOnPage) {
        LOG.debug("In findAll dao");
        List<Adv> listOfAdvs = sessionFactory.getCurrentSession().createCriteria(Adv.class).setFirstResult((pageNumber - 1) * quantityOnPage).
                setMaxResults(quantityOnPage).addOrder(Order.desc("idAdv")).list();
        /*
        another variant !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Query q = sessionFactory.getCurrentSession().createQuery("from Adv order by idAdv desc");
        q.setFirstResult((pageToGet - 1) * numberOfAdvsOnPage);
        q.setMaxResults(numberOfAdvsOnPage);
        List<Adv> listOfAdvs = q.list();
        */
        return listOfAdvs;
    }


    /**
     * Updates Adv
     *
     * @param adv adv to update
     * @return update reuslt: "ok" for success, "Error" otherwise
     */
    @Override
    public String update(Adv adv) {
        LOG.debug("I in update dao");
        try {
            sessionFactory.getCurrentSession().update(adv);
            return "ok";
        } catch (Exception ex) {
            LOG.warn("update failed");
            return "Error";
        }
    }

    /**
     * delete adv from DB
     *
     * @param adv - adv what need to delete
     * @return operation result
     */
    @Override
    public boolean delete(Adv adv) {
        LOG.debug("user in adv delete dao");
        try {
            sessionFactory.getCurrentSession().delete(adv);
        } catch (HibernateOptimisticLockingFailureException exp) {
            LOG.warn("delete failed");
            return false;
        }
        return true;
    }

    @Override
    public int getCommentCount(long advId) {
        return 0;
    }

    @Override
    public boolean increaseCommentCount(long advId) {
        Adv adv = findById(advId);
        adv.setCommentsCount(adv.getCommentsCount() + 1);
        return "ok".equals(update(adv));
    }

}
