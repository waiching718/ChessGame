package chess;

public class Rook extends piece{
	
	public Rook(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	
	public boolean isvalidmove(int file, int rank){
		return false;
	}

}
