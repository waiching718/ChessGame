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
	public abstract void move(int file, int rank);
}
