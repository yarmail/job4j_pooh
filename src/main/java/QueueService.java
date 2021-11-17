import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Сервис получает обработаенный парсером Req запрос
 * ???
 * Как я понимаю сервер запрашивает этот сервис.
 * А сервис кладет его в очередь и дает ответ
 * (есть тесты)
 */
public class QueueService implements Service {
    private final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    /**
     * httpRequestType - GET или POST. Он указывает на тип запроса.
     * poohMode - указывает на режим работы: queue или topic.
     * sourceName - имя очереди или топика - weather
     * param - содержимое запроса (temperature=18 или null)
     */
    @Override
    public Resp process(Req request) {
        Resp response = new Resp("BadRequest", "400");
        if (request.httpRequestType().equals("POST")) {
            queue.add(request.getParam());
        }
        if (request.httpRequestType().equals("GET")) {
            String param = queue.poll();
            response = new Resp(param, "200");
        }
        return response;
        }
}
