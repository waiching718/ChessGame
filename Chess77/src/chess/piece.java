package chess;

public abstract class piece {
	
	Boolean black;
	String piece;
	int file;
	int rank;
	static piece board[][];
	
	
	
	public piece(Boolean black, String piece, int file, int rank){
		this.black = black;
		this.piece = piece;
		this.file = file;
		this.rank = rank;
	}
	
	public abstract boolean isvalidmove(int file, int rank);

	public void move(int file, int rank){
		board[rank][file] = this; // move the piece to destination
		board[this.rank][this.file] = null; //remove the original piece
		Chess.print[rank][file] = this.piece; //update printboard with piece type
		
		if (this.rank % 2 == this.file % 2){ // add hastag when necessary
			Chess.print[this.rank][this.file] = Chess.hashtag;
		}else{
			Chess.print[this.rank][this.file] = "  ";
		}
		this.rank = rank; //update rank
		this.file = file; //update file
	}
}
