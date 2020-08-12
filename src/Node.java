
import java.util.*;



/**
 * 
 * @author shai kaikov.
 * 
 * here the description of the class.
 * the purpose of the class is to create state as Node.
 * the Node is the border with the numbers on the tile-puzzle and with colors(on every tile)-
 * green,red and black. the variables:
 * st will be the key in the hashtable that will be on the algorithms.
 * sq represent matrix of square objects.parent-the parent of every state.
 * childs represent vector of childs that belong to the state.counter represent the number of the tiles.
 * g-the represent the distance from the start node.h represent the distance to the goal state.
 * f represent the binary operation of + between h and g.ar_for_hur-his purpose belong to the
 * Heuristic function(I will explain in the future),but this is matrix with the combination
 * of i and j.out_for_IDA represent as out for algorithms IDA*
 * and DFBnB.black is to know how much black tiles I have.moves represent the moves of the state from
 * the start node(11L-9R... and more).Who_Was_first represent who is precede in the iterations(need for
 * the algorithms).temp_Who_Was_first general the order of the nodes that have been generates until
 * now.
 * 
 *
 */


public class Node {

	private String st;
	private square sq[][];
	private Node parent;
	private Vector<Node> childs=new Vector<Node>();
	private int counter;
	private int g;
	private int h;
	private int f;
	private int ar_for_hur[][];
	private boolean out_for_IDA=false;
	private int black;
	private String moves;
	private int Who_Was_first;
	private static int temp_Who_Was_first; 



	/**
	 * constructor for the start node-take the board and insert this
	 * to the Node object.in addition update the h(after activate the heuristic function) and f(g is zero).
	 * Who_Was_first=1-the node is the first.ar_for_hur is for store
	 * combination of i and j in every tile on the matrix(need this for the
	 * Heuristic)-counter-the number of the tiles(rows) and the columns are
	 * [i,j].I calculate black to(number of the black tiles in the board).
	 * 
	 * @param s as the matrix of the square objects
	 */
	public Node(square s[][]) {

		this.Who_Was_first=1;
		temp_Who_Was_first=1; 
		this.moves="";
		this.black=0;
		this.ar_for_hur=new int[(s.length)*(s[0].length)][2];
		this.g=0;
		this.st="";
		int count=0;
		sq=new square[s.length][s[0].length];
		for(int i=0;i<s.length;i++) {
			for(int j=0;j<s[0].length;j++) {
				this.ar_for_hur[count][0]=i;
				this.ar_for_hur[count][1]=j;

				count++;
				sq[i][j]=new square(s[i][j]);
				st=st+"," + String.valueOf(s[i][j].getSq());
				if(sq[i][j].getColor()==3) {
					this.black++;
				}

			}
		}



		this.counter=0;
		this.huristic();
		this.f=this.g+this.h;

	}





