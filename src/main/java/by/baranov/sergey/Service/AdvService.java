package by.baranov.sergey.Service;

import by.baranov.sergey.Entity.Adv;

import java.util.List;

/**
 *TODO
 */
public interface AdvService {

    public int getNumbersOfAdvs();

    public int addAdv(Adv adv);

    public Adv viewAdv(Long idUser, int idAdv);

    public List<Adv> getAdvs(int pageToGet, int numberOfAdvsOnPage);

    public String updateAdv(Adv adv);

    public boolean delAdv(Adv adv);


}
