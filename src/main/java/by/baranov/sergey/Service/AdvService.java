package by.baranov.sergey.Service;

import by.baranov.sergey.Entity.Adv;

import java.util.List;

/**
 *TODO
 */
public interface AdvService {

    public int getNumbersOfAdvs();

    public Long addAdv(Adv adv);

    public Adv viewAdv(Long idUser, Long idAdv);

    public List<Adv> getAdvs(int pageToGet, int numberOfAdvsOnPage);

    public String updateAdv(Adv adv);

    public boolean delAdv(Adv adv);


}
