package ca.sevenless.pixelcrops.util;

public class BoxCoord {
	
	private Coord TL;
	private Coord BR;
	/**
	 * creates box coord based on top left and bottom right 2d coordinates
	 * @param _TL
	 * @param _BR
	 */
	public BoxCoord( Coord _TL, Coord _BR){
		setTL(_TL);
		setBR(_BR); 
	}
	/**
	 * creates box coord based on 2 coordinates split into their x and y components 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public BoxCoord(int x1, int y1, int x2, int y2){
		setTL(new Coord(x1,y1));
		setBR(new Coord(x2,y2));
	}
	/**
	 * gets top left coordinates
	 * @return
	 */
	public Coord getTL() {
		return TL;
	}
	/**
	 * sets top left coordinate
	 * @param tL
	 */
	public void setTL(Coord tL) {
		TL = tL;
	}
	/**
	 * get bottom right coordinate
	 * @return
	 */
	public Coord getBR() {
		return BR;
	}
	/**
	 * set bottom right coordinate
	 * @param bR
	 */
	public void setBR(Coord bR) {
		BR = bR;
	}
}
