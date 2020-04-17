package model;

public class Category {
    private int idCategory;
    private String nameCategory;
    private String description;
    private int noDepartment;

    /**
     * This is the constructor of the Category
     *
     * @param idCategory   - the id of the category
     * @param nameCategory - the name of the category
     * @param description  - the description of the category
     * @param noDepartment - the number of the department
     */
    public Category(int idCategory, String nameCategory, String description, int noDepartment) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.description = description;
        this.noDepartment = noDepartment;
    }

    /**
     * This is the second constructor of the Category
     *
     * @param nameCategory - the name of the category
     * @param description  - the description of the category
     * @param noDepartment - the number of the department
     */
    public Category(String nameCategory, String description, int noDepartment) {
        this.nameCategory = nameCategory;
        this.description = description;
        this.noDepartment = noDepartment;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoDepartment() {
        return noDepartment;
    }

    public void setNoDepartment(int noDepartment) {
        this.noDepartment = noDepartment;

    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", nameCategory='" + nameCategory + '\'' +
                ", description='" + description + '\'' +
                ", noDepartment=" + noDepartment +
                '}';
    }
}
