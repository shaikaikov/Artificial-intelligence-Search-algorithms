
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 
 * 
 * @author shai kaikov.
 * here the algorithms. I put variables-cutoff and fail in kind of link-list
 * to use them in the DFID-in order to check if the result their equal to them
 * -if I have cutoff or fail-see the algorithm.the Cost is the final cost to get to
 * the goal from the start node and Num is the number of the nodes that I created.
 * print_open_list is flag to check if I need to print the open list if I have been asked.
 *
 */
public class allalgo {

	private LinkedList<Node> cutoff=new LinkedList<Node>();
	private LinkedList<Node> fail=new LinkedList<Node>();
	private int Cost;
	private int Num;
	private boolean print_open_list=false;



	/**
	 * 
	 * @return cutoff(address).
	 */

	public LinkedList<Node> getCutoff() {
		return cutoff;
	}


	/**
	 * 
	 * @param cutoff-set to new address.
	 */
	public void setCutoff(LinkedList<Node> cutoff) {
		this.cutoff = cutoff;
	}


	/**
	 * 
	 * @return fail(address).
	 */
	public LinkedList<Node> getFail() {
		return fail;
	}


	/**
	 * 
	 * @param fail-set to new address.
	 */
	public void setFail(LinkedList<Node> fail) {
		this.fail = fail;
	}


	/**
	 * 
	 * @return print_open_list
	 */
	public boolean isPrint_open_list() {
		return print_open_list;
	}


	/**
	 * 
	 * @param print_open_list-set to new boolean.
	 */
	public void setPrint_open_list(boolean print_open_list) {
		this.print_open_list = print_open_list;
	}

	/**
	 * 
	 * @return cost
	 */

	public int getCost() {
		return Cost;
	}


	/**
	 * 
	 * @param cost-set to cost
	 */
	public void setCost(int cost) {
		Cost = cost;
	}


	/**
	 * 
	 * @return Num
	 */
	public int getNum() {
		return Num;
	}


	/**
	 * 
	 * @param num-set to Num
	 */
	public void setNum(int num) {
		Num = num;
	}


	/**
	 * here I return node(child) from the list of the children
	 * after I activate operator in the algorithm.note that if I already use one child
	 * I go to the next child in the list.
	 * @param node(parent)
	 * @return node(child)
	 */
	public static Node operator(Node node) {

		if(node.getCounter()<node.getChilds().size()) {
			node.setCounter(node.getCounter()+1);
			return node.getChilds().get(node.getCounter()-1);

		}

		return null;
	}


	/**
	 * here I check if the node is the goal node.
	 * @param g-node
	 * @return boolean-if this is goal state or not.
	 */
	public static boolean goal(Node g) {

		int num=1;
		for(int i=0;i<g.getSq().length;i++) {
			for(int j=0;j<g.getSq()[0].length;j++) {
				if(num==g.getSq()[i][j].getSq()) {
					num++;
				}
				else if(i==g.getSq().length-1 && j==g.getSq()[0].length-1) {
					return true;
				}
				else {
					return false;

				}

			}
		}

		return false;


	}

	/**
	 * here I sort the vector of the children by my comparator.
	 * this is for the priority of the children for the DFBnB algorithm.
	 * @param v-the vector of the children.
	 */

	public static void sort(Vector<Node> v) {

		Comparator<Node> comparator = new  My_Comparator();
		Collections.sort(v,comparator);
		/*for(int i=0;i<v.size();i++) {
		Node g=v.get(i);
		System.out.println();
		System.out.println("new");
		System.out.println();
		for(int r=0;r<g.getSq().length;r++) {
			System.out.println();
			for(int o=0;o<g.getSq()[0].length;o++) {
				System.out.print(g.getSq()[r][o].getSq()+",");
			}
		}
	}*/
		return;
	}

	/**
	 * here the delete function.is for the DFBnB algorithm
	 * if its pass the threshold-than delete the child and the childs after him.
	 * @param v-the vector of the children.
	 * @param i-the index where need to start to delete.
	 */

	public static void delete(Vector<Node> v,int i) {

		while(i!=v.size()) {
			v.remove(i);
		}
	}

