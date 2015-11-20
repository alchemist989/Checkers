import junit.framework.TestCase;

public class PieceTest extends TestCase {
	public void testSide() {
		// test fire piece side
		Board b1 = new Board(true);
		Piece p1 = new Piece(0, b1);
		b1.place(p1, 2, 2);
		assertEquals(0, p1.side());
		
		// test water piece side
		Piece p2 = new Piece(1, b1);
		b1.place(p2, 5, 5);
		assertEquals(1, p2.side());
	}
	
	public void testIsKing() {
		// fire piece becomes king
		Board b1 = new Board(true);
		Piece p1 = new Piece(0, b1);
		b1.place(p1, 6, 6);
		assertEquals(false, p1.isKing());
		b1.move(p1, 6, 6, 7, 7);
		assertEquals(true, p1.isKing());
		
		// water piece becomes king
		Piece p2 = new Piece(1, b1);
		b1.place(p2, 1, 1);
		assertEquals(false, p2.isKing());
		b1.move(p2, 1, 1, 2, 0);
		assertEquals(true, p2.isKing());
	}
	
	public void testStartCapturing() {
		// test start capturing for fire piece
		Board b1 = new Board(true);
		Piece p1 = new Piece(0, b1);
		b1.place(p1, 2, 2);
		Piece p2 = new Piece(1, b1);
		b1.place(p2, 3, 3);
		assertEquals(false, p1.hasCaptured());
		b1.move(p1, 2, 2, 4, 4);
		assertEquals(true, p1.hasCaptured());
		
		// test start capturing for water piece
		Piece p3 = new Piece(1, b1);
		b1.place(p3, 7, 7);
		Piece p4 = new Piece(0, b1);
		b1.place(p4, 6, 6);
		assertEquals(false, p3.hasCaptured());
		b1.move(p3, 7, 7, 5, 5);
		assertEquals(true, p3.hasCaptured());
	}
	
	public void testFinishCapturing() {
		// test finish capturing for fire piece
		Board b1 = new Board(true);
		Piece p1 = new Piece(0, b1);
		b1.place(p1, 7, 4);
		Piece p2 = new Piece(1, b1);
		b1.place(p2, 6, 5);
		assertEquals(false, p1.hasCaptured());
		b1.select(7, 4);
		b1.select(5, 6);
		assertEquals(true, p1.hasCaptured());
		b1.endTurn();
		assertEquals(false, p1.hasCaptured());
		
		// test finish capturing for water piece
		Board b2 = new Board(true);
		Piece p3 = new Piece(1, b2);
		b2.place(p3, 5, 6);
		Piece p4 = new Piece(0, b2);
		b2.place(p4, 4, 5);
		assertEquals(false, p3.hasCaptured());
		b2.select(5, 6);
		b2.select(3, 4);
		assertEquals(true, p3.hasCaptured());
		b2.endTurn();
		assertEquals(false, p3.hasCaptured());
	}
}

