package viselica;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
    public static Scanner scan;
    public static String word;
    public static StringBuilder userWord = new StringBuilder();
    public static ArrayList<String> roundsScenes = new ArrayList<>();
    public static final int MAX_MISTAKES = 6;
    public static StringBuilder usedChars = new StringBuilder();

    public static void initGameResources(){
        usedChars.replace(0, usedChars.length(), "");
        userWord.replace(0, usedChars.length(), "");
        word = getRandomWord();
        initUserWord();
    }

    public static void initUserWord(){
        userWord.replace(0, userWord.length(), "");
        int wordSize = word.length();
        for(int i = 0; i < wordSize; i += 1) {
            userWord.append("_");
        }
    }

    public static void initRoundsScenes(){
        StringBuilder lines = new StringBuilder();
        File file = new File("roundsScenes.txt");
        try{
            scan = new Scanner(file);
            while(scan.hasNextLine()){
                lines.append(scan.nextLine() + "\n");
            }
            roundsScenes.addAll(Arrays.asList(lines.toString().split(";")));
            scan.close();
        } catch(IOException e){
            System.out.println("-----> Не удалось открыть файл с сценами персонажа!: " + e.getMessage());
        }
    }

    public static String getRandomWord(){
        File file = new File("words.txt");
        try{
            scan = new Scanner(file);
            ArrayList <String> words = new ArrayList<>();
            Random rand = new Random();
            while(scan.hasNextLine()){
                words.add(scan.nextLine());
            }
            scan.close();
            int index = rand.nextInt(1, words.size());
            return words.get(index);
        } catch (IOException e){
            System.out.println("-----> Не удалось открыть файл со словами!: " + e.getMessage());
            return null;
        }
    }

    public static boolean startGameMenu(String scene) {
        String userNum = "";
        renderScene(scene);
        scan = new Scanner(System.in);
        userNum = scan.nextLine();
        if(userNum.equals("1")) {
            runGame();
            return true;
        }
        else if(userNum.equals("0")) {
            scan.close();
            return false;
        }
        return true;
    }

    public static void runGame() {
        initGameResources();
        int mistakes = 0;
        String userChar;
        String lastLine = "";
        String gameScene = roundsScenes.get(mistakes) + "\n" + "Ошибки: " + mistakes + "\n" + userWord + "\n";
        renderScene(gameScene);
        while(isRunGame(mistakes)){
            lastLine = "";
            userChar = getUserChar();
            if(!isUserCharLengthOne(userChar)) {
                lastLine = ">>>>> Ошибка! ";
            } else if(isCharKirrilica(userChar)){
                if(!didCharUse(userChar)){
                    usedChars.append(userChar);
                    if (isCharInWord(userChar, word)){
                        setCharToUserWord(userChar);
                    }
                    else {
                        mistakes += 1;
                    }
                }
                else {
                    lastLine = ">>>>> Бувка уже использовалась вами!\n";
                }
            } else {
                lastLine = ">>>>> Введите БУКВУ кириллицы!!!\n";
            }
            gameScene = roundsScenes.get(mistakes) + "\n" + "Ошибки: " + mistakes + "\n" + userWord + "\n" + lastLine;
            renderScene(gameScene);
        }
    }

    public static boolean didCharUse(String ch){
        return usedChars.toString().contains(ch);
    }

    public static String getUserChar(){
        scan = new Scanner(System.in);
        String ch;
        System.out.print("Введите букву:\n> ");
        ch = scan.nextLine().toLowerCase();
        return ch;
    }

    public static boolean isCharKirrilica(String ch){
        String KIRRILICA = "йцукеёнгшщзхъфывапролджэячсмитьбю";
        return KIRRILICA.contains(ch);
    }

    public static boolean isUserCharLengthOne(String ch){
        return ch.length() != 1 ? false : true;
    }

    public static boolean isRunGame(int mistakes){
        if(mistakes >= MAX_MISTAKES){
            String gameOver = "\n>>>>> Вы проиграли!!!\nВы НЕ угадали слово: " + word + "\n\n";
            renderScene(gameOver);
            return false;
        } else if (userWord.toString().equals(word)){
            String gameOver = "\n>>>>> Поздраляем!!! Вы угадали слово!\n\n";
            renderScene(gameOver);
            return false;
        } else {
            return true;
        }
    }

    public static void setCharToUserWord(String ch){
        for(int i = 0; i < word.length(); i += 1){
            if(ch.charAt(0) == word.charAt(i)){
                userWord.replace(i, i+1, ch);
            }
        }
    }

    public static boolean isCharInWord(String ch, String word){
        return word.contains(ch);
    }

    public static void renderScene(String scene){
        System.out.print(scene);
    }

    public static void main(String[] args) {
        String menuScene = "////////////////////////\n//////  ВИСЕЛИЦА  //////\n////////////////////////\n"
                + "1 - начать новую игру\n0 - выход\nВаш выбор:\n> ";
        initRoundsScenes();
        boolean isGame;
        do{
            isGame = startGameMenu(menuScene);
        } while (isGame);
        System.out.println("До встречи!!!\nВыход из игры...");
    }
}