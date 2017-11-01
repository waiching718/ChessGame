package chess;

public class Bishop extends piece{
	
	public Bishop(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	
	public boolean isvalidmove(int file, int rank){
		if(file == this.file || rank == this.rank){ // have to move diagonally 
			return false;
		}
		
		if(Math.abs(file -this.file) != Math.abs(rank-this.rank)){ // If move correctly, diff of rank and file have to be the same
			return false;
		}
		
		//below, check if there is a blocking piece.
		int x,y;
		
		if(this.file > file){ // x to be stored to 1 or -1 for checking every column between 
			x = -1;		  // the piece's location and destination.
		}else{
			x= 1;
		}
		
		if(this.rank > rank){ //y to be stored to 1 or -1 for checking every row between
			y = -1;		  // the piece's location and destination.
		}else{
			y = 1;
		}
		int row; 
		int col; 
		col = file + x; // The first position to check
		
		if(this.black){ // For black bishop
			for(row = rank + y; row < this.rank; row = row + y ){ // Check if there is a blocking piece.
				if(board[row][col] != null && board[row][col].black == true){ // Can not move there if that is a black piece
				return false;
				}
				col = col + x;
			}
		}else{ // For White bishop
			for(row = rank + y; row < this.rank; row = row + y ){ // Check if there is a blocking piece.
				if(board[row][col] != null && board[row][col].black == false){ // Can not move there if that is a white peice
				return false;
				}
				col = col + x;
			}
		}
		
		return true;
	}
	
}