import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Сервис получает обработаенный парсером Req запрос
 * ???
 * Как я понимаю сервер запрашивает этот сервис.
 * А сервис кладет его в очередь и дает ответ
 * (есть тесты)
 */
public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    /**
     * httpRequestType - GET или POST. Он указывает на тип запроса.
     * poohMode - указывает на режим работы: queue или topic.
     * sourceName - имя очереди или топика - weather
     * param - содержимое запроса (temperature=18 или null)
     */
    @Override
    public Resp process(Req req) {
        if ("POST".equals(req.httpRequestType())) {
            return post(req);
        }
        if ("GET".equals(req.httpRequestType())) {
            return get(req);
        }
        return new Resp("type GET or POST", "500");
    }

    private Resp post(Req req) {
        queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>(Collections.singleton(req.getParam())));
        return new Resp(req.getParam() + " added to" + req.getSourceName(), "200");
    }

    private Resp get(Req req) {
        var element = Optional.ofNullable(queue.get(req.getSourceName()).poll()).orElse("");
        return new Resp(element, "ОК");
    }
}