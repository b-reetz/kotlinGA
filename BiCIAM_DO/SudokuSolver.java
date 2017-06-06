import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SudokuSolver {

	public static void main(String args[]) throws FileNotFoundException {
		if (args.length == 0) {
			System.out.println("Please providea a file to read the sudoku solutions from");
			System.exit(0);
		}
		
		List<Sudoku> sudokus = parseSudokuFromFile(args[1]);
	}

	private static List<Sudoku> parseSudokuFromFile(String string) throws FileNotFoundException {
		File f = new File(string);
		
		if (!f.exists())
			throw new FileNotFoundException("Cannot find file");
		
		List<Sudoku> puzzles = new ArrayList<>();
		Sudoku currentSudoku = new Sudoku();
		
		Scanner in = new Scanner(f);
		while (in.hasNextLine()) {
			while (in.hasNext()) {
				String token = in.next();
				if (token)
			}
		}
		
		
		return puzzles;
	}
	
	private static boolean isValid(String s) {
		if ()
		return true;
	}
	
}
