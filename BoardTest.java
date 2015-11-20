import junit.framework.TestCase;


public class BoardTest extends TestCase {
	
	public void testPieceAt() {
		// testing empty space; no piece there 
		Board b1 = new Board(true);
		Piece expected1 = null;
		Piece actual1 = b1.pieceAt(0, 0);
		assertEquals(expected1, actual1);
		
		// testing out of bounds 
		Board b2 = new Board(false);
		Piece expected2 = null;
		Piece actual2 = b2.pieceAt(4, 8);
		assertEquals(expected2, actual2);
		
		// testing when there is a piece there 
		Board b3 = new Board(true); 
		Piece p1 = new Piece(0, b3);
		b3.place(p1, 2, 0);
		Piece expected3 = p1;
		Piece actual3 = b3.pieceAt(2, 0);
		assertEquals(expected3, actual3); 
		
		// another test when there is a piece there 
		Board b4 = new Board(true); 
		Piece p2 = new BombPiece(1, b4);
		b4.place(p2, 5, 5);
		Piece expected4 = p2;
		Piece actual4 = b4.pieceAt(5, 5);
		assertEquals(expected4, actual4);
	}

	public void testPlace() {
		// testing out of bounds 
		Board b1 = new Board(true);
		Piece p1 = new Piece(1, b1);
		b1.place(p1, 10, 5);
		Piece expected1 = null;
		Piece actual1 = b1.pieceAt(10, 5);
		assertEquals(expected1, actual1);
		
		// testing when p is null 
		Board b2 = new Board(false);
		Piece p2 = null;
		b2.place(p2, 2, 3);
		Piece expected2 = null;
		Piece actual2 = b2.pieceAt(2, 3);
		assertEquals(expected2, actual2);
		
		// testing if piece is actually placed at (X, Y) 
		Board b3 = new Board(true); 
		Piece p3 = new Piece(0, b3);
		b3.place(p3, 0, 0); 
		Piece expected3 = p3;
		Piece actual3 = b3.pieceAt(0, 0);
		assertEquals(expected3, actual3);
	}	
	
	public void testCanSelect() {
		// testing can select capture position diagonally down right
		Board b1 = new Board(true);
		b1.side = 1;
		Piece p1 = new Piece(1, b1);
		b1.place(p1, 2, 2);
		Piece p2 = new Piece(0, b1);
		b1.place(p2, 3, 1);
		b1.select(2, 2);
		assertEquals(true, b1.canSelect(4, 0));
		
		// testing can select capture position diagonally up right
		Board b2 = new Board(true);
		Piece p3 = new Piece(0, b2);
		b2.place(p3, 4, 1);
		Piece p4 = new Piece(1, b2);
		b2.place(p4, 5, 2);
		b2.select(4, 1);
		assertEquals(true, b2.canSelect(6, 3));
		
		
		// testing can select capture position diagonally down left
		Board b3 = new Board(true);
		b3.side = 1;
		Piece p5 = new Piece(1, b3);
		b3.place(p5, 2, 2);
		Piece p6 = new Piece(0, b3);
		b3.place(p6, 1, 1);
		b3.select(2, 2);
		assertEquals(true, b3.canSelect(0, 0));
		
		// testing can select capture position diagonally up left
		Board b4 = new Board(true);
		Piece p7 = new Piece(0, b4);
		b4.place(p7, 3, 5);
		Piece p8 = new Piece(1, b4);
		b4.place(p8, 2, 6);
		b4.select(3, 5);
		assertEquals(true, b4.canSelect(1, 7));
		
		// testing can select capture position then non-capture movement; should be false
		Board b5 = new Board(true);
		Piece p9 = new Piece(0, b5);
		b5.place(p9, 3, 0);
		Piece p10 = new Piece(1, b5);
		b5.place(p10, 4, 1);
		b5.select(3, 0);
		assertEquals(true, b5.canSelect(5, 2));
		b5.select(5, 2);
		assertEquals(false, b5.canSelect(6, 3));
	}
	
