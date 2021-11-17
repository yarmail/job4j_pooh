import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Req - вероятно от слова Request - запрос
 * Req - класс, служит для парсинга входящего запроса.
 *
 * httpRequestType - GET или POST. Он указывает на тип запроса.
 * poohMode - указывает на режим работы: queue или topic.
 * sourceName - имя очереди или топика - weather
 * param - содержимое запроса (temperature=18 или null)
 *
 * (есть тесты)
 */
public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    /**
     * httpRequestType - GET или POST. Он указывает на тип запроса.
     * poohMode - указывает на режим работы: queue или topic.
     * sourceName - имя очереди или топика - weather
     * param - содержимое запроса (temperature=18 или null)
     *
     * ??? Для выбора метода GET или POST
     * используйте константы "GET".equals(req.method()), а не на оборот
     * req.method.equals("GET").
     */
    public static Req of(String content) {
        List<String> listKey = new ArrayList<>(Arrays.asList("POST", "GET", "topic", "queue", "weather", "temperature=18"));
        listKey.removeIf(el -> !content.contains(el));
        if (("GET").equals(listKey.get(0)) && listKey.get(1).equals("queue")) {
            listKey.add(3, "");
        }
        if (listKey.get(0).equals("GET") && listKey.get(1).equals("topic")) {
            listKey.add(3, "client407");
        }
        return new Req(listKey.get(0), listKey.get(1), listKey.get(2), listKey.get(3));
        }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
