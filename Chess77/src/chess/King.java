package chess;
/**This is the king piece
 * 
 * @author Tien-Lun Li
 *
 */
public class King extends piece{
	/**nevermoved checks if king is never moved
	 * castling checks if king is castling 
	 * 
	 */
	boolean nevermoved = true;
	boolean castling = false;
	/**Constructor of King
	 * 
	 * @param black
	 * @param piece
	 * @param file
	 * @param rank
	 */
	public King(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	/**Check if it is a valid move
	 * @param file The destination file
	 * @param rank The destination rank
	 * @return A boolean that if it is a valid move
	 */
	public boolean isvalidmove(int file, int rank){
		
		if(this.file == file && this.rank == rank){ // remain the same place
			return false;
		}
		
		if(this.black){ // Check if there is a same color piece on the destination square
			if(board[rank][file] != null && board[rank][file].black == true){
				return false;
			}
		}
		
		if(this.black== false){ // Check if there is a same color piece on the destination square
			if(board[rank][file] != null && board[rank][file].black == false){
				return false;
			}
		}
		
		if(Math.abs(this.rank - rank) <= 1 && Math.abs(this.file-file) <= 1){ //king's move
			return true;
		}
		
		if(this.nevermoved && (Math.abs(file-this.file) == 2) && (rank == this.rank)){ //check for castling
			if(this.black){ //black King
				if(file > this.file && board[7][7] != null && board[7][7] instanceof Rook && ((Rook)board[7][7]).nevermoved){ //castling the rook on the right side
					if(board[7][5] != null || board[7][6] != null){ //pieces in between
						return false;
					}else{ //check under check
						board[this.rank][this.file] = null;
						for(int i =1; i <=2; i++){ // go through position to check 
							board[this.rank][this.file+i] = this;
							if(King.isChecked(this.rank, this.file+i)){ //path is checked
								board[this.rank][this.file+i] = null;
								board[this.rank][this.file] = this;
								return false;
							}
							board[this.rank][this.file+i] = null;
						}
						board[this.rank][this.file] = this; // out of loop pass the test
						castling = true;
						return true;
					}
				}else if (file < this.file && board[7][0] != null && board[7][0] instanceof Rook && ((Rook)board[7][0]).nevermoved){//castling the rook on the left side
					if(board[7][3] != null || board[7][2] != null || board[7][1] != null){ //pieces in between
						return false;
					}else{ //check under check
						board[this.rank][this.file] = null;
						for(int i =-1; i >= -3; i--){ // go through position to check 
							board[this.rank][this.file+i] = this;
							if(King.isChecked(this.rank, this.file+i)){ //path is checked
								board[this.rank][this.file+i] = null;
								board[this.rank][this.file] = this;
								return false;
							}
							board[this.rank][this.file+i] = null;
						}
						board[this.rank][this.file] = this; // out of loop pass the test
						castling = true;
						return true;
					}
				}
			}else{ //White King
				if(file > this.file && board[0][7] != null && board[0][7] instanceof Rook && ((Rook)board[0][7]).nevermoved){ //castling the rook on the right side
					if(board[0][5] != null || board[0][6] != null){ //pieces in between
						return false;
					}else{ //check under check
						board[this.rank][this.file] = null;
						for(int i =1; i <=2; i++){ // go through position to check 
							board[this.rank][this.file+i] = this;
							if(King.isChecked( this.rank, this.file+i)){ //path is checked
								board[this.rank][this.file+i] = null;
								board[this.rank][this.file] = this;
								return false;
							}
							board[this.rank][this.file+i] = null;
						}
						board[this.rank][this.file] = this; // out of loop pass the test
						castling = true;
						return true;
					}
				}else if (file < this.file && board[0][0] != null && board[0][0] instanceof Rook && ((Rook)board[0][0]).nevermoved){//castling the rook on the left side
					if(board[0][3] != null || board[0][2] != null || board[0][1] != null){ //pieces in between
						return false;
					}else{ //check under check
						board[this.rank][this.file] = null;
						for(int i =-1; i >= -3; i--){ // go through position to check 
							board[this.rank][this.file+i] = this;
							if(King.isChecked(this.rank, this.file+i)){ //path is checked
								board[this.rank][this.file+i] = null;
								board[this.rank][this.file] = this;
								return false;
							}
							board[this.rank][this.file+i] = null;
						}
						board[this.rank][this.file] = this; // out of loop pass the test
						castling = true;
						return true;
					}
				}
			} //whiteking end
		}// castling
		
		return false;
	}
	/**
	 * King's moves is different than other pieces
	 */
	public void move(int file, int rank){
		this.nevermoved = false;
		
		if(castling == false){ //ordinary move
			super.move(file, rank);
		}else{ //castling
			castling = false;
			piece rook;
			if(file >4){ //right side
				super.move(file, rank);
				if(this.black){ //black
					rook = board[7][7];
				}else{ //white
					rook = board[0][7];
				}
				rook.move(file-1, rank);
			}else{ //left side
				super.move(file, rank);
				if(this.black){ //black
					rook = board[7][0];
				}else{ //white
					rook = board[0][0];
				}
				rook.move(file+1, rank);
			}
		}
	}
	/** King can be checked by other pieces
	 * @param rank
	 * @param file
	 * @return a boolean that if King is checked 
	 */
	public static boolean isChecked(int rank, int file){
		piece side = board[rank][file];
		
		for(int a = 0; a < 8; a++){ //loop a
			for(int b = 0; b < 8; b++){ //loop b
				if(board[a][b] != null && (board[a][b].black != side.black) && board[a][b].isvalidmove(file, rank)){ //can check
					return true;
				}
			}
		}
		return false;
	}
	
}