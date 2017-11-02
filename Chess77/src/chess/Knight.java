package chess;
/**This is the knight piece
 * 
 * @author Wai Ching Li
 *
 */
public class Knight extends piece{
	/**Constructor of knight 
	 * 
	 * @param black
	 * @param piece
	 * @param file
	 * @param rank
	 */
	public Knight(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	/**Check if it is a valid move
	 * @param file The destination file
	 * @param rank The destination rank
	 * @return A boolean that if it is a valid move
	 */
	public boolean isvalidmove(int file, int rank){		
		// Checking 8 possible knight moves
		if(Math.abs(this.rank - rank) == 2 && Math.abs(this.file - file) == 1){
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
			return true;
		}
		
		
		if(Math.abs(this.rank - rank) == 1 && Math.abs(this.file - file) == 2){
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
			return true;
		}
		return false;
	}
}
