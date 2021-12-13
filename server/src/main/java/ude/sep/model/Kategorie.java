package ude.sep.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Kategorie  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kategorieId;

    private String kategorie;

    private Long menuId;


    @OneToMany
    private List<Food> foods;

    public Kategorie(Long kategorieId, String kategorie, Long menuId, List<Food> foods) {
        this.kategorieId = kategorieId;
        this.kategorie = kategorie;
        this.menuId = menuId;
        this.foods = foods;
    }


    public Kategorie() {

    }


    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setKategorieId(Long kategorieId) {
        this.kategorieId = kategorieId;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Long getKategorieId() {
        return kategorieId;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
