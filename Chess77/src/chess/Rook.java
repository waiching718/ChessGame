package chess;
/**This is the rook piece
 * 
 * @author Tien-Lun Li
 *
 */
public class Rook extends piece{
	
	boolean nevermoved = true;
	/**Constructor of rook
	 * 
	 * @param black
	 * @param piece
	 * @param file
	 * @param rank
	 */
	public Rook(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	/**Check if it is a valid move
	 * @param file The destination file
	 * @param rank The destination rank
	 * @return A boolean that if it is a valid move
	 */
	public boolean isvalidmove(int file, int rank){
		if(file == this.file && rank != this.rank){ //same file different ranks
			if(rank > this.rank){ //destination is at the top
				int temp = rank - this.rank; //temp holds the number of difference
				
				for(int i=1; i<=temp; i++){ //for loop to check theres pieces between the move or not
					if ( i == temp){ //successfully arrive at destination
						if(board[rank][file] ==null || board[rank][file].black == Chess.blackmove){
							return true;
						}
						return false; // taking the same-side piece
					}
					if(board[this.rank+i][file] != null){ //there's piece in between
						return false;
					}
				}
				
			}else if (rank < this.rank){ //destination is at the bottom
				int temp = this.rank - rank; //temp holds the number of difference
				
				for(int i=1; i<=temp; i++){ //for loop to check theres pieces between the move or not
					if ( i == temp){ //successfully arrive at destination
						if(board[rank][file] ==null || board[rank][file].black == Chess.blackmove){
							return true;
						}
						return false; // taking the same-side piece
					}
					if(board[this.rank-i][file] != null){ //there's piece in between
						return false;
					}
				}
			}
		}else if(file != this.file && rank == this.rank){ //same rank different files
			if(file > this.file){ //destination is on the right
				int temp = file - this.file; //temp holds the number of difference
				
				for(int i=1; i<=temp; i++){ //for loop to check theres pieces between the move or not
					if ( i == temp){ //successfully arrive at destination
						if(board[rank][file] ==null || board[rank][file].black == Chess.blackmove){
							return true;
						}
						return false; // taking the same-side piece
					}
					if(board[rank][this.file+i] != null){ //there's piece in between
						return false;
					}
				}
				
			}else if (file < this.file){ //destination on the left
				int temp = this.file - file; //temp holds the number of difference
				
				for(int i=1; i<=temp; i++){ //for loop to check theres pieces between the move or not
					if ( i == temp){ //successfully arrive at destination
						if(board[rank][file] ==null || board[rank][file].black == Chess.blackmove){
							return true;
						}
						return false; // taking the same-side piece
					}
					if(board[rank][this.file-i] != null){ //there's piece in between
						return false;
					}
				}
			}
		}// end of same rank
		return false;
	}//end of isvalidmove
	/**Move method for rook
	 * 
	 */
	public void move(int file, int rank){
		this.nevermoved = false;
		super.move(file, rank);
	}
}