	/**
	 * here the constructor for build the nodes.the previous 
	 * constructor-his purpose was to the build the root node
	 * here is the for the rest of the nodes.
	 * the functions of the movements are activate this constructor(see below like-
	 * the Move_down function)here is a lot of logic operations so I will explain
	 * what is need.here I built  conditions according to every movement direction 
	 * and from that I build the states the opi and opj variables help me to choose according the 
	 * specific condition to what kind of state create(according the direction movement).
	 * here I build the key from string to the hashtable(the key represent the order of the numbers
	 * on the board).in the end I activate the heuristic function and update the state-functions-f,h and g
	 * (g represent the cost till him from the node root and the h represent the cost of the way to
	 * the goal state).there are more variables but they are less important to explain.
	 * @param s-is the parent node
	 * @param op_i-the index where the blank tile is in there(row).
	 * @param op_j-the index where the blank tile is in there(column).
	 * @param opi-the index of the tile that need to be move and replace the blank tile(row).
	 * @param opj-the index of the tile that need to be move and replace the blank tile(column).
	 */
	public Node(Node s,int op_i,int op_j,int opi,int opj) {
		temp_Who_Was_first++;
		this.Who_Was_first=temp_Who_Was_first;
		this.black=s.getBlack();
		this.ar_for_hur=s.getAr_for_hur();
		this.st="";
		this.sq=new square[s.sq.length][s.sq[0].length];
		if(opj==op_j-1 && op_i==opi) {
			if(s.getSq()[opi][opj].getColor()==2) {
				this.g=s.getG()+30;
			}
			else if(s.getSq()[opi][opj].getColor()==1) {
				this.g=s.getG()+1;
			}
			for(int i=0;i<this.sq.length;i++) {
				for(int j=0;j<this.sq[0].length;j++) {
					if(j==opj && i==opi) {


						this.sq[i][j]=new square(s.sq[i][j+1]);
						st=st+"," + String.valueOf(s.sq[i][j+1].getSq());
						this.sq[i][j+1]=new square(s.sq[i][j]);
						st=st+"," + String.valueOf(s.sq[i][j].getSq());
						j=j+1;

					}
					else {
						this.sq[i][j]=new square(s.sq[i][j]);
						st=st+"," + String.valueOf(s.sq[i][j].getSq());
					}
				}
			}
			this.huristic();
			this.f=this.g+this.h;
		}

		else if(opj==op_j+1 && opi==op_i) {
			if(s.getSq()[opi][opj].getColor()==2) {
				this.g=s.getG()+30;
			}
			else if(s.getSq()[opi][opj].getColor()==1) {
				this.g=s.getG()+1;
			}
			for(int i=0;i<this.sq.length;i++) {
				for(int j=0;j<this.sq[0].length;j++) {
					if(j+1==opj && i==opi) {
						this.sq[i][j]=new square(s.sq[i][j+1]);
						st=st+"," + String.valueOf(s.sq[i][j+1].getSq());
						this.sq[i][j+1]=new square(s.sq[i][j]);
						st=st+"," + String.valueOf(s.sq[i][j].getSq());
						j=j+1;
					}
					else {
						this.sq[i][j]=new square(s.sq[i][j]);
						st=st+"," + String.valueOf(s.sq[i][j].getSq());
					}
				}
			}
			this.huristic();
			this.f=this.g+this.h;
		}

		else if(opj==op_j && opi==op_i-1) {
			if(s.getSq()[opi][opj].getColor()==2) {
				this.g=s.getG()+30;
			}
			else if(s.getSq()[opi][opj].getColor()==1) {
				this.g=s.getG()+1;
			}
			for(int i=0;i<this.sq.length;i++) {
				for(int j=0;j<this.sq[0].length;j++) {
					if(j==opj && i==opi) {
						this.sq[i][j]=new square(s.sq[i+1][j]);
						st=st+"," + String.valueOf(s.sq[i+1][j].getSq());

					}
					else if(j==op_j && i==op_i) {
						this.sq[i][j]=new square(s.sq[i-1][j]);
						st=st+"," + String.valueOf(s.sq[i-1][j].getSq());
					}
					else {
						this.sq[i][j]=new square(s.sq[i][j]);
						st=st+"," + String.valueOf(s.sq[i][j].getSq());
					}
				}
			}
			this.huristic();
			this.f=this.g+this.h;
		}

		else if(opj==op_j && opi==op_i+1) {
			if(s.getSq()[opi][opj].getColor()==2) {
				this.g=s.getG()+30;
			}
			else if(s.getSq()[opi][opj].getColor()==1) {
				this.g=s.getG()+1;
			}
			for(int i=0;i<this.sq.length;i++) {
				for(int j=0;j<this.sq[0].length;j++) {
					if(j==opj && i==opi) {
						this.sq[i][j]=new square(s.sq[i-1][j]);
						st=st+"," + String.valueOf(s.sq[i-1][j].getSq());

					}
					else if(j==op_j && i==op_i) {
						this.sq[i][j]=new square(s.sq[i+1][j]);
						st=st+"," + String.valueOf(s.sq[i+1][j].getSq());
					}
					else {
						this.sq[i][j]=new square(s.sq[i][j]);
						st=st+"," + String.valueOf(s.sq[i][j].getSq());
					}
				}
			}
			this.huristic();
			this.f=this.g+this.h;
		}



		this.counter=0;
	}


	/**
	 * 
	 * @return Who_Was_first for iteration.
	 */

