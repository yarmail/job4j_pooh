import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueueServiceTest {

    /**
     * Добавляем данные в очередь weather. Режим queue
     * queueService.process(new Req("POST", "queue", "weather", paramForPostMethod));
     *
     * Забираем данные из очереди weather. Режим queue
     * new Req("GET", "queue", "weather", null)
     */
    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text(), is("temperature=18"));
    }
}