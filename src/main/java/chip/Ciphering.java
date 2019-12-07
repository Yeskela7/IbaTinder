package chip;

public class Ciphering {

    public Ciphering() {
    }

    public static int passwordCrypt(String password) {
        String salt = "sfv13erv54d";
        int prime = 997259;
        return (password.hashCode() ^ salt.hashCode()) % prime;
    }

}
