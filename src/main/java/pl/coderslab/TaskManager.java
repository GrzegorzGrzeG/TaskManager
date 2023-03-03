package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private static final String FILE = "tasks.csv";
    private static String[] MENU = {"add", "remove", "list", "exit"};
    private static String[][] TASKS;

    public static String[][] inputFile(String fileName) {
        String[][] arr = null;
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            System.out.println("No file");
        } else {
            try {
                List<String> list = Files.readAllLines(path);
                arr = new String[list.size()][list.get(0).split(",").length];
                for (int i = 0; i < list.size(); i++) {
                    String[] temp = list.get(i).split(",");
                    for (int j = 0; j < temp.length; j++) {
                        arr[i][j] = temp[j];
                    }
                }

            } catch (IOException e) {
                System.out.println("IO Exception on input");
            }
        }
        return arr;
    }

    public static void menu() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (String menuElement : MENU) {
            System.out.println(menuElement);
        }
    }

    public static String input() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext()) {
            System.out.println("Error. Try again");
            scanner.next();
        }
        String result = scanner.next();

        return result;
    }

    public static void eventHandling(String[][] arr) {
        menu();
        String input = input();

        switch (input) {
            case "add":
                System.out.print("add");
                break;
            case "remove":
                System.out.println("remove");
                break;
            case "list":
                list(arr);
                break;
            case "exit":
                System.out.println("exit");
                System.exit(0);
                break;
            default:
                System.out.println("error");
        }
    }

    public static void add() {
        System.out.println("Please add task description:");
        String description = input();
        System.out.println("Please add task due date:");
        String date = input();
        System.out.println("Is your task is important: true/false");
        String important = input();
        TASKS = Arrays.copyOf(TASKS, TASKS.length + 1);
        TASKS[TASKS.length - 1] = new String[3];
        TASKS[TASKS.length - 1][0] = description;
        TASKS[TASKS.length - 1][0] = date;
        TASKS[TASKS.length - 1][0] = important;

    }

    public static void remove() {

    }

    public static void list(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TASKS = inputFile(FILE);
        eventHandling(TASKS);
        //menu();
    }
}
