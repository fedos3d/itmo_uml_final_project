package passenger;

import java.util.Date;

public class Passenger {
    private String name;
    private String surName;
    private String passport;
    private Date birthDate;
    private Sex sex;


    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", passport='" + passport + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                '}';
    }

    public String getPassport() {
        return passport;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Passenger(String name, String surName, String passport, Date birthDate, Sex sex) {
        this.name = name;
        this.surName = surName;
        this.passport = passport;
        this.birthDate = birthDate;
        this.sex = sex;
    }

}