	/**
	 * factorial for the initial threshold for the DFBnB algorithm
	 * if the board have tiles<=12(not include the black tiles).
	 * @param n-the number of the tiles.
	 * @return res-the initial threshold.
	 */
	public static int factorial(int n) {

		int res = 1, i; 
		for (i=2; i<=n; i++) {
			res *= i; 
		}
		return res; 
	} 




	/**
	 * here the path to the goal that I need to return.
	 * I build link-list and put all the path by the parents their(include the son).
	 * @param g- the goal node.
	 * @return L(link-list of path).
	 */

	public static LinkedList<Node> path(Node g){
		LinkedList<Node> L=new LinkedList<Node>();
		while(g!=null) {
			L.add(g);
			g=g.getParent();
		}

		return L;
	}

	/**
	 * if I need to print the open list-so print with the use of
	 * foreach(function that belong to hashtable) the hashtable is the
	 * open list and with him I print every node in him.
	 * I print the matrix of tiles that belong to each state.
	 * @param H-the hashtable of the open list.
	 */

	public static void print_open_list(Hashtable<String,Node> H) {


		H.forEach((k, n) -> { 
			System.out.println();
			for(int i=0;i<n.getSq().length;i++) {
				System.out.println();
				for(int j=0;j<n.getSq()[0].length;j++) {
					if(j!=n.getSq()[0].length-1) {
						if(n.getSq()[i][j].getSq()!=0) {
							System.out.print(n.getSq()[i][j].getSq()+",");
						}
						else {
							System.out.print("_,");
						}
					}
					else {
						if(n.getSq()[i][j].getSq()!=0) {
							System.out.println(n.getSq()[i][j].getSq());
						}
						else {
							System.out.println("_");
						}
					}
				}
			}

		}); 

	}





	/**
	 * here the bfs  algorithm that act as brute-force algorithm with generate nodes in every level
	 * Until he find the goal state.here I used Hashtable and Queue as open list. the second Hashtable-C
	 * is the close list.note that after I get out the node from the open list I do for him expansion-
	 * and than do operations on the childs of the node that was in the open list.if the node do not on the 
	 * closure list or the open list-join him to the open list.
	 * @param start-get node start as input to generate the  nodes until the goal node if we have goal node 
	 * @param Goals-get as input vector of goal.
	 * @return the path as link list of nodes or null if we don't have goal node.
	 */

	public LinkedList<Node> BFS(Node start ,Vector<Node> Goals){

		this.Num=1;
		this.Cost=0;
		Queue<Node> L1=new LinkedList<>();
		Hashtable<String,Node> L2=new Hashtable<String,Node>();
		L1.add(start);
		L2.put(start.getSt(),start);
		Hashtable<String,Node> C=new Hashtable<String,Node>();
		while(!L1.isEmpty()) {
			if(this.print_open_list==true) {
				System.out.println();
				System.out.println("new:");
				print_open_list(L2);
			}
			Node n=(L1.remove());
			L2.remove(n.getSt());
			C.put(n.getSt(),n);
			n.expantion();
			for(int i=0;i< n.getChilds().size();i++ ) {
				Node g=operator(n);

				this.Num++;
				if(!C.containsKey(g.getSt()) && !L2.containsKey(g.getSt())) {
					if(goal(g)) {
						this.Cost=g.getF();
						return path(g);
					}
					L1.add(g);
					L2.put(g.getSt(), g); 


				}
			}

		}

		return null;

	}

	/**
	 * here the DFID algorithm-that combine bfs and dfs algorithms with threshold that start
	 * from one and continue until the MAX_VALUE.we go with dfs method until the threshold
	 * and than go to other nodes(to continue with the others nodes) .if we have cutoff-this mean that we get to the threshold and
	 * we start again from the start node.if we finish all the ways in the tile puzzle we return fail 
	 * that mean that we didn't have path.if we find the path we return him.
	 * @param Goals-get as input vector of goal.
	 * @return the path as link list of nodes or null if we don't have goal node.
	 */

	public LinkedList<Node> DFID(Node start ,Vector<Node> Goals){
		this.Cost=0;
		this.Num=1;
		for(int depth=1;depth<Integer.MAX_VALUE;depth++) {

			Hashtable<String,Node> H=new Hashtable<String,Node>();
			LinkedList<Node> result=Limited_DFS(new Node(start.getSq()),Goals,depth,H);
			if(result!=cutoff) {

				return result;
			}
		}

		return null;

	}
	/**
	 * the continue of DFID.
	 * @param n the node that we want to expansion if we can
	 * @param Goals-the vector
	 * @param limit-the limit of the threshold
	 * @param H-the hashtable of the nodes we work on
	 * @return cutoff or fail or path.
	 */

