import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ticTacToe {

    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPos = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        char[][] draw = { { '+', '-', '-', '-', '-', '-', '+' }, { '|', ' ', '|', ' ', '|', ' ', '|' },
                { '|', '-', '+', '-', '+', '-', '|' }, { '|', ' ', '|', ' ', '|', ' ', '|' },
                { '|', '-', '+', '-', '+', '-', '|' }, { '|', ' ', '|', ' ', '|', ' ', '|' },
                { '+', '-', '-', '-', '-', '-', '+' } };
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter your position (between 1 - 9) : ");
            int scanned = sc.nextInt();
            while (playerPos.contains(scanned) || cpuPos.contains(scanned)) {
                System.out.println("Not valid position, please try again : ");
                scanned = sc.nextInt();
            }

            placement(draw, scanned, "player");
            gameScreen(draw);

            if (condition().length() > 0) {
                clearScreen();
                gameScreen(draw);
                System.out.println(condition());
                break;
            }

            clearScreen();

            Random random = new Random();
            int cpuNum = random.nextInt(9) + 1;
            while (playerPos.contains(cpuNum) || cpuPos.contains(cpuNum)) {
                cpuNum = random.nextInt(9) + 1;
            }

            placement(draw, cpuNum, "cpu");
            gameScreen(draw);
            if (condition().length() > 0) {
                System.out.println(condition());
                break;
            }

        }
    }

    public static String condition() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPos.containsAll(l)) {
                return "You Win!";
            } else if (cpuPos.containsAll(l)) {
                return "Computer Wins!";
            }
        }
        if (playerPos.size() + cpuPos.size() == 9) {
            return "Tie!";
        }
        return "";
    }

    public static void placement(char[][] gameBoard, int input, String user) {
        char tictactoe = ' ';

        if (user.equals("player")) {
            tictactoe = 'X';
            playerPos.add(input);
        }
        if (user.equals("cpu")) {
            tictactoe = 'O';
            cpuPos.add(input);
        }

        switch (input) {
            case 1:
                gameBoard[1][1] = tictactoe;
                break;
            case 2:
                gameBoard[1][3] = tictactoe;
                break;
            case 3:
                gameBoard[1][5] = tictactoe;
                break;
            case 4:
                gameBoard[3][1] = tictactoe;
                break;
            case 5:
                gameBoard[3][3] = tictactoe;
                break;
            case 6:
                gameBoard[3][5] = tictactoe;
                break;
            case 7:
                gameBoard[5][1] = tictactoe;
                break;
            case 8:
                gameBoard[5][3] = tictactoe;
                break;
            case 9:
                gameBoard[5][5] = tictactoe;
                break;
        }
    }

    public static void gameScreen(char[][] gameBoard) {
        for (char[] rows : gameBoard) {
            for (char c : rows) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}