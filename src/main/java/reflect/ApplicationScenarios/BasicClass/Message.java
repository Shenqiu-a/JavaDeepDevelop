package reflect.ApplicationScenarios.BasicClass;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/411:06
 */

public class Message {
    private String content;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public void printContent() {
        System.out.println(this.content);
    }

    public void printContent(String content) {
        System.out.println(content);
    }
}
