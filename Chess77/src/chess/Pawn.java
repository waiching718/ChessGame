package chess;

public class Pawn extends piece{
	
	public Pawn(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	public boolean isvalidmove(int file, int rank){
		return false;
	}
	
	public void move(int file, int rank){
		
	}
}