package org.example.entity;

public class User {
    private int user_id;
    private String login;
    private String password;
    private String email;
    private String ranking;
    private int dateOfReg;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public int getDateOfReg() {
        return dateOfReg;
    }

    public void setDateOfReg(int dateOfReg) {
        this.dateOfReg = dateOfReg;
    }
}
