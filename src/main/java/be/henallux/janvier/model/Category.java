package be.henallux.janvier.model;

public class Category {

    private Integer id;
    private String code;
    private String nom;

    // Constructeurs
    public Category() {
    }

    public Category(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}


