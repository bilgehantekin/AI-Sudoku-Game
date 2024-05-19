import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;

public class SudokuTable {

    final JTextField[][] table = new JTextField[9][9]; // table for Sudoku
    final JButton solveButton = new JButton("Solve!"); // button to solve Sudoku
    final JButton newSudokuButton = new JButton("New Sudoku!");// button to generate new Sudoku
    private JFrame jFrame;
    private int[][] sudokuTable = new int[9][9];

    private int[][] solvedTable = new int[9][9]; // solved Sudoku table

    // constructor
    public SudokuTable() {
        generate();
    }

    // getter for jFrame
    public JFrame getJFrame() {
        return jFrame;
    }

    private void generate() {

        jFrame = new JFrame();
        jFrame.setBounds(100, 100, 417, 550);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setLayout(null);

        addingTable();
        addingSolveButton();
        addingSolveButtonListener();
        addingNewSudokuButton();
        addingNewSudokuButtonListener();
    }
    // adding table to JFrame
    private void addingTable() {

        int widthPoint = 12;
        int heightPoint = 12;
        int widthOfSquare = 40;
        int heightOfSquare = 40;

        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0 && i != 0) {
                heightPoint += 10;
            }

            for (int j = 0; j < 9; j++) {

                if (j % 3 == 0 && j != 0) {
                    widthPoint += 10;
                }

                table[i][j] = new JTextField();
                table[i][j].setColumns(10);
                table[i][j].setBounds(widthPoint, heightPoint, 38, 38);
                table[i][j].setBorder(new LineBorder(Color.BLACK, 3));

                table[i][j].setFont(new Font("Serif", Font.BOLD, 22));
                table[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                table[i][j].setEditable(true);

                jFrame.getContentPane().add(table[i][j]);

                widthPoint += widthOfSquare;
            }

            widthPoint = 12;
            heightPoint += heightOfSquare;
        }
    }
    // adding solve button to JFrame
    private void addingSolveButton() {

        solveButton.setText("Solve");
        solveButton.setBounds(39, 420, 150, 50);
        solveButton.setFont(new Font("Calibre", Font.BOLD, 18));

        jFrame.getContentPane().add(solveButton);
    }
    // adding solve button listener
    private void addingSolveButtonListener() {

        solveButton.addActionListener(e -> {

            sudokuTable = convertTo2DArray(table);

            solvedTable = Algorithm.solveSudoku(sudokuTable);

            assert solvedTable != null;

            for (int i = 0; i < 9; i++) {

                for (int j = 0; j < 9; j++) {

                    table[i][j].setEditable(false);

                    if (table[i][j].getText().isEmpty()) {

                        table[i][j].setText(String.valueOf(solvedTable[i][j]));
                        table[i][j].setForeground(Color.BLUE);
                    }
                }
            }
        });
    }
    // adding new Sudoku button to JFrame
    private void addingNewSudokuButton() {

        newSudokuButton.setText("New Sudoku");
        newSudokuButton.setBounds(209, 420, 150, 50);
        newSudokuButton.setFont(new Font("Calibre", Font.BOLD, 18));

        jFrame.getContentPane().add(newSudokuButton);
    }
    // adding new Sudoku button listener
    private void addingNewSudokuButtonListener() {

        newSudokuButton.addActionListener(e -> {

            for (int i = 0; i < 9; i++) {

                for (int j = 0; j < 9; j++) {

                    table[i][j].setEditable(true);
                    table[i][j].setText("");
                    table[i][j].setForeground(Color.BLACK);
                }
            }
        });
    }
    // converting table to 2D array
    private int[][] convertTo2DArray(JTextField[][] table) {

        int numberOfRows = table.length;
        int numberOfColumns = table[0].length;
        int[][] convertedTable = new int[9][9];

        for (int i = 0; i < numberOfRows; i++) {

            for (int j = 0; j < numberOfColumns; j++) {

                try {

                    if (table[i][j].getText().isEmpty()) {
                        convertedTable[i][j] = 0;
                    } else {
                        convertedTable[i][j] = Integer.parseInt(table[i][j].getText());
                    }

                } catch (NumberFormatException e) {
                    e.addSuppressed(new Throwable("Invalid input!"));
                }
            }
        }
        return convertedTable;
    }
}