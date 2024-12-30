# Виселица

Виселица – здесь игра, в котором компьютер загадывает случайное слово, которое игрок должен угадать, используя буквы алфавита и возможность совершить ограниченное количество ошибок.

## Об игре

Файл `words.txt` - 110 существительных на русском.
Файл `roundsScenes.txt` - рисунки сцен (7 рисунков разделённых \";\") из символов.
Файл `./src/viselica/Hangman.java` - файл с кодом игры на языке программирования Java.

## О проекте

Проект написан на языке программирования Java без использования ООП стиля.
1. При старте, приложение предлагает начать новую игру или выйти из приложения.
```
////////////////////////
//////  ВИСЕЛИЦА  //////
////////////////////////
1 - начать новую игру
0 - выход
Ваш выбор:
> 
```
2. При начале новой игры, случайным образом загадывается слово, и игрок начинает процесс по его отгадыванию.
```
//////////////
//          //
//          //
//          //
//          //
//      o   //
//     /|\  //
//_____/_\__//
Ошибки: 0
_________
Введите букву:
> 
```
3. После каждой введенной буквы выводим в консоль счётчик ошибок, текущее состояние виселицы (нарисованное ASCII символами)
Ввели букву \"а\:
```
//////////////
//          //
//          //
//          //
//          //
//      o   //
//     /|\  //
//_____/_\__//
Ошибки: 0
_________
Введите букву:
> а
```

Результат:
```
//////////////
//          //
//          //
//          //
//      o   //
//     /|\  //
//   __/_\_ //
//___|____|_//
Ошибки: 1
_________
Введите букву:
> 
```
 
4) По завершении игры выводим результат (победа или поражение) и возвращаемся к состоянию #1 - предложение начать новую игру или выйти из приложения
```
//////////////
//          //
// |        //
// |        //
// |    o   //
// |   /|\  //
// | __/_\_ //
//_A_|____|_//
Ошибки: 2
искусство

>>>>> Поздраляем!!! Вы угадали слово!

////////////////////////
//////  ВИСЕЛИЦА  //////
////////////////////////
1 - начать новую игру
0 - выход
Ваш выбор:
> 
```