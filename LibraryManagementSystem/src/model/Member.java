package model;

public class Member {

    private int id_member;
    private final String name;
    private final String email;

    public Member (String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Member (int id_member, String name, String email) {
        this.id_member = id_member;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public int getId_member() {
        return id_member;
    }

    @Override
    public String toString() {
        return "Id: " + id_member + "Name: " + name + " ; Email: " + email;
    }

}
