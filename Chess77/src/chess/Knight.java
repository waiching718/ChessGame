package chess;

public class Knight extends piece{
	
	public Knight(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	public boolean isvalidmove(int file, int rank){
		return false;
	}
	
	public void move(int file, int rank){
		
	}
}
