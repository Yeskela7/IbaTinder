package classes;

public class User {

    private final String email;
    private final String name;
    private final String surname;
    private final String picURL;
    private final int age;

    public User(String email, String name, String surname, String picURL, int age) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.picURL = picURL;
        this.age = age;
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
}