import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            SudokuTable window = new SudokuTable();
            window.getJFrame().setVisible(true);
        });
    }
}