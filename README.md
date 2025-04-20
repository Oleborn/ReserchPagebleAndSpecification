# 📘 Reserch Pageble and Specification

Этот проект демонстрирует основы использования пагинации и фильтрации данных в Spring Boot-приложении с помощью `Pageable`, `Specification` и `Criteria API`.  
Он предназначен для новичков, желающих изучить эти механизмы на практике.

---

## 🛠️ Технологии

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Mapstruct
- Maven

---

## 🚀 Запуск проекта

1. **Клонируйте репозиторий:**

   ```bash
   git clone https://github.com/Oleborn/ReserchPagebleAndSpecification.git
   cd ReserchPagebleAndSpecification
   ```

2. **Соберите проект с помощью Maven:**

   ```bash
   mvn clean install
   ```

3. **Запустите приложение:**

   ```bash
   mvn spring-boot:run
   ```

   Приложение будет доступно по адресу: `http://localhost:8081`

---

## 📂 Структура проекта

- `model/Person.java` — сущность пользователя с полями `numberPassport`, `name`, `surname`, `age`, `sex`.
- `repository/PersonRepository.java` — интерфейс репозитория, расширяющий `JpaRepository` и `JpaSpecificationExecutor`.
- `spec/PersonSpecification.java` — класс с методами для создания спецификаций фильтрации по всем полям сущности.
- `service/PersonService.java` — сервисный слой с методами для получения пользователей с использованием различных подходов.
- `controller/PersonController.java` — REST-контроллер с эндпоинтами для получения результатов.

---

## 🔍 Эндпоинты

1. **Наполнение тестовой БД указанным количеством персон с рандомными данными:**

   - **URL:** `GET /person/create-count/{count}`
   - **Параметры:**
     - `count` (количество персон для создания в БД)
    
2. **Получение всех пользователей без какой либо пагинации или фильтрации (демонстрационный):**

   - **URL:** `GET /person/getAll`
    
3. **Получение всех пользователей с пагинацией и возвращением данных в объекте Page:**

   - **URL:** `GET /person/getAllPages`
   - **Параметры:**
     - `page` (по умолчанию 0)
     - `size` (по умолчанию 10)
     - `sortField` (по умолчанию "numberPassport")
     - `sortDirection` (по умолчанию "asc")
    
4. **Получение всех пользователей с пагинацией и возвращением данных в ResponseDto:**

   - **URL:** `GET /person/getAllDto`
   - **Параметры:**
     - `page` (по умолчанию 0)
     - `size` (по умолчанию 10)
     - `sortField` (по умолчанию "numberPassport")
     - `sortDirection` (по умолчанию "asc")

5. **Получение пользователей с фильтрацией через Specification и RequestDto:**

   - **URL:** `GET /person/getAll-filter`
   - **Параметры:**
     - `page` (по умолчанию 0)
     - `size` (по умолчанию 10)
     - `sortField` (по умолчанию "numberPassport")
     - `sortDirection` (по умолчанию "asc")
     - `numberPassport` (для фильтрации по номеру паспорта)
     - `name` (для фильтрации по имени)
     - `surname` (для фильтрации по фамилии)
     - `age` (для фильтрации по возрасту)
     - `sex` (для фильтрации по полу)
     - `startAge` (для фильтрации по начальному возрасту)
     - `finishAge` (для фильтрации по конечному возрасту)
---

## 🧪 Примеры запросов

- `GET /person/getAll-filter?page=0&size=5`
- `GET /person/getAll-filter?name=John&age=25&page=0&size=10`
- `GET /person/getAll-filter?name=Jane&sort=age,desc&page=1&size=5`

---

## 📌 Примечания

- Приложение использует H2 in-memory базу данных, что упрощает тестирование и не требует дополнительной настройки.
- Для просмотра консоли H2 можно перейти по адресу: `http://localhost:8081/h2-console`
  - **JDBC URL:** `jdbc:h2:mem:testdb`
  - **Username:** `sa`
  - **Password:** (оставьте пустым)

---

## 📚 Ресурсы

- [Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Boot - Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

---

Если у вас есть дополнительные вопросы или предложения по улучшению проекта, не стесняйтесь открывать [issues](https://github.com/Oleborn/ReserchPagebleAndSpecification/issues) или создавать [pull requests](https://github.com/Oleborn/ReserchPagebleAndSpecification/pulls).

[@Oleborn](https://t.me/Oleborn)
