package ru.amorozov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String ADD_CMD_PREFIX = "add";
    private static final String PRINT_CMD_PREFIX = "print";
    private static final String REMOVE_GROUP_CMD_PREFIX = "remove";
    private static final String CLEAR_CMD_PREFIX = "clear";
    private static final String HELP_CMD_PREFIX = "help";
    private static final String EXIT_CMD_PREFIX = "exit";
    private static final String WELCOME_MSG = "Введите команду help, чтобы посмотреть все возможности приложения.";
    private static final int NULL_TOKEN = 0;

    private static List<MyNode> firstGroup = new ArrayList<>();
    private static List<MyNode> secondGroup = new ArrayList<>();
    private static List<MyNode> thirdGroup = new ArrayList<>();
    private static List<MyNode> withoutGroup = new ArrayList<>();

    private static MyNode node;

    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);

        while (true) {
            String userMessage = getCmdFromConsole();
            String[] token = userMessage.split("\\s", 4);

            if (token[0].equals(ADD_CMD_PREFIX)) {
                if (token[1] != null && token[2] != null) {
                    int x = Integer.parseInt(token[1]);
                    int y = Integer.parseInt(token[2]);
                    addNoteToList(x, y);
                }
            } else if (token[0].equals(PRINT_CMD_PREFIX)) {
                if (token[1] != null) {
                    int groupNumber = Integer.parseInt(token[1]);
                    printNodesFromGroup(groupNumber);
                } else printNodesFromGroup(NULL_TOKEN);
            } else if (token[0].equals(REMOVE_GROUP_CMD_PREFIX)) {
                if (token[1] != null) {
                    int groupNumber = Integer.parseInt(token[1]);
                    removeNodesFromGroup(groupNumber);
                } else removeNodesFromGroup(NULL_TOKEN);
            } else if (token[0].equals(CLEAR_CMD_PREFIX)) {
                firstGroup.clear();
                secondGroup.clear();
                thirdGroup.clear();
                withoutGroup.clear();
                System.out.println("Все группы очищены");
            } else if (token[0].equals(HELP_CMD_PREFIX)) {
                getAllCommands();
            } else if (token[0].equals(EXIT_CMD_PREFIX)) {
                System.out.println("Выход");
                break;
            } else System.out.println("Некорректная команда. " + WELCOME_MSG);
        }
    }

    // Добавление точек.
    protected static void addNoteToList(int x, int y) {
        if (y >= x && y < Math.pow(x, 2)) {
            node = new MyNode(x, y);
            firstGroup.add(node);
            System.out.println("Точка с координатами: " + x + " " + y + " добавлена в первую группу");
        } else if (y >= Math.pow(x, 2) && y < Math.pow(x, 3)) {
            node = new MyNode(x, y);
            secondGroup.add(node);
            System.out.println("Точка с координатами: " + x + " " + y + " добавлена во вторую группу");
        } else if (y >= Math.pow(x, 3)) {
            node = new MyNode(x, y);
            thirdGroup.add(node);
            System.out.println("Точка с координатами:" + x + " " + y + " добавлена в третью группу");
        } else {
            node = new MyNode(x, y);
            withoutGroup.add(node);
            System.out.println("Точка с координатами: " + x + " " + y + " не относится ни к одной из групп");
        }
    }

    // Печать групп точек.
    protected static void printNodesFromGroup(int groupNumber) {
        if (groupNumber == 1) {
            System.out.println(Arrays.toString(firstGroup.toArray()));
        } else if (groupNumber == 2) {
            System.out.println(Arrays.toString(secondGroup.toArray()));
        } else if (groupNumber == 3) {
            System.out.println(Arrays.toString(thirdGroup.toArray()));
        } else {
            System.out.println(Arrays.toString(firstGroup.toArray()));
            System.out.println(Arrays.toString(secondGroup.toArray()));
            System.out.println(Arrays.toString(thirdGroup.toArray()));
            System.out.println(Arrays.toString(withoutGroup.toArray()));
        }
    }

    // Очищение групп.
    protected static void removeNodesFromGroup(int groupNumber) {
        if (groupNumber == 1) {
            firstGroup.clear();
            System.out.println("Группа 1 очищена");
        } else if (groupNumber == 2) {
            secondGroup.clear();
            System.out.println("Группа 2 очищена");
        } else if (groupNumber == 3) {
            thirdGroup.clear();
            System.out.println("Группа 3 очищена");
        } else {
            withoutGroup.clear();
            System.out.println("Не отсортированная группа очищена");
        }
    }

    // Список команд.
    protected static void getAllCommands() {
        final List<String> listCommands = List.of(
                "Список команд",
                "\nadd <point>          - добавить в память программы точки, координаты передаются парами чисел через пробел",
                "\nprint                - напечатать построчно каждую из трех групп (входящие в них точки)",
                "\nprint <group_num>    - напечатать одним списком точки, входящие в группу(ы) переданную(ые) параметром <group_num>",
                "\nremove <group_num>   - удалить из памяти все точки, входящие в группу(ы) <group_num>",
                "\nclear                - очистить память",
                "\nhelp                 - вывод справки по командам",
                "\nexit                 - выход из приложения");
        System.out.println(listCommands);
    }

    // Проверка входных данных.
    static String getCmdFromConsole() {
        Scanner scanner = new Scanner(System.in);
        do {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
            System.out.println(WELCOME_MSG);
            scanner.nextLine();
        }
        while (true);
    }

}
