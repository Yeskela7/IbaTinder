package classes;

public class Like {

    private final int userFrom;
    private final int userTo;
    private final int like;

    public Like(int userFrom, int userTo, int like) {
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

    public int isLiked() {
        return like;
    }
}
