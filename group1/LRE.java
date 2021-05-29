package group3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LRE {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		String cfg[]=sc.nextLine().split(";");
		int numvar=cfg.length;
		String var[]=new String[numvar];
		ArrayList<ArrayList<String>> list=new ArrayList();
		for(int i=0;i<cfg.length;i++) {
			list.add(new ArrayList<String>());
			String[]rule=cfg[i].split(",");
			var[i]=rule[0];
			for(int j=0;j<rule.length;j++) {
				list.get(i).add(rule[j]);
			}
		}
		int cur=-1;
		
		for(int i=0;i<numvar;i++) {     //for every inner arraylist
			cur++;
			ArrayList<String>crnt=list.get(cur);
			for(int j=0;j<i;j++) {            // for previos vars
				char curchar=var[j].charAt(0);
				for(int k=1;k<crnt.size();k++) {         // loop over the inner elements of each array list
					if(crnt.get(k).charAt(0)==curchar) {  // if any elemnt in the current starts with a previous variable
						int idx=0;                       // the index of the previous rule to be substituted
						for(idx=0;idx<cur;idx++) {
							if(list.get(idx).get(0).charAt(0)==curchar)
								break;
						}
						String sub=crnt.get(k).substring(1,crnt.get(k).length());
						crnt.remove(k);
						//k--;                            // this is to substitute for the removed elemnt k
						int added=list.get(idx).size();
						for(int x=1;x<list.get(idx).size();x++) {
							crnt.add(k++,list.get(idx).get(x)+sub);
						}
						k--;
						
					}
				}                 
				
			} // tell here i guarntee the previous vars; lets check the current one.
			boolean lr=false;
			for(int k=1;k<crnt.size();k++) {
				if(crnt.get(k).charAt(0)==crnt.get(0).charAt(0)) {
					lr=true;
					break;
				}
			}
			if(lr) {
				ArrayList<String> dash=new ArrayList<String>();
				dash.add(crnt.get(0)+"-");
				for(int k=1;k<crnt.size();k++) {
					if(crnt.get(k).charAt(0)==crnt.get(0).charAt(0)) {
						dash.add(crnt.get(k).substring(1,crnt.get(k).length())+crnt.get(0)+"-");
						crnt.remove(k);
						k--;
					}
					else {
						String nElem=crnt.get(k)+crnt.get(0)+"-";
						crnt.remove(k);
						crnt.add(k,nElem);
					}
				}
				
				dash.add("e");
				list.add(++cur,dash);
			}
			
		}
		
		/*
		 * for(int i=0;i<numvar;i++) { for(int j=0;j<numvar;j++) { if(i<j) {
		 * ArrayList<String>current=list.get(cur); for(int k=1;k<current.size();k++) {
		 * if(current.get(k).charAt(0)==var[j].charAt(0)) } } } }
		 */
		System.out.println(Arrays.toString(var));
		System.out.println(list.toString());
		StringBuilder output=new StringBuilder("");
		
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<list.get(i).size();j++) {
				output.append(list.get(i).get(j));
				if(j<list.get(i).size()-1)
					output.append(",");
			}
			if(i<list.size()-1)
				output.append(";");
		}
		
		
		System.out.println(output.toString());
		
//		
		
		
//S,ScT,Sa,T,b;T,aSb,iaLb,i;L,SdL,S
//S,aSt,aS,c
//S,StS,SxS,a
//S,StT,T;T,TxF,F;F,id
//S,EF,Fd;E,SF,ES,c;F,SE,c
//S,ScT,Sa,T,b;T,aSb,iaLb,i;L,SdL,S
		
		

	}
	static class Scanner
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException
        {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public boolean ready() throws IOException {return br.ready();}


    }

}
