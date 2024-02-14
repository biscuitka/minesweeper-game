## Minesweaper

Веб-сервер с REST API, реализована базовая логика, нуждается в доработке.

<details>
  <summary><i><b><u>Создание игры</u></b></i></summary> 


Post запрос локально без параметров http://localhost:8080/game/new

Request body:
{
"width": 10,
"height": 10,
"mines_count": 10
}

</details>

<details>
  <summary><i><b><u>Ход:</u></b></i></summary> 

Post запрос локально без параметров http://localhost:8080/game/turn

Request body:
"game_id": "сформированный gameId при создании новой игры",
"col": 2,
"row": 5
}


</details>