	public LinkedList<Node> Limited_DFS(Node n ,Vector<Node> Goals,int limit,Hashtable<String,Node> H){

		if(goal(n)) {
			this.Cost=n.getF();

			return path(n);
		}
		else if(limit==0) {
			return cutoff;
		}

		else {

			H.put(n.getSt(), n);
			boolean isCutoff=false;
			n.expantion();
			for(int i=0;i< n.getChilds().size();i++ ) {

				Node g=operator(n);
				this.Num++;
				if(H.containsKey(g.getSt()) ) {

					continue;
				}
				LinkedList<Node> result=Limited_DFS(g,Goals,limit-1,H);
				if(result==cutoff) {
					isCutoff=true;
				}
				else if(result!=fail) {
					return result;
				}
			}

			if(this.print_open_list==true) {
				System.out.println();
				System.out.println("new:");
				print_open_list(H);
			}

			H.remove(n.getSt());
			if(isCutoff==true) {
				return cutoff;
			}
			else {
				return fail;
			}
		}
	}


	/**
	 * here the algorithm A* that done by weights of every state from the start node(g)
	 * and until the goal node(h)-the evaluation distance until the goal node.
	 * here I built PriorityQueue and hashtable as open list and hashtable of closure list.
	 * the PriorityQueue go from the low f that consistent g+h to high function.
	 * the method here work on the node that exit from the PriorityQueue with the lowest f-and with him
	 * expansion him and join his childs.from that I move until I find the goal node.
	 * @param start-get node start as input to generate the  nodes until the goal node -if we have goal node 
	 * @param Goals-get as input vector of goal.
	 * @return the path as link list of nodes or null if we don't have goal node.
	 */

	public LinkedList<Node> A_Star(Node start,Vector<Node> Goals){

		this.Cost=0;
		this.Num=1;
		Comparator<Node> comparator = new  My_Comparator();
		PriorityQueue<Node> L1 = new PriorityQueue<Node>(comparator); 
		Hashtable<String,Node> L2=new Hashtable<String,Node>();
		Hashtable<String,Node> C=new Hashtable<String,Node>();

		L1.add(start);
		L2.put(start.getSt(), start);
		while(!L1.isEmpty()) {
			if(this.print_open_list==true) {
				System.out.println();
				System.out.println("new:");
				print_open_list(L2);
			}
			Node n=L1.remove();
			L2.remove(n.getSt());
			if(goal(n)) {
				this.Cost=n.getF();
				return path(n);
			}
			C.put(n.getSt(), n);
			n.expantion();
			for(int i=0;i< n.getChilds().size();i++ ) {
				Node x=operator(n);
				this.Num++;
				if(!C.containsKey(x.getSt()) && !L2.containsKey(x.getSt())) {
					L1.add(x);
					L2.put(x.getSt(),x);

				}
				else if(L2.containsKey(x.getSt()) && x.getF()<L2.get(x.getSt()).getF()) {
					L1.remove(L2.get(x.getSt()));
					L1.add(x);
					L2.replace(x.getSt(), L2.get(x.getSt()), x);


				}
			}

		}

		return null;

	}

	/**
	 * here the IDA_Star  of IDA_Star-and here I do hashtable to avoid loop avoidance.
	 * in first t=max_value.the algorithm act like the 
	 * DFS but with threshold. if the node that I am there bigger than the threshold-do not join him
	 * to the stack.if he is smaller check if this state in the stack if- yes check the f with him.
	 * if not join him to the stack.
	 * note that I check if the parent is already been out-if it is I go back else I insert him again
	 * to the stack.the algorithm need to find the cheaper way to the goal state.
	 * @param start-the root node.
	 * @param Goals-the vector of the goal state.
	 * @return LinkedList-path of the states to the cheaper goal state.
	 */

