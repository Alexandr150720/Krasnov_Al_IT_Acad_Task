1. Установка email пользователя
   HTTP Запрос: POST /playlist/user/email
   Тело запроса: email@kras.com (пример)

2. Добавление песни в плейлист
   HTTP Запрос: POST /playlist/playlist/songs
   Тело запроса: Название песни, Исполнитель

3. Удаление песни из плейлиста
   HTTP Запрос: DELETE /playlist/playlist/songs/{songId}

4. Получение плейлиста
   HTTP Запрос: GET /playlist/playlist/songs
