/*
2. Тестовое задание - проект "Pooh JMS" [#268841]
В этом проекте мы сделаем аналог асинхронной очереди.
Приложение запускает Socket и ждет клиентов.
Клиенты могут быть двух типов: отправители (publisher),
получатели (subscriber).
В качестве клиента будем использовать cURL.
В качестве протокола будем использовать HTTP.

======================================

Сервер PoohServer
Проверим работу сервера через cURL.
1. запустить сервер PoohServer
2. Открыть CMD и перейти :  cd C:\Tools\curl773\bin
3. Проверить работоспособность curl: curl -V
4. Отправить запрос: curl -X POST -d "text=13" http://localhost:9000/queue/weather
5. Ответ должен быть: text=13
(все работает)

сервер Pooh будет иметь два режима: queue, topic.
........

Queue.
Отправитель посылает запрос на добавление данных
с указанием очереди (weather) и значением параметра
(temperature=18). Сообщение помещается в конец очереди.
Если очереди нет в сервисе, то нужно создать новую
и поместить в нее сообщение.

Получатель посылает запрос на получение данных с указанием очереди.
Сообщение забирается из начала очереди и удаляется.
Если в очередь приходят несколько получателей,
то они поочередно получают сообщения из очереди.
Каждое сообщение в очереди может быть
получено только одним получателем.

Примеры запросов.
POST запрос должен добавить элементы в очередь weather.
curl -X POST -d "temperature=18" http://localhost:9000/queue/weather
queue указывает на режим «очередь».
weather указывает на имя очереди.

GET запрос должен получить элементы из очереди weather.
curl -X GET http://localhost:9000/queue/weather
Ответ: temperature=18
......
Topic.
Отправитель посылает запрос на добавление данных
с указанием топика (weather) и значением
параметра (temperature=18). Сообщение помещается
в конец каждой индивидуальной очереди получателей.

Если топика нет в сервисе, то данные игнорируются.
Получатель посылает запрос на получение данных
с указанием топика. Если топик отсутствует,
то создается новый. А если топик присутствует,
то сообщение забирается из начала индивидуальной
очереди получателя и удаляется.

Когда получатель впервые получает данные из топика –
для него создается индивидуальная пустая очередь.
Все последующие сообщения от отправителей с данными
для этого топика помещаются в эту очередь тоже.

Таким образом в режиме "topic" для каждого потребителя
своя будет уникальная очередь с данными, в отличие
от режима "queue", где для все потребители получают
данные из одной и той же очереди.

Примеры запросов.
Отправитель.
POST /topic/weather -d "temperature=18"
topic указывает на режим «топик».
weather указывает на имя топика.

Получатель.
GET /topic/weather/1
1 - ID получателя.
Ответ: temperature=18

......
Архитектура проекта

        QueueService
resp     PoohServer    Req
       TopicService

Req - класс, служит для парсинга входящего запроса.
httpRequestType - GET или POST. Он указывает на тип запроса.

PoohMode - указывает на режим работы: queue или topic.
sourceName - имя очереди или топика.
param - содержимое запроса.






 */