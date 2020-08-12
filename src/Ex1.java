
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Ex1 {


	/**
	 * @author shai kaikov.
	 * here I activate the input and output files for the task. 
	 */




	/**
	 * here I built the file of the output.its simple logic of write to the file,
	 * therefore its not about the course and I will not explain this.note that I used the
	 *  Characters on the if conditions because I encounter difficulties with the string that
	 *  came from the read of file(I could not to compare between strings).
	 * @param path-path of the nodes to the goal node.
	 * @param bgs-the object of the allalgo class.
	 * @param time-string of run time of the algorithm.
	 * @param runt is the condition of run time that I read from the file.
	 */

	public static void output(LinkedList<Node> path,allalgo bgs,String time,String runt) {


		try {
			File writee = new File("output4.txt");
			if(!writee.exists()) {
				writee.createNewFile();
			}

			PrintWriter bw = new PrintWriter(writee);
			if(path!=null) {
				String st=path.getFirst().getMoves().substring(1);
				bw.println(st);
				st="Num: "+Integer.toString(bgs.getNum());
				bw.println(st);
				st="Cost: "+Integer.toString(bgs.getCost());
				bw.println(st);

				if(runt.charAt(0)=='n' && runt.charAt(1)=='o' && runt.charAt(2)==' ' && runt.charAt(3)=='t' && runt.charAt(4)=='i' && runt.charAt(5)=='m' && runt.charAt(6)=='e') {

					bw.close();


				}

				else if(runt.charAt(0)=='w' && runt.charAt(1)=='i' && runt.charAt(2)=='t' && runt.charAt(3)=='h' && runt.charAt(4)==' ' && runt.charAt(5)=='t' && runt.charAt(6)=='i' && runt.charAt(7)=='m' && runt.charAt(8)=='e') {

					st=time+" seconds";
					bw.println(st);
					bw.close();

				}
				System.out.println(("Done!"));
			}
			else if(path==null) {
				String st="no path";
				bw.println(st);
				st="Num: "+Integer.toString(bgs.getNum());
				bw.println(st);
				bw.close();
				System.out.println(("Done with no path"));
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}



	/**
	 * here I built the file of the input.its simple logic of read from file,
	 * therefore its not about the course and I will not explain this.note that I used the
	 * arrays of Characters on the if conditions because I encounter difficulties with the string that
	 * came from the read of file(I could not to compare between strings).in short here I pick the 
	 * algorithm in match the file was been read.after that send all the data I need to the output
	 * function in order to print what I need.
	 * @param start node-  here I get the start node from the main after I build him.
	 * @param Goals-vector of goals-after I built him in the main-I send him to the algorithm(one of them).
	 * @param algo-get string from the main after I read him from file and with him I pick the algorithm
	 * that I need to work with him.
	 * @param runt-the string of runt-with him I decide if will be run time or not after I read him from file-go to output.
	 * @param kindlist-string that I read from file -with that I decide if I need to print the open list(in the algorithm).
	 */



	public static void input(Node start,Vector<Node> Goals,String algo,String runt,String kindlist) {

		boolean b=false;
		if(kindlist.charAt(0)=='w' && kindlist.charAt(1)=='i' && kindlist.charAt(2)=='t' &&
				kindlist.charAt(3)=='h' && kindlist.charAt(4)==' ' && kindlist.charAt(5)=='o' &&
				kindlist.charAt(6)=='p' && kindlist.charAt(7)=='e' && kindlist.charAt(8)=='n') {
			b=true;
		}

		char c1[]= {'B','F','S'};
		char c2[]= {'D','F','I','D'};
		char c3[]= {'A','*'};
		char c4[]= {'I','D','A','*'};
		char c5[]= {'D','F','B','n','B'};

		if(algo.charAt(0)==c3[0] && algo.charAt(1)==c3[1]) {
			allalgo bgs=new allalgo();
			bgs.setPrint_open_list(b);
			long startime=System.nanoTime();
			LinkedList<Node> l=bgs.A_Star(start, Goals);
			long endtime=System.nanoTime();
			long total_time=endtime-startime;
			double convert = (double) total_time / 1_000_000_000;
			String t1=(String.format("%.12f", convert));
			output(l,bgs,t1,runt);


		}


		else if(algo.charAt(0)==c1[0] && algo.charAt(1)==c1[1] && algo.charAt(2)==c1[2]) {
			allalgo bgs=new allalgo();
			bgs.setPrint_open_list(b);
			long startime=System.nanoTime();
			LinkedList<Node> l=bgs.BFS(start, Goals);
			long endtime=System.nanoTime();
			long total_time=endtime-startime;
			double convert = (double) total_time / 1_000_000_000;
			String t1=(String.format("%.12f", convert));
			output(l,bgs,t1,runt);

		}

		else if(algo.charAt(0)==c2[0] && algo.charAt(1)==c2[1] && algo.charAt(2)==c2[2] && algo.charAt(3)==c2[3]) {
			allalgo bgs=new allalgo();
			bgs.setPrint_open_list(b);
			long startime=System.nanoTime();
			LinkedList<Node> l=bgs.DFID(start, Goals);
			long endtime=System.nanoTime();
			long total_time=endtime-startime;
			double convert = (double) total_time / 1_000_000_000;
			String t1=(String.format("%.12f", convert));
			if(l==bgs.getFail() || l==null) {
				l=null;
			}
			output(l,bgs,t1,runt);
		}

		else if(algo.charAt(0)==c4[0] && algo.charAt(1)==c4[1] && algo.charAt(2)==c4[2] && algo.charAt(3)==c4[3] ) {
			allalgo bgs=new allalgo();
			bgs.setPrint_open_list(b);
			long startime=System.nanoTime();
			LinkedList<Node> l=bgs.IDA_Star(start, Goals);
			long endtime=System.nanoTime();
			long total_time=endtime-startime;
			double convert = (double) total_time / 1_000_000_000;
			String t1=(String.format("%.12f", convert));
			output(l,bgs,t1,runt);

		}

		else if(algo.charAt(0)==c5[0] && algo.charAt(1)==c5[1] && algo.charAt(2)==c5[2] && algo.charAt(3)==c5[3] && algo.charAt(4)==c5[4]) {
			allalgo bgs=new allalgo();
			bgs.setPrint_open_list(b);
			long startime=System.nanoTime();
			LinkedList<Node> l=bgs.DFBnB(start, Goals);
			long endtime=System.nanoTime();
			long total_time=endtime-startime;
			double convert = (double) total_time / 1_000_000_000;
			String t1=(String.format("%.12f", convert));
			output(l,bgs,t1,runt);

		}

	}


	/**
	 * here I create vector of goal to send him to the algorithm.
	 * just put num++ in every tile on the board-and than I get the board.
	 * @param row-represent the number of the rows of the board
	 * @param col-represent the number of the columns of the board.
	 * @return vector of goal(Node).
	 */


	public static Vector<Node> find_goal(int row,int col) {

		int num=1;
		square sq1[][]=new square[row][col];
		for(int i=0;i<sq1.length;i++) {
			for(int j=0;j<sq1[0].length;j++) {
				if(i==sq1.length-1 && j==sq1[0].length-1) {
					sq1[i][j]=new square(0,-1);
				}
				else {
					sq1[i][j]=new square(num,-1);
					num++;
				}
			}
		}

		Vector<Node> v1=new Vector<Node>();
		v1.add(new Node(sq1));
		return v1;

	}




	/**
	 * 
	 * here I handle the input file.I will not explain to much here
	 * -in the reason that its a a lot of logic that do not connect to the idea 
	 * of the course itself.in short in every count its match to every line.
	 * and I create vectors of colors and numbers to help me build board.
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader reader;
		String algo="";
		String runtime="";
		String kindlist="";
		int count=0;
		Vector<Integer> black=new Vector<Integer>();
		Vector<Integer> red=new Vector<Integer>(); 
		Vector<square> sq1=new Vector<square>();
		int row=0;
		int col=0;
		square sq2[][];
		int c=0;
		try {
			FileReader file=new FileReader("C:/input4.txt");
			reader=new BufferedReader(file);
			String line=reader.readLine();
			while(line!=null) {
				if(count==0) {
					algo=(String)line;

				}
				if(count==1) {
					runtime=line;
				}

				if(count==2) {
					kindlist=line;
				}


				if(count==3) {
					for(int i=0;i<line.length();i++) {

						if(Character.isDigit(line.charAt(i))==false && c==0) {
							row=(Integer.parseInt(line.substring(c, i)));
							c=i+1;

						}
						else if(i==line.length()-1) {
							col=(Integer.parseInt(line.substring(c, i+1)));

						}
					}


				}
				if(count==4) {
					c=0;
					boolean flag=false;
					for(int i=0;i<line.length();i++) {
						if(flag==true) {
							if(Character.isDigit(line.charAt(i))==false) {
								black.add(Integer.parseInt(line.substring(c, i)));
								c=i+1;
							}
							else if(i==line.length()-1) {
								black.add(Integer.parseInt(line.substring(c, i+1)));
							}
						}
						if(Character.isDigit(line.charAt(i))!=false && flag==false) {
							if(i==line.length()-1) {
								black.add(Integer.parseInt(line.substring(i, line.length())));
							}
							else {
								c=i;
								flag=true;
							}
						}
					}


				}

				if(count==5) {
					c=0;
					boolean flag=false;
					for(int i=0;i<line.length();i++) {

						if(flag==true) {
							if(Character.isDigit(line.charAt(i))==false) {
								red.add(Integer.parseInt(line.substring(c, i)));
								c=i+1;

							}
							else if(i==line.length()-1) {
								red.add(Integer.parseInt(line.substring(c, i+1)));

							}
						}

						if(Character.isDigit(line.charAt(i))!=false && flag==false) {
							if(i==line.length()-1) {
								red.add(Integer.parseInt(line.substring(i, line.length())));
							}
							else {
								c=i;
								flag=true;
							}
						}
					}


				}

				if(6<=count) {
					c=0;
					int x=0;
					for(int i=0;i<line.length();i++) {
						if(Character.isDigit(line.charAt(i))==false && i!=line.length()-1 ) {
							if(line.charAt(i)=='_') {
								sq1.add(new square(0,0));
								c=i+2;
								i++;
							}
							else {
								x=(Integer.parseInt(line.substring(c, i)));

								c=i+1;
								if(red.contains(x)) {
									sq1.add(new square(x,2));

								}

								else if(black.contains(x)) {
									sq1.add(new square(x,3));

								}
								else {
									sq1.add(new square(x,1));

								}
							}
						}



						else if(i==line.length()-1) {
							if(line.charAt(i)!='_') {
								x=(Integer.parseInt(line.substring(c, i+1)));

								if(red.contains(x)) {
									sq1.add(new square(x,2));

								}
								else if(black.contains(x)) {
									sq1.add(new square(x,3));

								}
								else  {
									sq1.add(new square(x,1));

								}
							}
							else if(line.charAt(i)=='_') {
								sq1.add(new square(0,0));
							}
						}

					}
				}




				//System.out.println(line);
				line=reader.readLine();
				count++;
			}
			reader.close();

		}
		catch(IOException e) {
			e.printStackTrace();
		}

		sq2=new square[row][col];

		for(int i=0;i<sq2.length;i++) {
			for(int j=0;j<sq2[0].length;j++) {
				sq2[i][j]=sq1.remove(0);

			}
		}





		Node start=new Node(sq2);

		Vector<Node> Goals=find_goal(row,col);
		input(start,Goals,algo,runtime,kindlist);
        
        
        var csc="css";

	}

}
