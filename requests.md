# Приклади запитів — Заняття 1.2 (MovieStore, повний CRUD)

Бекенд має бути запущений: `mvn spring-boot:run` (порт 8080, JDK 21).

На цьому занятті — усі шість операцій: перелік, фільтр за жанром, один фільм,
створення, оновлення, видалення.

---

## 1. Перелік фільмів

```bash
curl http://localhost:8080/api/movies
```

## 2. Перелік із фільтром за жанром

```bash
curl "http://localhost:8080/api/movies?genre=Драма"
```
Поверне лише фільми цього жанру (у стартових даних — «Тіні забутих предків» і «Земля»).

## 3. Один фільм з деталями

```bash
curl http://localhost:8080/api/movies/1
```

## 4. Створити фільм (POST → 201 Created)

```bash
curl -i -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Нова стрічка",
    "director": "Режисер",
    "price": 199.00,
    "year": 2024,
    "durationMinutes": 100,
    "genre": "Комедія",
    "posterUrl": "/posters/new.jpg",
    "description": "Опис нового фільму."
  }'
```
Очікувано: `HTTP/1.1 201` і JSON створеного фільму з призначеним `id`.

## 5. Оновити фільм (PUT → 200 OK)

```bash
curl -i -X PUT http://localhost:8080/api/movies/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Тіні забутих предків (рест.)",
    "director": "Сергій Параджанов",
    "price": 220.00,
    "year": 1965,
    "durationMinutes": 97,
    "genre": "Драма",
    "posterUrl": "/posters/shadows.jpg",
    "description": "Оновлений опис."
  }'
```
Очікувано: `HTTP/1.1 200` і JSON оновленого фільму.

## 6. Оновити неіснуючий фільм → 404

```bash
curl -i -X PUT http://localhost:8080/api/movies/999 \
  -H "Content-Type: application/json" \
  -d '{ "title": "X", "director": "Y", "price": 1, "genre": "Z" }'
```
Очікувано: `HTTP/1.1 404`.

## 7. Видалити фільм (DELETE → 204 No Content)

```bash
curl -i -X DELETE http://localhost:8080/api/movies/2
```
Очікувано: `HTTP/1.1 204` (без тіла).

## 8. Видалити неіснуючий фільм → 404

```bash
curl -i -X DELETE http://localhost:8080/api/movies/999
```
Очікувано: `HTTP/1.1 404`.
