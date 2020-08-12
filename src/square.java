
/**
 * 
 * @author shai kaikov
 * 
 * here the square class.this class represent tile. the sq variable represent the number
 * in the tile and the color represent the color of the tile.
 *
 */
public class square {

	private int sq;
	private int color;

	/**
	 * constructor for the tile
	 * @param s as number in the tile
	 * @param col-color of the tile.
	 */
	public square(int s,int col) {
		this.sq=s;
		this.color=col;

	}
	
	
	/**
	 * copy constructor for the tile.
	 * @param s-tile object.
	 */
	public square(square s) {
		this.sq=s.sq;
		this.color=s.color;
	}

	/**
	 * 
	 * @return sq-the number in the tile.
	 */
	public int getSq() {
		return sq;
	}

	/**
	 * 
	 * @param sq-to set
	 */
	public void setSq(int sq) { 
		this.sq = sq;
	}
	/**
	 * 
	 * @return color-the color of the tile
	 */
	public int getColor() {
		return color;
	}

	/**
	 * 
	 * @param color to set.
	 */
	public void setColor(int color) {
		this.color = color;
	}





}
