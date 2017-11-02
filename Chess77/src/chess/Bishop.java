package chess;
/**This is the Bishop piece
 *@author Wai Ching Li
 *
 */
public class Bishop extends piece{
	/**Constructor of bishop
	 * 
	 * @param black
	 * @param piece
	 * @param file
	 * @param rank
	 */
	public Bishop(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	/**Check if it is a valid move
	 * @param file The destination file
	 * @param rank The destination rank
	 * @return A boolean that if it is a valid move
	 */
	public boolean isvalidmove(int file, int rank){
		// If move correctly, diff of rank and file have to be the same
		if(Math.abs(file -this.file) != Math.abs(rank-this.rank)){ 
			return false;
		}
		if(this.black){ // Check if there is a same color piece on the destination square
			if(board[rank][file] != null && board[rank][file].black == true){
				return false;
			}
		}
		
		if(this.black== false){
			if(board[rank][file] != null && board[rank][file].black != true){
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
		
		for(row = this.rank + y; row < rank; row = row + y ){ // Check if there is a blocking piece.
			if(board[row][col] != null){
				return false;
			}
			col = col + x;
		}
		
		return true;
	}
	
}