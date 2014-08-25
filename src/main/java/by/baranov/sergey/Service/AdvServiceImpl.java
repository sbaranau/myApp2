package by.baranov.sergey.Service;

import by.baranov.sergey.DAO.AdvDao;
import by.baranov.sergey.Entity.Adv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *TODO
 */
@Service
public class AdvServiceImpl implements AdvService {

    private static final Logger LOG = LoggerFactory.getLogger(AdvServiceImpl.class);

    @Autowired
    private AdvDao advDao;

    @Transactional
    public int getNumbersOfAdvs() {
        LOG.debug("get number of advs: Advs Service");
        return advDao.getQuantity();
    }

    @Transactional
    public Long addAdv(Adv adv) {
        LOG.debug("add adv: Advs Service");
        return advDao.add(adv);
    }

    @Transactional
    public Adv viewAdv(Long idUser, Long idAdv) {
        LOG.debug("get adv: Advs Service");
        Adv adv = advDao.findById(idAdv);

        if (!adv.getUser().getIdUser().equals(idUser)) {         //increment views if user isn't author
            Long views = adv.getViews();
            adv.setViews(++views);
        }
        return adv;
    }


    @Transactional
    public List<Adv> getAdvs(int pageToGet, int numberOfAdvsOnPage) {
        LOG.debug("get list of ad: Advs Service");
        return advDao.getAll(pageToGet, numberOfAdvsOnPage);
    }

    @Transactional
    public String updateAdv(Adv adv) {
        LOG.debug("update adv: Advs Service");
        return advDao.update(adv);
    }

    @Transactional
    public boolean delAdv(Adv adv) {
        LOG.debug("delete adv: Advs Service");
        return advDao.delete(adv);
    }

}