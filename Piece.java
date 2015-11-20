/*Piece.java*/

/**
 *  Represents a Normal Piece in Checkers61bl
 * @author 
 */

public class Piece {
	
	int teamSide;
	boolean isKinged;
	Board whichBoard;
	boolean pieceCaptured;
  /**
   *  Define any variables associated with a Piece object here.  These
   *  variables MUST be private or package private.
   */

  
	/**
	 * Initializes a Piece
	 * @param  side The side of the Piece
	 * * @param  b    The Board the Piece is on
	 */
	Piece(int side, Board b) {
		    //YOUR CODE HERE
		teamSide = side;
		whichBoard = b;
		isKinged = false;
			  
			  
			  
		  }
	/**
	* Returns the side that the piece is on
	* @return 0 if the piece is fire and 1 if the piece is water
	*/
  public int side() {
    //YOUR CODE HERE
	  return teamSide;
  }
  
  

  public boolean isKing() {
    //YOUR CODE HERE
	  return isKinged;
  }
  
  
  String picture() {
	  if (isKinged) {
		  if (this.side() == 1) {
			  return "img/pawn-water-crowned.png";
		  } else {
			  return "img/pawn-fire-crowned.png";
		  }
	  } else {
		  if (this.side() == 1) {
			  return "img/pawn-water.png";
		  } else {
			  return "img/pawn-fire.png";
		  }
	  }
  }

  /**
   * Destroys the piece at x, y. ShieldPieces do not blow up
   * @param x The x position of Piece to destroy
   * @param y The y position of Piece to destroy
   */
  void getBlowUp(int x, int y) {
    //YOUR CODE HERE
	  whichBoard.remove(x, y);
  }
  
  boolean isBomb() {
	  return false;
  }

  /**
   * Does nothing. For bombs, destroys pieces adjacent to it
   * @param x The x position of the Piece that will explode
   * @param y The y position of the Piece that will explode
   */
  void explode(int x, int y) {
    //Implemented 
  }

  /**
   * Signals that this Piece has begun to capture (as in it captured a Piece)
   */
  void startCapturing() {
    //YOUR CODE HERE
	  if (hasCaptured() == false) {
		  pieceCaptured = true;
	  }
  }

  /**
   * Returns whether or not this piece has captured this turn
   * @return true if the Piece has captured
   */
  public boolean hasCaptured() {
    //YOUR CODE HERE
	  return pieceCaptured;
  }

  /**
   * Resets the Piece for future turns
   */
  public void finishCapturing() {
    //YOUR CODE HERE
	  pieceCaptured = false;
	  
  }
}