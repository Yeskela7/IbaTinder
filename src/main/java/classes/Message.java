package classes;

public class Message {

    private int from;
    private int to;
    private String content;

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    public Message(int from, int to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("from:'").append(from).append('\'');
        sb.append("to:'").append(to).append('\'');
        sb.append("content:'").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
