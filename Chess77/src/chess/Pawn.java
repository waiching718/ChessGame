package chess;
/**This is the pawn piece
 * 
 * @author Tien-Lun Li
 *
 */
public class Pawn extends piece{
	/**
	 * present promotion
	 * present enpassant
	 */
	char promotion = 'Q';
	static Pawn whiteforenpassant = null;
	static Pawn blackforenpassant = null;
	static boolean enpassant = false;
	/**Constructor of pawn
	 * 
	 * @param black
	 * @param piece
	 * @param file
	 * @param rank
	 */
	public Pawn(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	/**Check if it is a valid move
	 * @param file The destination file
	 * @param rank The destination rank
	 * @return A boolean that if it is a valid move
	 */
	public boolean isvalidmove(int file, int rank){
		
		if(this.black){ //black pawn
			if(file == this.file){ // moving rather than taking opponents pieces
				if (this.rank == 6){ // first pawn move
					if(rank - this.rank == -2){ // move two rank down
						if (board[this.rank-1][file] == null && board[this.rank-2][file] == null){ // no pieces in between the move
							blackforenpassant = this;
							return true;
						}
					}else if(rank - this.rank == -1){ // first move but move one rank
						if (board[this.rank-1][file] == null){ // no pieces in between the move
							return true;
						}
					}
				}else{ //not first move
					if (rank - this.rank == -1){ // check valid move (down only one rank)
						if (board[this.rank-1][file] == null){ // no pieces in between the move
							return true;
						}
					}
				}
			}else if(file == this.file+1 || file == this.file-1){ //taking pieces out or move(en peasant)
				if (rank - this.rank == -1){ //down one square
					if(board[rank][file] != null && board[rank][file].black ==false){ //destination is a black piece
						return true;
					}else if(file == this.file+1){ // check for right enpassant
						if (whiteforenpassant == board[this.rank][this.file+1]){ //check the pawn next to the moving pawn
							enpassant = true;
							return true;
						}
					}else if (file == this.file-1){ // check for left enpassant
						if(whiteforenpassant == board[this.rank][this.file-1]){ //check the pawn next to the moving pawn
							enpassant = true;
							return true;
						}
					}
				} 
			}else{ //illegalmove
				return false;
			}//end of white pawn if 
			return false; //catch all possibilities
			
		}else{ //white pawn
			if(file == this.file){ // moving rather than taking opponents pieces
				if (this.rank == 1){ // first pawn move
					if(rank - this.rank == 2){ // move two rank up
						if (board[this.rank+1][file] == null && board[this.rank+2][file] == null){ // no pieces in between the move
							whiteforenpassant = this;
							return true;
						}
					}else if(rank - this.rank == 1){ // first move but move one rank
						if (board[this.rank+1][file] == null){ // no pieces in between the move
							return true;
						}
					}
				}else{ //not first move
					if (rank - this.rank == 1){ // check valid move (up only one rank)
						if (board[this.rank+1][file] == null){ // no pieces in between the move
							return true;
						}
					}
				}
			}else if(file == this.file+1 || file == this.file-1){ //taking pieces out or move(en peasant)
				if (rank - this.rank == 1){ //up one square
					if(board[rank][file] != null && board[rank][file].black ==true){ //destination is a black piece
						return true;
					}else if(file == this.file+1){ // check for right enpassant
						if (blackforenpassant == board[this.rank][this.file+1]){ //check the pawn next to the moving pawn
							enpassant = true;
							return true;
						}
					}else if (file == this.file-1){ // check for left enpassant
						if(blackforenpassant == board[this.rank][this.file-1]){ //check the pawn next to the moving pawn
							enpassant = true;
							return true;
						}
					}
				}	
			}else{ //illegalmove
				return false;
			}//end of white pawn if 
			return false; //catch all possibilities
		}
		
	}//end of the function isvalidmove
	/**Special moves for pawn and promotion
	 * @param file The destination file
	 * @param rank The destination rank
	 */
	public void move(int file, int rank){
		
		if (rank == 7 || rank == 0){ // qualified for promotion
			piece temp = null;
			if (promotion == 'Q'){ // promote to queen
				temp = new Queen(this.black, "" + this.piece.charAt(0)+promotion, file , rank);
			}else if(promotion == 'N'){ // promote to Knight
				temp = new Knight(this.black, "" + this.piece.charAt(0)+promotion, file , rank);
			}else if(promotion == 'B'){ // promote to Bishop
				temp = new Bishop(this.black, "" + this.piece.charAt(0)+promotion, file , rank);
			}else if(promotion == 'R'){ // promote to Rook
				temp = new Rook(this.black, "" + this.piece.charAt(0)+promotion, file , rank);
			}
			board[rank][file] = temp;// move the piece to destination
			board[this.rank][this.file] = null; //remove the original piece
			Chess.print[rank][file] = temp.piece; //update printboard with piece type
			
			if (this.rank % 2 == this.file % 2){ // add hastag when necessary
				Chess.print[this.rank][this.file] = Chess.hashtag;
			}else{
				Chess.print[this.rank][this.file] = "  ";
			}
			return;
		}
		
		if (enpassant){ //take the pawn out by enpassant
			enpassant = false;
			if(Chess.blackmove){ //white turn (reversed)
				board[rank-1][file] = null;
				if ((rank-1) % 2 == file % 2){ // add hastag when necessary
					Chess.print[rank-1][file] = Chess.hashtag;
				}else{
					Chess.print[rank-1][file] = "  ";
				}
			}else{ //black turn
				board[rank+1][file] = null;
				if ((rank+1) % 2 == file % 2){ // add hastag when necessary
					Chess.print[rank+1][file] = Chess.hashtag;
				}else{
					Chess.print[rank+1][file] = "  ";
				}
			}
		}
		
		super.move(file, rank); // call ordinary move
		
		
		
	}
}