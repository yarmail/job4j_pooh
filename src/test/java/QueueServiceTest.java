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

    @Test
    public void whenPostThenGetQueueB() {
        QueueService queueService = new QueueService();
        String weatherSourceName = "weather";
        String trafficSourceName = "traffic";
        String paramForWeatherSourceName = "temperature=18";
        String paramForTrafficSourceName = "cars=20188";

        queueService.process(
                new Req("POST", "queue", weatherSourceName, paramForWeatherSourceName)
        );
        queueService.process(
                new Req("POST", "queue", trafficSourceName, paramForTrafficSourceName)
        );
        Resp resultFromWeatherSource1 = queueService.process(
                new Req("GET", "queue", weatherSourceName, null)
        );
        Resp resultFromWeatherSource2 = queueService.process(
                new Req("GET", "queue", weatherSourceName, null)
        );
        Resp resultFromTrafficSource1 = queueService.process(
                new Req("GET", "queue", trafficSourceName, null)
        );
        Resp resultFromTrafficSource2 = queueService.process(
                new Req("GET", "queue", trafficSourceName, null)
        );
        assertThat(resultFromWeatherSource1.text(), is("temperature=18"));
        assertThat(resultFromWeatherSource2.text(), is(""));
        assertThat(resultFromTrafficSource1.text(), is("cars=20188"));
        assertThat(resultFromTrafficSource2.text(), is(""));
    }
}