	public int getWho_Was_first() {
		return Who_Was_first;
	}

	/**
	 * 
	 * @param who_Was_first to set iteration.
	 */
	public void setWho_Was_first(int who_Was_first) {
		Who_Was_first = who_Was_first;
	}

	/**
	 * 
	 * @return moves to know the string path
	 */
	public String getMoves() {
		return moves;
	}

	/**
	 * 
	 * @param moves to set moves string
	 */
	public void setMoves(String moves) {
		this.moves = moves;
	}

	/**
	 * 
	 * @return black- the number of the black tiles.
	 */
	public int getBlack() {
		return black;
	}

	/**
	 * 
	 * @param black-to set the number of the black tiles
	 */
	public void setBlack(int black) {
		this.black = black;
	}

	/**
	 * 
	 * @return ar_for_hur- the matrix that store the combination of i and j
	 * in the board.
	 */
	public int[][] getAr_for_hur() {
		return ar_for_hur;
	}

	/**
	 * 
	 * @param ar_for_hur-set the matrix that store the combination of i and j in the board.
	 */
	public void setAr_for_hur(int[][] ar_for_hur) {
		this.ar_for_hur = ar_for_hur;
	}

	/**
	 * 
	 * @return out_for_IDA-represent "out" of the node
	 */
	public boolean isOut_for_IDA() {
		return out_for_IDA;
	}

	/**
	 * 
	 * @param out_for_IDA to set
	 */

	public void setOut_for_IDA(boolean out_for_IDA) {
		this.out_for_IDA = out_for_IDA;
	}

	/**
	 * 
	 * @return f(g+h)
	 */
	public int getF() {
		return f;
	}

	/**
	 * 
	 * @param f to set
	 */
	public void setF(int f) {
		this.f = f;
	}

	/**
	 * 
	 * @return g 
	 */
	public int getG() {
		return g;
	}

	/**
	 * 
	 * @param g to set
	 */
	public void setG(int g) {
		this.g = g;
	}

	/**
	 * 
	 * @return h-the way to the goal state
	 */
	public int getH() {
		return h;
	}

	/**
	 * 
	 * @param h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * 
	 * @return st-string of the board(key)-order of the numbers on the board.
	 */
	public String getSt() {
		return st;
	}

	/**
	 * 
	 * @param st to set
	 */
	public void setSt(String st) {
		this.st = st;
	}

	/**
	 * 
	 * @return counter- number of the tiles in the board.
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * 
	 * @param counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * 
	 * @return sq- matrix of the board
	 */
	public square[][] getSq() {
		return sq;
	}

	/**
	 * 
	 * @param sq to set
	 */
	public void setSq(square[][] sq) {
		this.sq = sq;
	}

	/**
	 * 	
	 * @return parent of the node
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * 
	 * @param parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * 
	 * @return childs-the child of the state.
	 */
	public Vector<Node> getChilds() {
		return this.childs;
	}

	/**
	 * 
	 * @param childs to set
	 */
	public void setChilds(Vector<Node> childs) {
		this.childs=childs;
	}

	/**
	 * here I move the tile -left from the empty tile right.
	 * first I check if the tile in the board and if not black and check if his father can't be the same
	 * of him.than build him.
	 * @param i is the i position of the empty tile.
	 * @param j is the i position of the empty tile.the combination-[i,j].
	 */

	public void Move_right(int i,int j) {

		if(j<this.sq[0].length && 0<j) {
			if(this.sq[i][j-1].getColor()!=3) {
				if(this.getParent()==null || this.sq[i][j].getSq()!=this.getParent().sq[i][j-1].getSq()) {



					String temp=this.getMoves()+"-"+Integer.toString(this.getSq()[i][j-1].getSq())+"R";
					Node nd=new Node(this,i,j,i,j-1);
					nd.setMoves(temp);
					nd.parent=this;
					this.childs.add(nd);

					return;
				}
			}
		}

	}

	/**
	 * here I move the tile -right from the empty tile left.
	 * first I check if the tile in the board and if not black and check if his father can't be the same
	 * of him.than build him.
	 * @param i is the i position of the empty tile.
	 * @param j is the i position of the empty tile.the combination-[i,j].
	 */	

