package tic_tac_toe_app;

import java.util.Scanner;
import java.io.PrintStream;

class TTT {
	static Scanner userinput = new Scanner(System.in);
	char[][] board = { { '.', '.', '.' }, { '.', '.', '.' }, { '.', '.', '.' } };
	int turn = 1;
	char player = 'X';

	public void printBoard() {
		int i, j;
		System.out.println("");
		for (i = 0; i <= 2; i++) {
			for (j = 0; j <= 2; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public void move(int i, int j) {
		board[i][j] = player;
		turn++;
	}

	public void unDoMove(int i, int j) {
		board[i][j] = '.';
		turn--;
	}

	public void switchPlayers() {
		if (player == 'X') {
			player = 'O';
		} else
			player = 'X';
	}

	public boolean isLegal(int i, int j) {
		if (board[i][j] == '.')
			return true;
		else
			return false;
	}

	public boolean winner() {
		int i;
		boolean test = false;
		for (i = 0; i <= 2; i++) {
			if ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][0] != '.')) {
				test = true;
			}
			if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && (board[0][i] != '.')) {
				test = true;
			}
		}
		if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[0][0] != '.')) {
			test = true;
		}

		if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2]) && (board[2][0] != '.')) {
			test = true;
		}
		return test;
	}

	public void human() {
		int i, j;

		boolean test = false; // have I found a place to go
		while (test == false) {
			System.out.println("\nEnter Coordinates Where To Go Separated By A Space...");
			i = userinput.nextInt();
			j = userinput.nextInt();
			userinput.nextLine();
			if (isLegal(i - 1, j - 1) == true) {
				test = true;
				move(i - 1, j - 1);
			}
		}
	}

	
	//Added 143 lines of code for the AI
	public void ai() {
		int i = 0, j = 0;
		// random
		boolean test = false, send = false; // have I found a place to go
		while (test == false) {
			// Looking for Straight patterns for the AI
			for (int x = 0; x <= 2; x++) {
				if (board[x][0] == 'O' && board[x][1] == 'O' && board[x][2] == '.') {
					i = x;
					j = 2;
					send = true;
				}
				if (board[x][1] == 'O' && board[x][2] == 'O' && board[x][0] == '.') {
					i = x;
					j = 0;
					send = true;
				}
				if (board[0][x] == 'O' && board[1][x] == 'O' && board[2][x] == '.') {
					i = 2;
					j = x;
					send = true;
				}
				if (board[1][x] == 'O' && board[2][x] == 'O' && board[0][x] == '.') {
					i = 0;
					j = x;
					send = true;
				}

			}
			//Looking for straight patterns from the user
			if (send == false) {
				for (int x = 0; x <= 2; x++) {
					if (board[x][0] == 'X' && board[x][1] == 'X' && board[x][2] == '.') {
						i = x;
						j = 2;
						send = true;
					} else if (board[x][1] == 'X' && board[x][2] == 'X' && board[x][0] == '.') {
						i = x;
						j = 0;
						send = true;
					} else if (board[0][x] == 'X' && board[1][x] == 'X' && board[2][x] == '.') {
						i = 2;
						j = x;
						send = true;
					} else if (board[1][x] == 'X' && board[2][x] == 'X' && board[0][x] == '.') {
						i = 0;
						j = x;
						send = true;
					}
				}
			}
			// Diagonal Patterns for the AI
			if (board[0][0] == 'O' && board[0][2] == 'O' && board[1][1] == '.' && send == false) {
				i = 1;
				j = 1;
				send = true;
			} else if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == '.' && send == false) {
				i = 2;
				j = 2;
				send = true;
			} else if (board[1][1] == 'O' && board[2][2] == 'O' && board[1][1] == '.' && send == false) {
				i = 1;
				j = 1;
				send = true;
			} else if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == '.' && send == false) {
				i = 2;
				j = 0;
				send = true;
			} else if (board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == '.' && send == false) {
				i = 0;
				j = 2;
				send = true;
			} else if (board[2][0] == 'O' && board[0][2] == 'O' && board[1][1] == '.' && send == false) {
				i = 1;
				j = 1;
				send = true;
				
				//Diagonals from the user
			} else if (board[0][0] == 'X' && board[0][2] == 'X' && board[1][1] == '.' && send == false) {
				i = 1;
				j = 1;
				send = true;
			} else if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == '.' && send == false) {
				i = 2;
				j = 2;
				send = true;
			} else if (board[1][1] == 'X' && board[2][2] == 'X' && board[1][1] == '.' && send == false) {
				i = 1;
				j = 1;
				send = true;
			} else if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == '.' && send == false) {
				i = 2;
				j = 0;
				send = true;
			} else if (board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == '.' && send == false) {
				i = 0;
				j = 2;
				send = true;
			} else if (board[2][0] == 'X' && board[0][2] == 'X' && board[1][1] == '.' && send == false) {
				i = 1;
				j = 1;
				send = true;
			}
			
			// Straight Pattern for X
			else if ((board[0][0] == 'X' || board[0][2] == 'X' || board[2][0] == 'X' || board[2][2] == 'X')
					&& (board[1][1] == '.') && send == false) {
				i = 1;
				j = 1;
				send = true;
			}
			//Looking for if the User places in the corners
			else if (board[0][0] == 'X' && board[0][2] == 'X' && board[0][1] == '.' && send == false) {
				i = 0;
				j = 1;
				send = true;
			} else if (board[0][0] == 'X' && board[2][0] == 'X' && board[1][0] == '.' && send == false) {
				i = 1;
				j = 0;
				send = true;
			} else if (board[2][0] == 'X' && board[2][2] == 'X' && board[2][1] == '.' && send == false) {
				i = 2;
				j = 1;
				send = true;
			} else if (board[0][2] == 'X' && board[2][2] == 'X' && board[1][2] == '.' && send == false) {
				i = 1;
				j = 2;
				send = true;
			} else if (board[0][1] == 'X' && board[1][0] == 'X' && board[0][0] == '.' && send == false) {
				i = 0;
				j = 0;
				send = true;
			} else if (board[1][0] == 'X' && board[2][1] == 'X' && board[2][0] == '.' && send == false) {
				i = 2;
				j = 0;
				send = true;
			} else if (board[2][1] == 'X' && board[1][2] == 'X' && board[2][2] == '.' && send == false) {
				i = 2;
				j = 2;
				send = true;
			} else if (board[0][1] == 'X' && board[1][2] == 'X' && board[0][2] == '.' && send == false) {
				i = 0;
				j = 2;
				send = true;
			}
			if (send == false) {
				i = (int) (Math.random() * 3.0);
				j = (int) (Math.random() * 3.0);
			}

			if (isLegal(i, j) == true) {
				test = true;
				move(i, j);
			}
		}
		System.out.println("AI is moving ... ");

	}
}

public class Tic_Tac_Toe_APP {
	static Scanner userinput = new Scanner(System.in);

	public static void main(String[] args) {
		TTT game = new TTT();
		game.printBoard();
		for (int i = 1; i <= 5; i++) {
			game.human();
			game.printBoard();
			if ((game.winner() == true) || (i == 5)) {
				break;
			}
			game.switchPlayers();
			game.ai();
			game.printBoard();
			if ((game.winner() == true) || (i == 5)) {
				break;
			}
			game.switchPlayers();
		}

		if (game.winner() == true) {
			System.out.println("\nThe winner is " + game.player);
		} else {
			System.out.println("\nCat Game.");
		}
	}
}
