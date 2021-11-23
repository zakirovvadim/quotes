1. Сервис для сохранения котировок и получения elvl по инструментам по REST API.

• Сервис получает котировки
• Сервис хранит историю полученных котировок в БД
• Сервис рассчитывает elvl (правила ниже)
• Сервис предоставляет elvl по isin
• Сервис предоставляет перечень всех elvls

Правила расчёта elvl на основе котировки:
1. Если bid > elvl, то elvl = bid
2. Если ask < elvl, то elvl = ask
3. Если значение elvl для данной бумаги отсутствует, то elvl = bid
4. Если bid отсутствует, то elvl = ask

Валидация вносимых данных.
• bid должен быть меньше ask
• isin – 12 символов

После настройки БД по конфигурации из docker-compose.yml и запуска программы необходимо открыть локальный сервер по 
выбранному вами порту подключения.

Варианты работы по внесению данных о котировках:
/ - возвращает весь список сохраненных котировок - GET.
/ - добавляет новую котировку и присваивает статус isActual = true. 
Прежняя актуальная котировка меняет is Actual = false.
{
"id": 0,
"isin": "string",
"bid": 0,
"ask": 0,
"elvl": 0,
"actual": true
}

/{isin} - осуществляет поиск котировки по уникальному номеру isin.

2. Свойства application.property
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   spring.jpa.generate-ddl=true 
   server.port=8080

2.1 Свойства application-test.properties
spring.datasource.url=jdbc:postgresql://localhost:5434/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.generate-ddl=true

server.port=8081

3. Команды для создания БД

CREATE DATABASE quote_table;

CREATE TABLE quote
(
id   SERIAL PRIMARY KEY,
isin CHARACTER VARYING(30),
bid  DECIMAL,
ask  DECIMAL,
is_Actual BOOLEAN,
elvl DECIMAL
);

3.1. БД для тестирования аналогична БД для работы сервиса.


4. Свойства для формирования docker-compose.yml
   version: '3.5'
   services:
   postgres:
   container_name: postgres_container
   image: postgres
   environment:
   POSTGRES_USER: ${POSTGRES_USER:-postgres}
   POSTGRES_PASSWORD: ${POSTGRES_PASSWORD:-changeme}
   PGDATA: /data/postgres
   volumes:
   - postgres:/data/postgres
     ports:
   - "5432:5432"
     networks:
   - postgres
     restart: unless-stopped
4.1 Команда запуска докер контейнера для бд для тестов.
     docker run --name docker run --name testQuoteBd -e POSTGRES_PASSWORD=password -d postgres -p 5432:5434