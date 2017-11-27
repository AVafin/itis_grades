package database;

public class Student {
    private int id;
    private String name;
    private int budget;
    private String group;

    public Student() {
    }

    public Student(int id, String name, int budget, String group) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