	public LinkedList<Node> IDA_Star(Node start,Vector<Node> Goals){

		this.Cost=0;
		this.Num=1;
		Stack<Node> L=new Stack<Node>();
		Hashtable<String,Node> H=new Hashtable<String,Node>();
		int t=start.getH();
		while(t!=Integer.MAX_VALUE) {
			int minF=Integer.MAX_VALUE;

			Node no=new Node(start.getSq());
			L.push(no);
			H.put(no.getSt(), no);

			while(!L.isEmpty()) {
				Node n=L.pop();
				if(n.isOut_for_IDA()) {
					if(this.print_open_list==true) {
						System.out.println();
						System.out.println("new:");
						print_open_list(H);
					}

					H.remove(n.getSt());

				}
				else {
					n.setOut_for_IDA(true);
					L.push(n);
					n.expantion();
					for(int i=0;i<n.getChilds().size();i++) {
						Node g=operator(n);
						this.Num++;
						if(g.getF()>t) {
							minF=Math.min(minF, g.getF());

							continue;

						}
						if(H.containsKey(g.getSt()) && H.get(g.getSt()).isOut_for_IDA()==true){

							continue;
						}
						if(H.containsKey(g.getSt()) && H.get(g.getSt()).isOut_for_IDA()!=true){

							if(g.getF()<H.get(g.getSt()).getF()) {
								Node temp=H.remove(g.getSt());
								L.remove(temp);


							}
							else {

								continue;
							}
						}

						if(goal(g)) {
							this.Cost=g.getF();
							return path(g);
						}
						L.push(g);
						H.put(g.getSt(), g);

					}
				}


			}
			t=minF;
		}



		return null;
	}




	/**
	 * here the DFBnB  of DFBnB-and here I do hashtable to avoid loop avoidance.
	 * in first if the size of the tiles are less than 13(not include the blacks tiles)
	 * than t=n!(n is the number of the tiles) else t=max_value.the algorithm act like the 
	 * DFS but with threshold. if the node that I am there bigger than the threshold-do cut and delete his rest sons,
	 * else he go to the stack and go on with his sons-which he choose the smallest f in his sons
	 * to go on with his way.the purpose of this algorithm is to find the cheaper way to the goal
	 * state.note that I check if the parent is already been out-if it is I go back else I insert him again
	 * to the stack.
	 * @param start-the root node.
	 * @param Goals-the vector of the goal state.
	 * @return LinkedList-path of the states to the cheaper goal state.
	 */
	public LinkedList<Node> DFBnB(Node start,Vector<Node> Goals){

		this.Cost=0;
		this.Num=1;
		Stack<Node> L=new Stack<Node>();
		Hashtable<String,Node> H=new Hashtable<String,Node>();
		L.push(start);
		H.put(start.getSt(), start);
		LinkedList<Node> result=null;
		int t=Integer.MAX_VALUE;

		if((((start.getSq().length)*(start.getSq()[0].length)) - start.getBlack()) <= 12){
			t=factorial(((start.getSq().length)*(start.getSq()[0].length)) - start.getBlack());
		}

		while(!L.isEmpty()) {
			Node n=L.pop();
			if(n.isOut_for_IDA()==true) {

				if(this.print_open_list==true) {
					System.out.println();
					System.out.println("new:");
					print_open_list(H);

				}
				H.remove(n.getSt());

			}
			else {
				n.setOut_for_IDA(true);
				L.push(n);
				n.expantion();
				Vector<Node> N=n.getChilds();


				sort(N);
				this.Num=this.Num+n.getChilds().size();
				for(int i=0;i<N.size();i++) {
					Node g= N.get(i);
					if(g.getF()>=t) {
						delete(N,i);
					}

					else if(H.containsKey(g.getSt()) && H.get(g.getSt()).isOut_for_IDA()) {
						N.remove(g);
					}
					else if(H.containsKey(g.getSt()) && H.get(g.getSt()).isOut_for_IDA()==false) {
						if(g.getF()>=H.get(g.getSt()).getF()) {
							N.remove(g);
						}
						else {

							Node temp=H.remove(g.getSt());
							L.remove(temp);

						}
					}
					else if (goal(g)) {
						t=g.getF();
						this.Cost=g.getF();
						result=path(g);
						delete(N,i);
					}

				}
				for(int i=N.size()-1;0<=i;i--) {
					L.push(N.get(i));
					H.put(N.get(i).getSt(), N.get(i));

				}
			}


		}
		return result;
	}




}
