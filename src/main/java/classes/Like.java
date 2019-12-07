package classes;

public class Like {

    private final int userFrom;
    private final int userTo;
    private final boolean like;

    public Like(int userFrom, int userTo, boolean like) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.like = like;
    }

    public int getUserFrom() {
        return userFrom;
    }

    public int getUserTo() {
        return userTo;
    }

    public boolean isLiked() {
        return like;
    }
}
