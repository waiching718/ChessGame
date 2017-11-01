package chess;

public class Queen extends piece{
	
	public Queen(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	public boolean isvalidmove(int file, int rank){
		if(this.file == file && this.rank == rank){ // remain the same place
			return false;
		}
		// Below, Queen share the similar function with bishop and rook.
		if(this.black){ // Check if there is a same color piece on the destination square
			if(board[rank][file] != null && board[rank][file].black == true){
				return false;
			}
		}
		
		if(this.black== false){
			if(board[rank][file] != null && board[rank][file].black == false){
				return false;
			}
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
		col = this.file + x; // The first position to check
		
		for(row = this.rank + y; row < this.rank; row = row + y ){ // Check if there is a blocking piece diagonally.
			if(board[row][col] != null){
				return false;
			}
			col = col + x;
		}
		
		if(this.rank == rank){
			for(col = this.file + x; col <file; col= col + x){ // Check if there is a blocking piece horizontally and vertically.
				if(board[rank][col] != null){
					return false;
				}
			}
		}
		if(this.file == file){
			for(row = this.rank + y; row < rank; row = row + y){
				if(board[row][file] != null){
					return false;
				}
			}
		}
		return true;
	}
	
}