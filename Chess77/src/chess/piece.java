package chess;
/**This is the piece class that has the general methods.
 * 
 * @author Tien-Lun Li
 *
 */
public abstract class piece {
	/** present the piece, piece's color, piece's file and rank
	 *
	 */
	Boolean black;
	String piece;
	int file;
	int rank;
	static piece board[][];
	
	
	/**Constructor of piece
	 * 
	 * @param black
	 * @param piece
	 * @param file
	 * @param rank
	 */
	public piece(Boolean black, String piece, int file, int rank){
		this.black = black;
		this.piece = piece;
		this.file = file;
		this.rank = rank;
	}
	
	/**Check if it is a valid move
	 * @param file The destination file
	 * @param rank The destination rank
	 * @return A boolean that if it is a valid move
	 */
	public abstract boolean isvalidmove(int file, int rank);
	/**Move the piece
	 * 
	 * @param file
	 * @param rank
	 */
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
