package classes;

import java.util.Objects;

public class User {

    private final String email;
    private final String name;
    private final String surname;
    private final String picURL;
    private final int age;
    private final int password;

    public User(String email, String name, String surname, String picURL, int age, int password) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.picURL = picURL;
        this.age = age;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPicURL() {
        return picURL;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", picURL='" + picURL + '\'' +
                ", age=" + age +
                '}';
    }

    public int getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() == o.hashCode()) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}