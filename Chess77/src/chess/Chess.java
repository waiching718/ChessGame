package chess;

import java.util.*;

public class Chess {

	static String print[][] = new String[8][8];
	static piece board[][] = new piece[8][8];
	static String hashtag = "##";
	static String fileString = " a  b  c  d  e  f  g  h";
	static boolean blackmove = false;
	
	public static void main(String[] args) {
		//intitialize the board
		initialize();
		printboard();
		
		Scanner input = new Scanner(System.in);
		String receive;
		
		while(true){
			//check if its a black move
			if (blackmove){
				blackmove = false;
				System.out.println("Black's move: ");
				receive = input.nextLine();
				
			}else{
				blackmove = true;
				System.out.println("White's move: ");
				receive = input.nextLine();
				System.out.println(inputparse(receive));
			}
		}
	}
	
	
	public static int inputparse(String input){
		
		if (input.matches("[a-h]\\d\\s[a-h]\\d")){ //"FileRank FileRank"
			return 1;
		}else if (input.matches("[a-h]\\d\\s[a-h]\\d\\s[QRBN]")){ //Promotion "FileRank FileRank [QRBN]"
			return 2;
		}else if(input.equals("resign")){ //resign
			return 3;
		}else if(input.matches("[a-h]\\d\\s[a-h]\\d\\sdraw\\?")){ //draw pending
			return 4;
		}else if(input.equals("draw")){ //draw
			return 5;
		}else{ //error
			return 6;
		}
	}
	
	//initialize the board
	public static void initialize(){
		//white side
		board[0][0] = new Rook(false,"wR");
		board[0][1] = new Knight(false,"wN");
		board[0][2] = new Bishop(false,"wB");
		board[0][3] = new Queen(false,"wQ");
		board[0][4] = new King(false,"wK");
		board[0][5] = new Bishop(false,"wB");
		board[0][6] = new Knight(false,"wN");
		board[0][7] = new Rook(false,"wR");
		
		for(int i = 0; i<8; i++){
			board[1][i] = new Pawn(false,"wp");
		}
		//black side
		board[7][0] = new Rook(true,"bR");
		board[7][1] = new Knight(true,"bN");
		board[7][2] = new Bishop(true,"bB");
		board[7][3] = new Queen(true,"bQ");
		board[7][4] = new King(true,"bK");
		board[7][5] = new Bishop(true,"bB");
		board[7][6] = new Knight(true,"bN");
		board[7][7] = new Rook(true,"bR");
		
		for(int i = 0; i<8; i++){
			board[6][i] = new Pawn(true,"bp");
		}
		
		//initialize the print board
		for(int a=7; a>-1; a--){
			for(int b=7; b>-1; b--){
				//check if there is a piece
				if(board[a][b] != null){
					print[a][b] =board[a][b].piece;
				}else if( a%2 == b%2){ //if there's no piece, fill in the hashtags
					print[a][b] = hashtag;
				}else{  //fill in whitespaces if there's no piece and not a black square
					print[a][b] = "  ";
				}
			}
		}
	}

	//print the board
	public static void printboard(){
		
		//printing
		for(int a=7; a>-1; a--){
			for(int b=7; b>-1; b--){
				System.out.print(print[a][b] + " ");
			}
			System.out.println((char)(a+1+48) + " "); // convert to corresponding ASCII and +1 for rank
		}
		
		System.out.println(fileString + "\n");
		
	}
}



