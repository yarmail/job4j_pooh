/**
 * Resp - ответ от сервиса.
 * (responce - отклик)
 * text - текст ответа.
 * status  - это HTTP response status codes.
 * И, для примера, что если запрос прошел,
 * то статус = 200, а если нет данных,
 * то статус = 204.
 */
public class Resp {
    private String text;
    private String status;

    public Resp(String text, String status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public String status() {
        return status;
    }

    public void setText(String text) {
        this.text = text;
    }
}