	public void Move_Left(int i,int j) {

		if(j<this.sq[0].length-1 && 0<=j) {
			if(this.sq[i][j+1].getColor()!=3) {
				if(this.getParent()==null || this.sq[i][j].getSq()!=this.getParent().sq[i][j+1].getSq()) {


					String temp=this.getMoves()+"-"+Integer.toString(this.getSq()[i][j+1].getSq())+"L";
					Node nd=new Node(this,i,j,i,j+1);
					nd.setMoves(temp);
					nd.parent=this;
					this.childs.add(nd);

					return;
				}
			}
		}

	}


	/**
	 * here I move the tile -up from the empty tile down.
	 * first I check if the tile in the board and if not black and check if his father can't be the same
	 * of him.than build him.
	 * @param i is the i position of the empty tile.
	 * @param j is the i position of the empty tile.the combination-[i,j].
	 */		
	public void Move_down(int i,int j) {

		if(i<this.sq.length && 0<i) {
			if(this.sq[i-1][j].getColor()!=3) {
				if(this.getParent()==null || this.sq[i][j].getSq()!=this.getParent().sq[i-1][j].getSq()) {

					String temp=this.getMoves()+"-"+Integer.toString(this.getSq()[i-1][j].getSq())+"D";
					Node nd=new Node(this,i,j,i-1,j);
					nd.setMoves(temp);
					nd.parent=this;
					this.childs.add(nd);

					return;
				}
			}
		}

	}

	/**
	 * here I move the tile -down from the empty tile up.
	 * first I check if the tile in the board and if not black and check if his father can't be the same
	 * of him.than build him.
	 * @param i is the i position of the empty tile.
	 * @param j is the i position of the empty tile.the combination-[i,j].
	 */		    
	public void Move_up(int i,int j) {

		if(i<this.sq.length-1 && 0<=i) {
			if(this.sq[i+1][j].getColor()!=3) {
				if(this.getParent()==null || this.sq[i][j].getSq()!=this.getParent().sq[i+1][j].getSq()) {

					String temp=this.getMoves()+"-"+Integer.toString(this.getSq()[i+1][j].getSq())+"U";
					Node nd=new Node(this,i,j,i+1,j);
					nd.setMoves(temp);
					nd.parent=this;
					this.childs.add(nd);

					return;
				}
			}
		}

	}

	/**
	 * here I expansion the state node on the base of the directions.
	 *    
	 */

	public void expantion() {

		for(int i=0;i<this.sq.length;i++) {
			for(int j=0;j<this.sq[0].length;j++) {
				if(this.sq[i][j].getColor()==0) {

					this.Move_Left(i, j);
					this.Move_up(i, j);
					this.Move_right(i, j);
					this.Move_down(i, j);
					return;
				}
			}
		}
	}


	/**
	 * here my heuristic function-I used the mantahan-distance to build this.
	 * its very simple-I go in every tile in the state and I check
	 * the distance between him to where is need to be(his number) by i and
	 * j indexes-if the tile is red so do i*30 and j*30 than add them(in every movement) to the
	 * h-the evaluation cost to the goal state,if this green so add i*1 and j*1.
	 *
	 *  
	 */


	public void huristic() {
		this.h=0;
		for(int i=0;i<this.sq.length;i++) {
			for(int j=0;j<this.sq[0].length;j++) {


				if(this.sq[i][j].getColor()==2) {


					this.h=this.h +(Math.abs(this.ar_for_hur[this.sq[i][j].getSq()-1][0]-i)*30);
					this.h=this.h +(Math.abs(this.ar_for_hur[this.sq[i][j].getSq()-1][1]-j)*30);
				}
				else if(this.sq[i][j].getColor()==3) {
					continue ;
				}
				else if(this.sq[i][j].getColor()==1) {
					this.h=this.h +Math.abs(this.ar_for_hur[this.sq[i][j].getSq()-1][0]-i);
					this.h=this.h +Math.abs(this.ar_for_hur[this.sq[i][j].getSq()-1][1]-j);
				}
				else if(this.sq[i][j].getColor()==0) {
					continue ;
				}

			}
		}

	}








}




