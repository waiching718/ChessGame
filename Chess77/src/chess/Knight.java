package chess;

public class Knight extends piece{
	
	public Knight(Boolean black, String piece, int file, int rank){
		super(black,piece, file, rank);
	}
	
	public boolean isvalidmove(int file, int rank){
		if(rank - this.rank == -2 && file - this.file == 1){ // first check if the destination is valid for knight with 8 different ways.
			return true;
		}else if(rank - this.rank == -2 && file - this.file == -1){
			return true;
		}else if(rank - this.rank == 2 && file - this.file == 1){
			return true;
		}else if(rank - this.rank == 2 && file - this.file == -1){
			return true;
		}else if(rank - this.rank == 1 && file - this.file == -2){
			return true;
		}else if(rank - this.rank == -1 && file - this.file == -2){
			return true;
		}else if(rank - this.rank == 1 && file - this.file == 2){
			return true;
		}else if(rank - this.rank == -1 && file - this.file == 2){
			return true;
		}
		return false;
	}
}
