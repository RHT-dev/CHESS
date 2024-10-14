package Chess;

public abstract class ChessPiece {
    protected String color;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public abstract String get_color();

    public abstract boolean can_move_to_position(ChessBoard board, int start_line, int start_column, int to_line, int to_column);

    public abstract String get_symbol();
}
