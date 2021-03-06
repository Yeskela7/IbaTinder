package classes;

import java.util.Objects;

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

    @Override
    public boolean equals(Object that) {
        if (this.hashCode() == that.hashCode()) return false;
        if (this == that) return true;
        if (getClass() != that.getClass()) return false;
        Like like = (Like) that;
        return userFrom == like.userFrom &&
                userTo == like.userTo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userFrom, userTo);
    }
}