	public void testValidMove() {
		// moving one space no capturing
		
		// testing valid move for fire piece, not a king, moving up without capturing
		Board b1 = new Board(true);
		Piece p1 = new Piece(0, b1);
		b1.place(p1, 1, 0);
		assertEquals(true, b1.validMove(1, 0, 2, 1));
		assertEquals(true, b1.validMove(1, 0, 0, 1));
		assertEquals(false, b1.validMove(1,  0,  3,  4));
		
		// testing valid move for fire piece, is a king, moving up and down without capturing
		Piece p2 = new Piece(0, b1);
		p2.isKinged = true;
		b1.place(p2, 2, 2);
		assertEquals(true, b1.validMove(2, 2, 3, 3));
		assertEquals(true, b1.validMove(2, 2, 1, 3));
		assertEquals(true, b1.validMove(2, 2, 1, 1));
		assertEquals(true, b1.validMove(2,  2,  3,  1));
		assertEquals(false, b1.validMove(2, 2, 1, 4));
		
		// testing valid move for water piece, not a king, moving down without capturing
		Piece p3 = new Piece(1, b1);
		b1.side = 1;
		b1.place(p3, 6, 7);
		assertEquals(true, b1.validMove(6,  7,  5,  6));
		assertEquals(true, b1.validMove(6, 7, 7, 6));
		assertEquals(false, b1.validMove(6,  7,  5,  5));
		
		// testing valid move for water piece, is a king, moving up and down without capturing
		Piece p4 = new Piece(1, b1);
		p4.isKinged = true;
		b1.place(p4, 4, 4);
		assertEquals(true, b1.validMove(4, 4, 5, 5));
		assertEquals(true, b1.validMove(4, 4, 3, 5));
		assertEquals(true, b1.validMove(4,  4,  3,  3));
		assertEquals(true, b1.validMove(4, 4, 5, 3));
		assertEquals(false, b1.validMove(4, 4, 3, 2));
		
		// move one space yes capturing
		
		// testing valid move for fire piece, not a king, moving up left capturing
		b1.side = 0;
		Piece p5 = new Piece(0, b1);
		b1.place(p5, 7, 4);
		Piece p6 = new Piece(1, b1);
		b1.place(p6, 6, 5);
		assertEquals(true, b1.validMove(7, 4, 5, 6));
		
		// testing valid move for fire piece, not a king, moving up right capturing
		Piece p7 = new Piece(0, b1);
		b1.place(p7, 5, 4);
		assertEquals(true, b1.validMove(5,  4,  7,  6));
		
		// testing valid move for water piece, not a king, moving down left capturing
		Piece p8 = new Piece(1, b1);
		b1.side = 1;
		b1.place(p8, 2, 7);
		Piece p9 = new Piece(0, b1);
		b1.place(p9, 1, 6);
		assertEquals(true, b1.validMove(2,  7,  0,  5));
		
		// testing valid move for water piece, not a king, moving down right capturing
		Piece p10 = new Piece(0, b1);
		b1.place(p10, 3, 6);
		assertEquals(true, b1.validMove(2, 7, 4, 5));
						
		//(Doesn't Work) testing valid move for fire piece, is a king, moving up down left capturing
		Board b2 = new Board(true);
		b2.side = 0;
		Piece p11 = new Piece(0, b2);
		p11.isKinged = true;
		b2.place(p11, 2, 2);
		Piece p12 = new Piece(1, b2);
		b2.place(p12, 1, 3);
		assertEquals(true, b2.validMove(2,  2,  0,  4));
		Piece p13 = new Piece(1, b2);
		b2.place(p13,  1,  1);
		b2.place(p11, 2, 2);
		assertEquals(true, b2.validMove(2,  2,  0,  0));
		
		// testing valid move for fire piece, is a king, moving up down right capturing
		Piece p14 = new Piece(1, b2);
		b2.place(p14, 3, 3);
		assertEquals(true, b2.validMove(2,  2,  4,  4));
		Piece p15 = new Piece(1, b2);
		b2.place(p15, 3, 1);
		b2.place(p11, 2, 2);
		assertEquals(true, b2.validMove(2, 2, 4, 0));
						
		// testing valid move for water piece, is a king, moving up down left capturing
		Piece p16 = new Piece(1, b2);
		b2.side = 1;
		p16.isKinged = true;
		b2.place(p16, 5, 2);
		Piece p17 = new Piece(0, b2);
		b2.place(p17,  4,  3);
		assertEquals(true, b2.validMove(5,  2,  3,  4));
		Piece p18 = new Piece(0, b2);
		b2.place(p18, 4, 1);
		b2.place(p16, 5, 2);
		assertEquals(true, b2.validMove(5,  2,  3,  0));
		
		// testing valid move for water piece, is a king, moving up down right capturing
		Piece p19 = new Piece(0, b2);
		b2.place(p19, 6, 3);
		assertEquals(true, b2.validMove(5,  2,  7,  4));
		Piece p20 = new Piece(0, b2);
		b2.place(p20, 6, 1);
		b2.place(p16, 5, 2);
		assertEquals(true, b2.validMove(5,  2,  7,  0));
	}
	
