package chess;

public class Pawn extends piece{
	
	public Pawn(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	public boolean isvalidmove(int file, int rank){
		
		if(this.black){ //black pawn
			return true;
		}else{ //white pawn
			if(file == this.file){ // moving rather than taking opponents pieces
				if (this.rank == 1){ // first pawn move
					if(rank - this.rank == 2){ // move two rank up
						if (board[this.rank+1][file] == null && board[this.rank+2][file] == null){ // no pieces in between the move
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
					}
				}
			}else{ //illegalmove
				return false;
			}//end of white pawn if 
			return false; //catch all possibilities
		}
		
	}//end of the function isvalidmove
	
	public void move(int file, int rank){
		board[rank][file] = this; // move the piece to destination
		board[this.rank][this.file] = null; //remove the original piece
		Chess.print[rank][file] = this.piece; //update printboard with piece type
		
		if (this.rank % 2 == this.file % 2){ // add hastag when necessary
			Chess.print[this.rank][this.file] = Chess.hashtag;
		}else{
			Chess.print[this.rank][this.file] = "  ";
		}
		this.rank = rank; //update rank
		this.file = file; //update file
	}
}