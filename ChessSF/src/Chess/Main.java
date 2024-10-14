package Chess;

import java.util.Scanner;

public class Main {

    public static ChessBoard build_board() {
        ChessBoard board = new ChessBoard("Белый");

        board.board[0][0] = new Rook("Белый");
        board.board[0][1] = new Horse("Белый");
        board.board[0][2] = new Bishop("Белый");
        board.board[0][3] = new Queen("Белый");
        board.board[0][4] = new King("Белый");
        board.board[0][5] = new Bishop("Белый");
        board.board[0][6] = new Horse("Белый");
        board.board[0][7] = new Rook("Белый");
        board.board[1][0] = new Pawn("Белый");
        board.board[1][1] = new Pawn("Белый");
        board.board[1][2] = new Pawn("Белый");
        board.board[1][3] = new Pawn("Белый");
        board.board[1][4] = new Pawn("Белый");
        board.board[1][5] = new Pawn("Белый");
        board.board[1][6] = new Pawn("Белый");
        board.board[1][7] = new Pawn("Белый");

        board.board[7][0] = new Rook("Чёрный");
        board.board[7][1] = new Horse("Чёрный");
        board.board[7][2] = new Bishop("Чёрный");
        board.board[7][3] = new Queen("Чёрный");
        board.board[7][4] = new King("Чёрный");
        board.board[7][5] = new Bishop("Чёрный");
        board.board[7][6] = new Horse("Чёрный");
        board.board[7][7] = new Rook("Чёрный");
        board.board[6][0] = new Pawn("Чёрный");
        board.board[6][1] = new Pawn("Чёрный");
        board.board[6][2] = new Pawn("Чёрный");
        board.board[6][3] = new Pawn("Чёрный");
        board.board[6][4] = new Pawn("Чёрный");
        board.board[6][5] = new Pawn("Чёрный");
        board.board[6][6] = new Pawn("Чёрный");
        board.board[6][7] = new Pawn("Чёрный");
        return board;
    }

    public static void main(String[] args) {

        ChessBoard board = build_board();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Чтобы проверить игру, надо вводить команды:
                'выход' - для выхода
                'перезапуск' - для перезапуска игры
                'castling0' или 'castling7' - для рокировки по соответствующей линии
                'движ 1 1 2 3' - для передвижения фигуры с позиции 1 (строка) 1 (столбец) на 2 3""");
        System.out.println();
        board.print_board();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("выход") || s.equals("Выход")) break;
            else if (s.equals("перезапуск") || s.equals("Перезапуск")) {
                System.out.println("Заново");
                board = build_board();
                board.print_board();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling_0()) {
                        System.out.println("Рокировка удалась");
                        board.print_board();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling_7()) {
                        System.out.println("Рокировка удалась");
                        board.print_board();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("движ")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int to_line = Integer.parseInt(a[3]);
                        int to_column = Integer.parseInt(a[4]);
                        if (board.move_to_position(line, column, to_line, to_column)) {
                            System.out.println("Успешно передвинулись");
                            board.print_board();
                        } else {
                            System.out.println("Передвижение не удалось");
                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка: неправильный ввод. Попробуйте снова!");
                    }
                }
            }
        }
    }
}