	public void testCanEndTurn() {
		// moved but did not capture
		Board b1 = new Board(true);
		b1.side = 1;
		Piece p1 = new Piece(1, b1);
		b1.place(p1, 6, 7);
		assertEquals(false, b1.canEndTurn());
		b1.select(6, 7);
		b1.move(p1, 6, 7, 5, 6);
		assertEquals(true, b1.canEndTurn());
		
		// moved and captured
		Board b2 = new Board(true);
		Piece p2 = new Piece(0, b2);
		b2.place(p2, 1, 0);
		Piece p3 = new Piece(1, b2);
		b2.place(p3, 2, 1);
		assertEquals(false, b2.canEndTurn());
		b2.select(1, 0);
		b2.move(p2, 1, 0, 3, 2);
		assertEquals(true, b2.canEndTurn());
	}
	
	public void testWinner() {
		// when there are only water pieces left
		Board b1 = new Board(true);
		Piece p1 = new Piece(1, b1);
		b1.place(p1, 3, 4);
		Piece p2 = new ShieldPiece(1, b1);
		b1.place(p2, 5, 4);
		Piece p3 = new BombPiece(0, b1);
		b1.place(p3, 2, 1);
		Piece p4 = new Piece(1, b1);
		b1.place(p4, 3, 2);
		b1.select(2, 1);
		b1.move(p3, 2, 1, 4, 3);
		assertEquals(null, b1.pieceAt(3, 2));
		assertEquals(null, b1.pieceAt(3, 4));
		assertEquals("Water", b1.winner());
		
		// when there are only fire pieces left
		Board b2 = new Board(true);
		Piece p5 = new Piece(0, b2);
		b2.place(p5, 0, 5);
		Piece p6 = new Piece(0, b2);
		b2.place(p6, 6, 0);
		assertEquals("Fire", b2.winner());
		
		// when there are no pieces left on the board
		Board b3 = new Board(true);
		assertEquals("Tie", b3.winner());
		Piece p7 = new BombPiece(0, b3);
		b3.place(p7, 3, 3);
		Piece p8 = new ShieldPiece(1, b3);
		b3.place(p8, 2, 4);
		b3.select(3, 3);
		assertEquals(null, b3.winner());
		b3.select(1, 5);
		assertEquals("Tie", b3.winner());
		
		// when there are still pieces left on the board
		Board b4 = new Board(false);
		assertEquals(null, b4.winner());
		
		Board b5 = new Board(true);
		Piece p9 = new Piece(0, b5);
		b5.place(p9, 0, 0);
		Piece p10 = new Piece(1, b5);
		b5.place(p10, 7, 7);
		assertEquals(null, b5.winner());
	}
	
	public void testRemove() {
		Board b1 = new Board(false);
		b1.remove(3, 5);
		assertEquals(null, b1.pieceAt(3, 5));
	}

}
