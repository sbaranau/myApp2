package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Adv;

import java.util.List;

/**
 */
public interface AdvDao {

    public int getQuantity();

    public Adv findById(Long advId);

    public List<Adv> getAll(int pageToGet, int numPerPage);

    public Long add(Adv adv);

    public String update(Adv adv);

    public boolean delete(Adv adv);

    public int getCommentCount(long advId);

    public boolean increaseCommentCount(long advId);
}
