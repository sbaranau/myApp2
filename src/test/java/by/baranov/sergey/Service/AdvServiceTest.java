package by.baranov.sergey.Service;

import by.baranov.sergey.DAO.AdvDao;
import by.baranov.sergey.DAO.AdvDaoImpl;
import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.User;
import junit.framework.Assert;
import org.junit.Test;

public class AdvServiceTest {
    @Test
    public void testViewAdv() {
        AdvDao advDaoTest = new AdvDaoImpl() {
            @Override
            public Adv findById(Long advId) {
                Adv adv = new Adv();
                adv.setViews(0L);
                User user = new User(2L);
                adv.setUser(user);

                return adv;
            }
        };

        AdvServiceImpl advService = new AdvServiceImpl();
        advService.advDao = advDaoTest;

        // Viewing with user who didn't create this adv
        Adv adv1 = advService.viewAdv(1L, 101L);
        Assert.assertEquals(adv1.getViews(), (Long) 1L);

        // Viewing with user who created this adv
        Adv adv2 = advService.viewAdv(2L, 101L);
        Assert.assertEquals(adv2.getViews(), (Long) 0L);
    }
}
