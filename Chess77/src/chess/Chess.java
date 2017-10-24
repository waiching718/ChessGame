package chess;

import java.util.*;

public class Chess {

	static String print[][] = new String[8][8];
	static piece board[][] = new piece[8][8];
	static String hashtag = "##";
	static String fileString = " a  b  c  d  e  f  g  h";
	static boolean blackmove = false;
	static boolean drawPending = false;
	
	public static void main(String[] args) {
		//intitialize the board
		initialize();
		printboard();
		piece.board = board;
		
		Scanner input = new Scanner(System.in);
		String receive;
		
		while(true){
			//check if its a black move
			if (blackmove){
				blackmove = false;
				System.out.print("Black's move: ");
				receive = input.nextLine();
				System.out.println(inputparse(receive));
				takeAppropriateMove(receive); // take move
				// more to go, not yet completed
				
			}else{
				blackmove = true;
				System.out.print("White's move: ");
				receive = input.nextLine(); // take message
				System.out.println(inputparse(receive)); // for testing show input corresponding message
				takeAppropriateMove(receive); // take move
				printboard(); //print board
				// more to go, not yet completed
			}
		}
	}
	
	public static void takeAppropriateMove(String receive){
		if(inputparse(receive) == 1){ //"filerank filerank"
			int file = receive.charAt(0) - 97; int rank = receive.charAt(1) - 49;
			piece movingPiece = board[rank][file]; //moving piece
			
			if(movingPiece == null){ //moving piece doesn't exist
				printerrormessage();
				blackmove = !blackmove; // same side moves
			}else{
				file = receive.charAt(3) - 97; rank = receive.charAt(4) - 49; //which square moving towards
				if(movingPiece.isvalidmove(file, rank)){ //valid move
					movingPiece.move(file, rank); //move to the square
				}else{ //not valid move;
					printerrormessage();
					blackmove = !blackmove; // same side moves
				}
			}
		}else if(inputparse(receive) == 2){
			//promotion
		}else if (inputparse(receive) == 3){
			//resign
			if (blackmove){ //white resign
				System.out.println("Black wins");
			}else{
				System.out.println("White wins");
			}
			System.exit(0);//exit the program
			
		}else if (inputparse(receive) == 4){
			//someone pending to draw
			int file = receive.charAt(0) - 97; int rank = receive.charAt(1) - 49;
			piece movingPiece = board[rank][file]; //moving piece
			
			if(movingPiece == null){ //moving piece doesn't exist
				printerrormessage();
				blackmove = !blackmove; // same side moves
			}else{
				file = receive.charAt(3) - 97; rank = receive.charAt(4) - 49; //which square moving towards
				if(movingPiece.isvalidmove(file, rank)){ //valid move
					movingPiece.move(file, rank); //move to the square
					drawPending = true;//set drawPending to true
				}else{ //not valid move;
					printerrormessage();
					blackmove = !blackmove; // same side moves
				}
			}
			
		}else if (inputparse(receive) == 5){
			//accept draw
			if(drawPending){
				System.out.println("Draw");
				System.exit(0);
			}
			
		}else{
			//print error message
			System.out.println("Invalid input. Please try again.");
		}
	}
	
	//print error message
	public static void printerrormessage(){
		System.out.println("Illegal move, try again");
	}
	
	//parse the input and return some int corresponding to some message
	public static int inputparse(String input){
		
		if (input.matches("[a-h]\\d\\s[a-h]\\d\\s*")){ //"FileRank FileRank"
			return 1;
		}else if (input.matches("[a-h]\\d\\s[a-h]\\d\\s[QRBN]\\s*")){ //Promotion "FileRank FileRank [QRBN]"
			return 2;
		}else if(input.matches("resign\\s*")){ //resign
			return 3;
		}else if(input.matches("[a-h]\\d\\s[a-h]\\d\\sdraw\\?\\s*")){ //draw pending
			return 4;
		}else if(input.matches("draw\\s*")){ //draw
			return 5;
		}else{ //error
			return 6;
		}
	}
	
	//initialize the board
	public static void initialize(){
		//white side
		board[0][0] = new Rook(false,"wR",0,0);
		board[0][1] = new Knight(false,"wN",1,0);
		board[0][2] = new Bishop(false,"wB",2,0);
		board[0][3] = new Queen(false,"wQ",3,0);
		board[0][4] = new King(false,"wK",4,0);
		board[0][5] = new Bishop(false,"wB",5,0);
		board[0][6] = new Knight(false,"wN",6,0);
		board[0][7] = new Rook(false,"wR",7,0);
		
		for(int i = 0; i<8; i++){
			board[1][i] = new Pawn(false,"wp",i,1);
		}
		//black side
		board[7][0] = new Rook(true,"bR",0,7);
		board[7][1] = new Knight(true,"bN",1,7);
		board[7][2] = new Bishop(true,"bB",2,7);
		board[7][3] = new Queen(true,"bQ",3,7);
		board[7][4] = new King(true,"bK",4,7);
		board[7][5] = new Bishop(true,"bB",5,7);
		board[7][6] = new Knight(true,"bN",6,7);
		board[7][7] = new Rook(true,"bR",7,7);
		
		for(int i = 0; i<8; i++){
			board[6][i] = new Pawn(true,"bp",i,6);
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
			for(int b=0; b<8; b++){
				System.out.print(print[a][b] + " ");
			}
			System.out.println((char)(a+1+48) + " "); // convert to corresponding ASCII and +1 for rank
		}
		
		System.out.println(fileString + "\n");
		
	}
}



