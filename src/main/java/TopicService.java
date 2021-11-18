import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req request) {
        Resp response = new Resp("", "400 Bad request");
        if (request.httpRequestType().equals("GET") && map.isEmpty()) {
            map.put(request.getParam(), "");
        }
        if (request.httpRequestType().equals("POST") && !map.isEmpty()) {
            Optional<String> firstKey = map.keySet().stream().findFirst();
            String key = firstKey.get();
            map.put(key, request.getParam());
        }
        if (request.httpRequestType().equals("GET")
                && !map.isEmpty() && map.get(request.getParam()) != null) {
            response = new Resp(map.get(request.getParam()), "200 OK");
        }
        return response;
        }
}