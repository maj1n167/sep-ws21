package ude.sep.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Menu implements Serializable {


    @Id
    private long menuId;

    @OneToMany
    private List<Kategorie> kategories;



    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }


    public List<Kategorie> getKategories() {
        return kategories;
    }

    public void setKategories(List<Kategorie> kategories) {
        this.kategories = kategories;
    }




    public Menu(long menuId, String name, String beschreibung, String kategorie, double preis, String bild) {
        this.menuId = menuId;
    }

    public Menu() {

    }
}
