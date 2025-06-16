1. Установка email пользователя
   HTTP Запрос: POST /api/user/email
   Content-Type: text/plain
   Тело запроса: email@kras.com (пример)

3. Добавление песни в плейлист
   HTTP Запрос: POST /api/playlist/songs
   Content-Type: text/plain
   Тело запроса: Название песни, Исполнитель

5. Удаление песни из плейлиста
   HTTP Запрос: DELETE /api/playlist/songs/{songId}

6. Получение плейлиста
   HTTP Запрос: GET /api/playlist/songs
