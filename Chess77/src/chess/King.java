package chess;

public class King extends piece{
	
	public King(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	public boolean isvalidmove(int file, int rank){
		return false;
	}
	
}