/*Board.java*/

/**
 * Represents a Board configuration of a game of Checkers61bl
 * @author
 */

public class Board {
	int side;
	Piece[][] pieces;
	boolean hasSelected;
	boolean hasMoved;
	int selectedPieceX;
	int selectedPieceY;
	Piece selectedPiece;
	int fireCount;
	int waterCount;
	boolean gameContiunes;
	boolean pieceReadyForMulti;
	 
  public Board(boolean shouldBeEmpty) {
	  fireCount = 12;
	  waterCount = 12;
	  gameContiunes = true;
	  if (shouldBeEmpty == false) {
			pieces = new Piece[8][8];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					
					if (i % 2 == 0) {
		    			if (j == 0) {
		    				pieces[i][j] = new Piece(0, this);
		    			}
		    			if (j == 2) {
		    				pieces[i][j] = new BombPiece(0, this);
		    			}
		    		} else {
		    			if (j == 1) {
		    				pieces[i][j] = new ShieldPiece(0, this);
		    			}
		    		}
			        if (i % 2 == 1) {
		    			if (j == 7) {
		    				pieces[i][j] = new Piece(1, this);
		    			}
		    			if (j == 5) {
		    				pieces[i][j] = new BombPiece(1, this);
		    			}
		    		} else {
		    			if (j == 6) {
		    				pieces[i][j] = new ShieldPiece(1, this);
		    			}
		    		}
				}
			}
	  } else {
		  pieces = new Piece[8][8];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					pieces[i][j] = null;
				}
			}
	  }
	  }

  public Piece pieceAt(int x, int y) {
    if (x < 0 || x >= 8 || y < 0 || y >= 8) {
    	return null;
    } else {
      return pieces[x][y];
    }
  }

  public void place(Piece p, int x, int y) {
    if (x < 8 && x >= 0 && y < 8 && y >= 0 && p != null) {
      pieces[x][y] = p;
    }
  }

  public Piece remove(int x, int y) {
	  if (pieces[x][y] == null || x < 0 || x > 7 || y < 0 || y > 7) {
	      return null;
	  } else {
		  Piece temp = pieces[x][y];
		  if (pieces[x][y].side() == 0) {
			  fireCount = fireCount - 1;
		  } else if (pieces[x][y].side() == 1) {
			  waterCount = waterCount -1;
		  }
		  pieces[x][y] = null;
		  return temp;
	  }
  }
  
  boolean multiCaptureValidMove(int x1, int y1, int x2, int y2) {
	  if (pieces[x1][y1] != null) {
		  //King 4-Way Movement
		  if (pieceAt(x1, y1).isKing()) {
			  if (pieceAt(x1, y1).side() == 0) {
				  if (y1 + 1 >= 0 && y1 + 1 < 8 && y1 + 2 >= 0 && y1 + 2 < 8 && y1 == y2 - 2) {
//			  Capture right up
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 + 1] != null && pieceAt(x1 + 1, y1 + 1).side() == 1) {
						  if (x1 == x2 - 2 && y1 == y2 - 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left up					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 + 1] != null && pieceAt(x1 - 1, y1 + 1).side() == 1) {
						  if (x1 == x2 + 2 && y1 == y2 - 2) {
							  return true;
						  }
					  }
				  }
			  }
				  } else if ( y1 - 1 >= 0 && y1 - 1 < 8 && y1 - 2 >= 0 && y1 - 2 < 8 && y1 == y2 + 2) {
//			  Capture right down
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 - 1] != null && pieceAt(x1 + 1, y1 - 1).side() == 1) {
						  if (x1 == x2 - 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left down					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 - 1] != null && pieceAt(x1 -1, y1 -1).side() == 1) {
						  if (x1 == x2 + 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
			  }

		  }
			  } else if (pieceAt(x1, y1).side() == 1) {
				  if (y1 + 1 >= 0 && y1 + 1 < 8 && y1 + 2 >= 0 && y1 + 2 < 8 && y1 == y2 - 2) {
//			  Capture right up
					  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
						  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
							  if (pieces[x1 + 1][y1 + 1] != null && pieceAt(x1 + 1, y1 + 1).side() == 0) {
								  if (x1 == x2 - 2 && y1 == y2 - 2) {
									  return true;
								  }
							  }
						  }
//			  Capture left up					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 + 1] != null && pieceAt(x1 - 1, y1 + 1).side() == 0) {
						  if (x1 == x2 + 2 && y1 == y2 - 2) {
							  return true;
						  }
					  }
				  }
			  }
		  } else if ( y1 - 1 >= 0 && y1 - 1 < 8 && y1 - 2 >= 0 && y1 - 2 < 8 && y1 == y2 + 2) {
//			  Capture right down
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 - 1] != null && pieceAt(x1 + 1, y1 - 1).side() == 0) {
						  if (x1 == x2 - 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left down					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 - 1] != null && pieceAt(x1 -1, y1 -1).side() == 0) {
						  if (x1 == x2 + 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
			  }
		  }
	  }

				  

	 
	//Regular Piece movement
		  } else {
	  //fire
	  if (side == 0) {

		  
//		  Capture
		if (y1 + 1 >= 0 && y1 + 1 < 8 && y1 + 2 >= 0 && y1 + 2 < 8) {
//			  Capture right up
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 + 1] != null && pieceAt(x1 + 1, y1 + 1).side() == 1) {
						  if (x1 == x2 - 2 && y1 == y2 - 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left up					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
			  		if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
			  			if (pieces[x1 - 1][y1 + 1] != null && pieceAt(x1 - 1, y1 + 1).side() == 1) {
			  				if (x1 == x2 + 2 && y1 == y2 - 2) {
			  					return true;
			  				}
			  			}
			  		}
			  	}
		  	}
