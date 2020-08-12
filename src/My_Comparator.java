
import java.util.Comparator; 

/**
 * 
 * @author shai kaikov
 * 
 * here the Comparator for the heuristic algorithms-for the priority queue- and more kinds 
 * of sort.
 *
 */

public class My_Comparator implements Comparator<Node> {

	/**
	 * here I take the two nodes and compare them with their f function 
	 * as long as the f of node 1 is less from the other f of the second node
	 * he will become before him in his priority.if they are equal-check who was first in the iteration
	 * and do the priority with that. 
	 * @param n-input the first node.
	 * @param n1-input the second node.
	 * @return number.
	 */
	public int compare(Node n, Node n1) {

		if (n.getF() < n1.getF()) {
			return -1;
		}
		if (n.getF() > n1.getF()) {
			return 1;
		}

		if(n.getF() == n1.getF()){
			if(n.getWho_Was_first()<n1.getWho_Was_first()){
				return -1;

			}
			if(n.getWho_Was_first()>n1.getWho_Was_first()){
				return 1;

			}


		}
		return 0;
	}



} 



