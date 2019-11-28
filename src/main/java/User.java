public class User {

    private final String nickname;
    private final String name;
    private final String surname;
    private final String picURL;
    private final int age;

    public User(String nickname, String name, String surname, String picURL, int age) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.picURL = picURL;
        this.age = age;
    }

    public String getNickname() {
        return nickname;
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
                "nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", picURL='" + picURL + '\'' +
                ", age=" + age +
                '}';
    }
}