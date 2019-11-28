package classes;

public class Message {

    private String from;
    private String to;
    private String content;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    public Message(String from, String to, String content) {
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
