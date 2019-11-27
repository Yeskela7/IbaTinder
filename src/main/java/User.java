public class User {

    private final String uid;
    private final String fullName;
    private final int age;

    public User(String uid, String fullName, int age) {
        this.uid = uid;
        this.fullName = fullName;
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + fullName + ", UID: " + uid + ", Age: "+ age;
    }

}