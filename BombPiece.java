/*BombPiece.java*/

/**
 *  Represents a BombPiece ins Checkers61bl
 * @author 
 */

public class BombPiece extends Piece {

  /**
   *  Define any variables associated with a BombPiece object here.  These
   *  variables MUST be private or package private.
   */

  /**
   * Constructs a new BombPiece
   * @param  side what side this BombPiece is on
   * @param  b    Board that this BombPiece belongs to
   */
  public BombPiece(int side, Board b) {
    //YOUR CODE HERE
	  super(side, b);

  }
  
  boolean isBomb() {
	  return true;
  }
  
  String picture() {
	  if (this.isKinged) {
		  if (this.side() == 0) {
			  return "img/bomb-fire-crowned.png";
		  } else {
			  return "img/bomb-water-crowned.png";
		  }
	  } else {
		  if (this.side() == 0) {
			  return "img/bomb-fire.png";
		  } else {
			  return "img/bomb-water.png";
		  }
	  }
  }
  
  
  
  void explode(int x, int y) {
	  for (int j = (x-1); j <= x + 1; j++) {
		  for (int i = (y-1); i <= y + 1; i++) {
			  if (i >= 0 && j >= 0 && i < 8 && j < 8 && whichBoard.pieceAt(j, i) != null) {
				  whichBoard.pieces[j][i].getBlowUp(j, i);
			  }
		  }
	  }
	  whichBoard.remove(x, y);
  }
}
