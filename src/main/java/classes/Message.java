package classes;

import java.util.Objects;

public class Message {

    private int from;
    private int to;
    private String content;
    private long date;
    private String dateString;

    public Message(int from, int to, String content, long date) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = date;
        this.dateString = getDateString();
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    public long getDate() {
        return date;
    }

    private String getDateString() {
        return DateConverter.format(date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", to=" + to +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Message message = (Message) that;
        return from == message.from &&
                to == message.to &&
                Objects.equals(content, message.content) &&
                Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, content, date);
    }
}
