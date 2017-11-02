package chess;

import java.util.*;
/**This class represents Chess
 * 
 * @author Tien-Lun Li
 *
 */
public class Chess {
	/**
	 * presents a chess board as a 8x8 2d array
	 * board is filled with hashtag and white space
	 * letters a to h for the board's file
	 * blackmove to check what color's piece turn
	 * drawPending to check if draw is pending
	 * legalmove to check if it is a legal move
	 */
	static String print[][] = new String[8][8];
	static piece board[][] = new piece[8][8];
	static String hashtag = "##";
	static String fileString = " a  b  c  d  e  f  g  h";
	static boolean blackmove = false;
	static boolean drawPending = false;
	static boolean legalmove = true;
	static piece whiteKing;
	static piece blackKing;
	
	/**Main method that run chess
	 * 
	 * @param args Run the main method
	 */
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
				takeAppropriateMove(receive); // take move
				System.out.println("");
				
				if(legalmove){
					Pawn.whiteforenpassant = null;
				}
				
				printboard();
				int ret = check(false);
				if(ret == 1){
					System.out.println("Check");
				}else if(ret == 2){
					System.out.println("Black wins");
					System.exit(0);
				}
				 //print board
				// more to go, not yet completed
				
			}else{
				blackmove = true;
				System.out.print("White's move: ");
				receive = input.nextLine(); // take message
				takeAppropriateMove(receive); // take move
				System.out.println("");
				
				if (legalmove){
					Pawn.blackforenpassant = null;
				}
				printboard(); //print board
				int ret = check(true);
				if(ret == 1){
					System.out.println("Check");
				}else if(ret == 2){
					System.out.println("White wins");
					System.exit(0);
				}
				
				
			}
		}
	}
	
	public static int check(boolean black){
		int file, rank;
		if(black){ //black king
			file = blackKing.file; rank = blackKing.rank; boolean check; boolean checkmate = true;
			check = King.isChecked(rank, file);
			
			if(check == false){
				return 0; //no check
			}
			board[rank][file] = null; //clear the original place
			
			for(int a = -1; a<2;a++){ //loop
				for(int b = -1; b<2; b++){ //loop
					
					if(a == 0 && b == 0){ //same place
						continue;
					}
					if((file+a) > -1 && (file+a) < 8 && (rank+b) > -1 && (rank+b) < 8){ //test for ischecked
						piece temp = null;
						if(board[rank+b][file+a] != null && board[rank+b][file+a].black){ //destination black piece
							continue;
						}else{ //null or opponent piece
							temp = board[rank+b][file+a]; // store the piece in dest
							board[rank+b][file+a] = blackKing; // push blackKingup
							if(King.isChecked(rank+b, file+a) == false){ //no checkmate
								board[rank+b][file+a] = temp;
								board[rank][file]  = blackKing;
								return 1; //check but not checkmate
							}
							board[rank+b][file+a] = temp;
						}
					}
				}
			}
			board[rank][file] = blackKing;
			return 2;
		}else{ //white
			file = whiteKing.file; rank = whiteKing.rank; boolean check; boolean checkmate = true;
			check = King.isChecked(rank, file);
			
			if(check == false){
				return 0; //no check
			}
			board[rank][file] = null; //clear the original place
			
			for(int a = -1; a<2;a++){ //loop
				for(int b = -1; b<2; b++){ //loop
					
					if(a == 0 && b == 0){ //same place
						continue;
					}
					if((file+a) > -1 && (file+a) < 8 && (rank+b) > -1 && (rank+b) < 8){ //test for ischecked
						piece temp = null;
						if(board[rank+b][file+a] != null && board[rank+b][file+a].black == false){ //destination black piece
							continue;
						}else{ //null or opponent piece
							temp = board[rank+b][file+a]; // store the piece in dest
							board[rank+b][file+a] = whiteKing; // push blackKingup
							if(King.isChecked(rank+b, file+a) == false){ //no checkmate
								board[rank+b][file+a] = temp;
								board[rank][file]  = whiteKing;
								return 1; //check but not checkmate
							}
							board[rank+b][file+a] = temp;
						}
					}
				}
			}
			board[rank][file] = whiteKing;
			return 2;
		}
	}
	
	/**From receive, know what actions to do
	 * Move, promote, resign, draw, error
	 * 
	 * @param receive input to be parsed
	 */
	public static void takeAppropriateMove(String receive){
		if(inputparse(receive) == 1){ //"filerank filerank"
			int file = receive.charAt(0) - 97; int rank = receive.charAt(1) - 49;
			piece movingPiece = board[rank][file]; //moving piece
			
			if(movingPiece == null || movingPiece.black == blackmove){ //moving piece doesn't exist
				printerrormessage();
				blackmove = !blackmove; // same side moves
			}else{
				file = receive.charAt(3) - 97; rank = receive.charAt(4) - 49; //which square moving towards
				if(movingPiece.isvalidmove(file, rank)){ //valid move
					movingPiece.move(file, rank); //move to the square
					legalmove = true;
					return;
				}else{ //not valid move;
					printerrormessage();
					blackmove = !blackmove; // same side moves
				}
			}
		}else if(inputparse(receive) == 2){	
			//promotion
			char promotion = receive.charAt(6);
			int file = receive.charAt(0) - 97; int rank = receive.charAt(1) - 49;
			piece movingPiece = board[rank][file]; //moving piece
			
			if(movingPiece == null || movingPiece.black == blackmove || !(movingPiece instanceof Pawn)){ //moving piece doesn't exist
				printerrormessage();
				blackmove = !blackmove; // same side moves
			}else{
				file = receive.charAt(3) - 97; rank = receive.charAt(4) - 49; //which square moving towards
				if(movingPiece.isvalidmove(file, rank)){ //valid move
					((Pawn) movingPiece).promotion = promotion;// change the promotion char to user's input
					movingPiece.move(file, rank); //move to the square
					legalmove = true;
					return;
				}else{ //not valid move;
					printerrormessage();
					blackmove = !blackmove; // same side moves
				}
			}
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
			
			if(movingPiece == null || movingPiece.black == blackmove){ //moving piece doesn't exist
				printerrormessage();
				blackmove = !blackmove; // same side moves
			}else{
				file = receive.charAt(3) - 97; rank = receive.charAt(4) - 49; //which square moving towards
				if(movingPiece.isvalidmove(file, rank)){ //valid move
					movingPiece.move(file, rank); //move to the square
					drawPending = true;//set drawPending to true
					legalmove = true;
					return;
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
			blackmove = !blackmove; // same side moves
		}
		legalmove = false;
	}
	
	/**Print message if any errors
	 * 
	 */
	public static void printerrormessage(){
		System.out.println("Illegal move, try again");
	}
	
	/**parse the input and return some int corresponding to some message
	 * @param input using regular expression to parse input
	 * @return a int for 6 kinds of conditions
	 */
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
	
	/**Initialize the board
	 * Put white pieces and black pieces on their spot
	 */
	public static void initialize(){
		//white side
		board[0][0] = new Rook(false,"wR",0,0);
		board[0][1] = new Knight(false,"wN",1,0);
		board[0][2] = new Bishop(false,"wB",2,0);
		board[0][3] = new Queen(false,"wQ",3,0);
		board[0][4] = new King(false,"wK",4,0);
		whiteKing = board[0][4];
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
		blackKing = board[7][4];
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
	
	/**Print the board 
	 * Print the board after every valid moves
	 */
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