//		water
	  } else if (side == 1) {
//		  Move left down

//		  Capture
		  if ( y1 - 1 >= 0 && y1 - 1 < 8 && y1 - 2 >= 0 && y1 - 2 < 8) {
//			  Capture right down
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 - 1] != null && pieceAt(x1 + 1, y1 - 1).side() == 0) {
						  if (x1 == x2 - 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left down					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 - 1] != null && pieceAt(x1 -1, y1 -1).side() == 0) {
						  if (x1 == x2 + 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
			  }
		  }
	  }
		  }
	  }
	  return false;
  }

  boolean validKingMovement(int x1, int y1, int x2, int y2) {
	  //King 4-Way Movement
//		  Move left up
	  if (x2 + 1 >= 0 && x2 + 1 < 8 && x1 == x2 + 1 && y1 == y2 - 1) {
		  if (x1 - 1 >= 0 && x1 -1 < 8 && y1 + 1 >= 0 && y1 +1 < 8) {
			  if (x1 == x2 + 1 && y1 == y2 - 1) {
				  return true;
			  }
		  }
//		  Move right up
	  } else if (x2 - 1 >= 0 && x2 - 1 < 8 && x1 == x2 - 1 && y1 == y2 - 1) {
		  if (x1 + 1 >= 0 && x1 + 1 < 8 && y1 + 1 >= 0 && y1 +1 < 8) {
			  if (x1 == x2 - 1 && y1 == y2 - 1) {
				  return true;
			  }
		  }
//		  Move left down
	  } else if (x2 + 1 >= 0 && x2 + 1 < 8 && x1 == x2 + 1 && y1 == y2 + 1) {
			  if (x1 - 1 >= 0 && x1 -1 < 8 && y1 - 1 >= 0 && y1 - 1 < 8) {
				  if (x1 == x2 + 1 && y1 == y2 + 1) {
					  return true;
				  }
			  }
//		  Move right down
	  } else if (x2 - 1 >= 0 && x2 - 1 < 8 && x1 == x2 - 1 && y1 == y2 + 1) {
		  if (x1 + 1 >= 0 && x1 + 1 < 8 && y1 - 1 >= 0 && y1 - 1 < 8) {
			  if (x1 == x2 - 1 && y1 == y2 + 1) {
				  return true;
			  }
		  }
	  } else if (pieceAt(x1, y1).side() == 0) {
		  if (y1 + 1 >= 0 && y1 + 1 < 8 && y1 + 2 >= 0 && y1 + 2 < 8 && y1 == y2 - 2) {
//			  Capture right up
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 + 1] != null && pieceAt(x1 + 1, y1 + 1).side() == 1) {
						  if (x1 == x2 - 2 && y1 == y2 - 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left up					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 + 1] != null && pieceAt(x1 - 1, y1 + 1).side() == 1) {
						  if (x1 == x2 + 2 && y1 == y2 - 2) {
							  return true;
						  }
					  }
				  }
			  }
		  } else if ( y1 - 1 >= 0 && y1 - 1 < 8 && y1 - 2 >= 0 && y1 - 2 < 8 && y1 == y2 + 2) {
//			  Capture right down
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 - 1] != null && pieceAt(x1 + 1, y1 - 1).side() == 1) {
						  if (x1 == x2 - 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left down					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 - 1] != null && pieceAt(x1 -1, y1 -1).side() == 1) {
						  if (x1 == x2 + 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
			  }

		  }
	  } else if (pieceAt(x1, y1).side() == 1) {
		  if (y1 + 1 >= 0 && y1 + 1 < 8 && y1 + 2 >= 0 && y1 + 2 < 8 && y1 == y2 - 2) {
//			  Capture right up
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 + 1] != null && pieceAt(x1 + 1, y1 + 1).side() == 0) {
						  if (x1 == x2 - 2 && y1 == y2 - 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left up					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 + 1] != null && pieceAt(x1 - 1, y1 + 1).side() == 0) {
						  if (x1 == x2 + 2 && y1 == y2 - 2) {
							  return true;
						  }
					  }
				  }
			  }
		  } else if ( y1 - 1 >= 0 && y1 - 1 < 8 && y1 - 2 >= 0 && y1 - 2 < 8 && y1 == y2 + 2) {
//			  Capture right down
			  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
				  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
					  if (pieces[x1 + 1][y1 - 1] != null && pieceAt(x1 + 1, y1 - 1).side() == 0) {
						  if (x1 == x2 - 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
//			  Capture left down					  
			  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
				  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  if (pieces[x1 - 1][y1 - 1] != null && pieceAt(x1 -1, y1 -1).side() == 0) {
						  if (x1 == x2 + 2 && y1 == y2 + 2) {
							  return true;
						  }
					  }
				  }
			  }
		  }
	  }
	  return false;
  }

  boolean validMove(int x1, int y1, int x2, int y2) {
//	  Multi-Capture (King/Reg)
	  if (pieceReadyForMulti) {
		  return multiCaptureValidMove(x1, y1, x2, y2);
	  //Regular Movement King/Normal
	  } else if (pieces[x1][y1] != null) {
		  //King Piece Movement/Capture
		  if (pieceAt(x1, y1).isKing()) {
			  return validKingMovement(x1, y1, x2, y2);
		//Regular Piece movement
		  } else {
			  //fire
			  if (side == 0) {
		//		  Move left up
				  if (x2 + 1 >= 0 && x2 + 1 < 8 && x1 == x2 + 1) {
					  if (x1 - 1 >= 0 && x1 -1 < 8 && y1 + 1 >= 0 && y1 +1 < 8) {
						  if (x1 == x2 + 1 && y1 == y2 - 1) {
							  return true;
						  }
					  }
		//		  Move right up
				  } else if (x2 - 1 >= 0 && x2 - 1 < 8 && x1 == x2 - 1) {
					  if (x1 + 1 >= 0 && x1 + 1 < 8 && y1 + 1 >= 0 && y1 +1 < 8) {
						  if (x1 == x2 - 1 && y1 == y2 - 1) {
							  return true;
						  }
					  }
				  
		//		  Capture
				  } else if (y1 + 1 >= 0 && y1 + 1 < 8 && y1 + 2 >= 0 && y1 + 2 < 8) {
		//			  Capture right up
					  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
						  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
							  if (pieces[x1 + 1][y1 + 1] != null && pieceAt(x1 + 1, y1 + 1).side() == 1) {
								  if (x1 == x2 - 2 && y1 == y2 - 2) {
									  return true;
								  }
							  }
						  }
		//			  Capture left up					  
					  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
					  		if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
					  			if (pieces[x1 - 1][y1 + 1] != null && pieceAt(x1 - 1, y1 + 1).side() == 1) {
					  				if (x1 == x2 + 2 && y1 == y2 - 2) {
					  					return true;
					  				}
					  			}
					  		}
					  	}
				  	}
		//		water
			  } else if (side == 1) {
		//		  Move left down
				  if (x1 - 1 >= 0 && x1 -1 < 8 && x1 == x2 + 1) {
					  if (x1 - 1 >= 0 && x1 -1 < 8 && y1 - 1 >= 0 && y1 - 1 < 8) {
						  if (x1 == x2 + 1 && y1 == y2 + 1) {
							  return true;
						  }
					  }
		//		  Move right down
				  } else if (x1 + 1 >= 0 && x1 + 1 < 8 && x1 == x2 - 1) {
					  if (x1 + 1 >= 0 && x1 + 1 < 8 && y1 - 1 >= 0 && y1 - 1 < 8) {
						  if (x1 == x2 - 1 && y1 == y2 + 1) {
							  return true;
						  }
					  }
		//		  Capture
				  } else if ( y1 - 1 >= 0 && y1 - 1 < 8 && y1 - 2 >= 0 && y1 - 2 < 8) {
		//			  Capture right down
					  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 == x2 - 2) {
						  if (x1 + 2 >= 0 && x1 + 2 < 8 && x1 + 1 >= 0 && x1 + 1 < 8) {
							  if (pieces[x1 + 1][y1 - 1] != null && pieceAt(x1 + 1, y1 - 1).side() == 0) {
								  if (x1 == x2 - 2 && y1 == y2 + 2) {
									  return true;
								  }
							  }
						  }
		//			  Capture left down					  
					  } else if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 == x2 + 2) {
						  if (x1 - 2 >= 0 && x1 - 2 < 8 && x1 - 1 >=0 && x1 -1 < 8) {
							  if (pieces[x1 - 1][y1 - 1] != null && pieceAt(x1 -1, y1 -1).side() == 0) {
								  if (x1 == x2 + 2 && y1 == y2 + 2) {
									  return true;
								  }
							  }
						  }
					  }
				  }
			  }
		  }
	  }
	  return false;
  }
		  
  public boolean canSelect(int x, int y) {
	  if (x >= 0 && y >= 0 && x < 8 && y < 8) {
		  if (hasMoved == false) {
			  if (pieces[x][y] != null) {
				  if (this.side == pieces[x][y].side()) {
					  return  true;
				  }
			  } else if (pieces[x][y] == null) {
				  if (hasSelected && validMove(selectedPieceX, selectedPieceY, x, y)) {
					  return true;
				  }
			  }
		  } else if (pieces[x][y] == null) {
			  if (hasSelected && validMove(selectedPieceX, selectedPieceY, x, y)) {
				  return true;
			  }
		  }
	  }
	  return false;
  }
  
  private int distance(int x1, int y1, int x2, int y2) {
	  return (int) Math.sqrt(Math.pow(x2 - x1 , 2) + Math.pow(y2 - y1, 2));
  }

  public void select(int x, int y) {
	  //Prepping a Piece for Movement or Capture
	  if (pieces[x][y] != null) {
			  selectedPieceX = x;
			  selectedPieceY = y;
			  selectedPiece = pieces[x][y];
			  hasSelected = true;
	  //Moving or Capturing
	  } else if (pieces[x][y] == null && hasSelected) {
			  move(selectedPiece, selectedPieceX, selectedPieceY, x, y);
	  }
	  
  }

  public void move(Piece p, int x1, int y1, int x2, int y2) {
	  //Capturing 
	  if (this.distance(x1, y1, x2, y2) == 2) { 
		  p.startCapturing();
		  if (p.isKinged) {
			  if (x1 == x2 -2 && y1 == y2-2) {
				  remove(x1 + 1, y1 + 1);
			  } else if (x1 == x2 + 2 && y1 == y2 -2) {
				  remove(x1 - 1, y1 + 1);			  
			  } else if (x1 == x2 -2 && y1 == y2+2) {
				  remove(x1 + 1, y1 - 1);
			  } else if (x1 == x2 + 2 && y1 == y2+2) {
				  remove(x1 - 1, y1 - 1);
			  }
		  } else {
			  if (this.side == 0) {
				  if (x1 == x2 -2 && y1 == y2-2) {
					  remove(x1 + 1, y1 + 1);
				  } else if (x1 == x2 + 2 && y1 == y2 -2) {
					  remove(x1 - 1, y1 + 1);			  
				  }
			  } else if (this.side == 1) {
				  if (x1 == x2 -2 && y1 == y2+2) {
					  remove(x1 + 1, y1 - 1);
				  } else if (x1 == x2 + 2 && y1 == y2+2) {
					  remove(x1 - 1, y1-1);
				  }
			  }
		  }
		  place(p, x2, y2);
		  selectedPieceX = x2;
		  selectedPieceY = y2;
		  pieces[x1][y1] = null;
		  hasMoved = true;
		  pieceReadyForMulti = true;
		  //Bomb Explosion
		  if (p.isBomb()) {
			  p.explode(x2, y2);
		  }
	//Regular Movement of Piece
	  } else if (this.distance(x1, y1, x2, y2) == 1) {
		  place(p, x2, y2);
		  pieces[x1][y1] = null;
		  hasMoved = true;
	  }
	  if (p.side() == 0 && y2 == 7) {
		  p.isKinged = true;
	  } else if (p.side() == 1 && y2 == 0) {
		  p.isKinged = true;
	  }
	  
}
 
  public boolean canEndTurn() {
	  if (selectedPiece != null) {
		  if (hasMoved || selectedPiece.hasCaptured()) {
			  return true;
			}
	  }
	  return false;
  }

  public void endTurn() {
	  if (canEndTurn()) {
		  this.side = 1 - this.side;
		  hasMoved = false;
		  hasSelected = false;
		  pieceReadyForMulti = false;
		  selectedPiece.finishCapturing();
		  selectedPiece = null;
	  }
  }

  public String winner() {
	  if (waterCount <= 0 && fireCount <= 0) {
		  gameContiunes = false;
		  return "Tie";
	  } else if (waterCount <= 0) {
		  gameContiunes = false;
		  return "Fire";
	  } else if (fireCount <= 0) {
		  gameContiunes = false;
		  return "Water";
	  } else {
		  return null;
	  }
  }

  public static void main(String[] args) {
	  Board b = new Board(false);
	  StdDrawPlus.setScale(0, 8);
	  b.drawBoard();
	  while (b.gameContiunes) {
		  b.winner();
		  b.drawBoard();
		  if (StdDrawPlus.mousePressed()) {
			  int x = (int) StdDrawPlus.mouseX();
	          int y = (int) StdDrawPlus.mouseY();
	          if (b.canSelect(x, y)) {
	        	  b.select(x, y);
	            }
	    	}
		   StdDrawPlus.show(10);
	    	if (StdDrawPlus.isSpacePressed()) {
	    		b.endTurn();
	    	}
	  }
  }
	    
  private void drawBoard() {
	    for (int i = 0; i < pieces.length; i++) {
	      for (int j = 0; j < pieces[0].length; j++) {
	    	  if ((i + j) % 2 == 0) {
	    		  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	    	  } else if ((i + j) % 2 == 1) {
	    		  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	    	  }
	    	  	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	    	  	if (selectedPiece != null && pieces[i][j] == selectedPiece) {
	  	  			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	  	  			StdDrawPlus.filledSquare(selectedPieceX + .5, selectedPieceY + .5, .5);
	  	  		}
	  	  		Piece p = pieceAt(i, j);
	  	  		if (p != null) {
	        	StdDrawPlus.picture(i + .5, j + .5, p.picture(), 1, 1);
	        }
	      }
	    }	
  }
}

  
  
  