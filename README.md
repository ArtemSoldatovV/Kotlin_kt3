# Kotlin_kt3
## Запуск

1. Склонировать репозиторий
2. Построить проект: ./gradlew build
3. Запустить сервер через файл main.kt: Сервер будет доступен на `http://localhost:8080`

для запуска тестов файл ApiTest.kt

## Маршруты:
- `GET /pizza_menu` — получить все виды пицц
- `GET /pizza_menu/{name}` — получить пиццу по названию (path параметр)
- `POST /adding_pizza` — добавить пиццу (JSON тело запроса, формат Pizza - {name: String, filling: String, cost: Int})
- `DELETE /pizza_removal` — удалить пиццу по названию (query параметр)
