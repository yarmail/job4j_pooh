import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String httpRequestType;
        String poohMode;
        String sourceName;
        String param;
        String[] lines = content.split(System.lineSeparator());
        String[] firstLine = lines[0].split(" ");
        String[] modeAndName = firstLine[1].split("/");
        httpRequestType = firstLine[0];
        poohMode = modeAndName[1];
        sourceName = modeAndName[2];
        param = lines[lines.length - 1];
        if ("GET".equals(httpRequestType)) {
            if (modeAndName.length < 4) {
                param = "";
            } else {
                param = modeAndName[3];
            }
        }

        return new Req(httpRequestType, poohMode, sourceName, param);
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