/*ShieldPiece.java*/

/**
 * Represents a ShieldPiece in Checkers61bl
 * @author 
 */

public class ShieldPiece extends Piece {
  /**
   *  Define any variables associated with a ShieldPiece object here.  These
   *  variables MUST be private or package private.
   */
  
  /**
   * Constructs a new ShieldPiece
   * @param  side what side this ShieldPiece is on
   * @param  b    Board that this ShieldPiece belongs to
   */
  public ShieldPiece(int side, Board b) {
    //YOUR CODE HERE
	  super(side, b);
	  }
  
  
  void getBlowUp(int x, int y) {
	  }
  
  String picture() {
	  if (this.isKinged) {
		  if (this.side() == 0) {
			  return "img/shield-fire-crowned.png";
		  } else {
			  return "img/shield-water-crowned.png";
		  }
	  } else {
		  if (this.side() == 0) {
			  return "img/shield-fire.png";
		  } else {
			  return "img/shield-water.png";
		  }
	  }
  }
  
}
