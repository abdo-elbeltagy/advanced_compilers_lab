package group3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class LL1 {
	public static void main(String[] args) throws IOException {
//S,iST,e;T,cS,a#S,i,e;T,c,a#S,ca$;T,ca$
		Scanner sc=new Scanner(System.in);
		PrintWriter out=new PrintWriter(System.out);
		String input=sc.nextLine();
		for(int x=0;x<5;x++) {
		String str=sc.nextLine()+"$";
		String tockens[]=input.split("#");
		ArrayList<ArrayList<String>> rules=tockenize(tockens[0]) ;
		ArrayList<ArrayList<String>> first=tockenize(tockens[1]) ;
		ArrayList<ArrayList<String>> follow=tockenize2(tockens[2]) ;
		
		String vars[]=new String[rules.size()];
		for(int i=0;i<vars.length;i++) {
			vars[i]=rules.get(i).get(0);
		}
		String term[]=terminal(rules,vars);
//		out.println(rules);
//		out.println(first);
//		out.println(follow);
		
		String table[][]=new String[vars.length][term.length];
		
		// filling in the table .....
		for(int i=0;i<rules.size();i++) {
			String var=rules.get(i).get(0);
			int idx=Arrays.asList(vars).indexOf(var);             // the index of the variable in the vars array
			for(int j=1;j<rules.get(i).size();j++) {
				String rule=rules.get(i).get(j);
				
				if(rule.equals("e")) {
					ArrayList<String>fol = getfol(var, follow);       // fol is an array list with the follow of the variable
					for(int t=1;t<fol.size();t++) {
						table[idx][Arrays.asList(term).indexOf(fol.get(t))]="e";        // assign the follows with epcelons
					}
					
				}
				else {
					String frst=first.get(i).get(j);
					boolean eps=false;
					for(int k=0;k<frst.length();k++) {
						if(frst.charAt(k)=='e')
							eps=true;
						else {
							table[idx][Arrays.asList(term).indexOf(frst.charAt(k)+"")]=rule;
						}
					}
					if(eps) {
						ArrayList<String>fol = getfol(var, follow);       // fol is an array list with the follow of the variable
						for(int t=1;t<fol.size();t++) {
							table[idx][Arrays.asList(term).indexOf(fol.get(t))]="e";        // assign the follows with epcelons
						}
					}
				}
//				if(f=='e') {                   // in case epcelon
//					ArrayList<String>fol = null;       // fol is an array list with the follow of the variable
//					for(int k=0;k<follow.size();k++) {
//						if(follow.get(k).get(0).equals(var)) {
//							fol=follow.get(k);
//							break;
//						}
//							
//					}
//					for(int t=1;t<fol.size();t++) {
//						table[idx][Arrays.asList(term).indexOf(fol.get(t))]="e";        // assign the follows with epcelons
//					}
//				}
//				
//				
//				else if(Arrays.asList(term).contains(f+"")) {                 // in case terminal
//					table[idx][Arrays.asList(term).indexOf(f+"")]=rule;
//				}
//				
//				
//				else if(Arrays.asList(vars).contains(f+"")) {				// in case Variable
//					ArrayList<String>fir = null;       // fol is an array list with the follow of the variable
//					for(int k=0;k<first.size();k++) {
//						if(first.get(k).get(0).equals(f+"")) {
//							fir=first.get(k);
//							System.err.println(fir);
//							break;
//						}
//						out.println("here");
//							
//					}
//					for(int t=1;t<fir.size();t++) {
//						table[idx][Arrays.asList(term).indexOf(fir.get(t))]=rule;        // assign the follows with epcelons
//					}
//				}
			}
		}
		
		ArrayList<String>output=new ArrayList<String>();
		output.add("S");
		Stack<String>st=new Stack<>();
		st.push("S");
		String last="S";
		for(int i=0;i<str.length();i++) {
			if(st.isEmpty())
				{
				if(str.charAt(i)!='$')
					output.add("Error");
				break;
				}
			if(Arrays.asList(term).contains(st.peek())) {
				if(st.peek().equals(str.charAt(i)+""))
					st.pop();
				else {
					output.add("Error");
					System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeee");
					break;
				}
			}
			else if(Arrays.asList(vars).contains(st.peek())) {
				
				String var=st.pop();
				String rl=table[Arrays.asList(vars).indexOf(var)][Arrays.asList(term).indexOf(str.charAt(i)+"")];
				if(rl==null) {
					output.add("ERROR");
					break;
				}
				
				else {

					if(rl=="e")
						last=last.replaceFirst(var, "");
					else {
						last=last.replaceFirst(var, rl);
						for(int m=rl.length()-1;m>=0;m--) {
							st.push(rl.charAt(m)+"");
							}
						}
					output.add(last);
				}
				
				i--;
			}
		}
		out.print(str+"====>");
		out.println(output);

		
		}
		
		
		
		
// Tell here i've got the rules of the rule and first and follo tomorro you should get the table and construct the pda isa
		out.flush();
		out.close();
		
	}
	
	public static ArrayList<String>getfol(String var,ArrayList<ArrayList<String>>follow){
		ArrayList<String>fol = null;       // fol is an array list with the follow of the variable
		for(int k=0;k<follow.size();k++) {
			if(follow.get(k).get(0).equals(var)) {
				fol=follow.get(k);
				break;
			}
				
		}
		return fol;
	}
	
	public static ArrayList<ArrayList<String>> tockenize(String tocken){
		ArrayList<ArrayList<String>> ret=new  ArrayList<ArrayList<String>>();
		String split[]=tocken.split(";");
		for(int i=0;i<split.length;i++) {
			ret.add(new ArrayList<String>());
			String rule[]=split[i].split(",");
			for(int j=0;j<rule.length;j++) {
				ret.get(i).add(rule[j]);
			}
			
		}
		return ret;
	}
	public static ArrayList<ArrayList<String>> tockenize2(String tocken){
		ArrayList<ArrayList<String>> ret=new  ArrayList<ArrayList<String>>();
		String split[]=tocken.split(";");
		for(int i=0;i<split.length;i++) {
			ret.add(new ArrayList<String>());
			String rule[]=split[i].split(",");
			for(int j=0;j<rule.length;j++) {
				for(int k=0;k<rule[j].length();k++) {
					
					String elem=rule[j].charAt(k)+"";
					if(!elem.equals("e"))
						ret.get(i).add(elem);
				}
			}
			
		}
		return ret;
	}
	
	public static String[] terminal (ArrayList<ArrayList<String>>rules,String []vars){
		ArrayList<String>var=new ArrayList<String>();
		for(String elem:vars) {
			var.add(elem);
		}
		TreeSet<String>set=new TreeSet<>();
		for(int i=0;i<rules.size();i++) {
			for(int j=1;j<rules.get(i).size();j++) {
				String rl=rules.get(i).get(j);
				for(int k=0;k<rl.length();k++) {
					if(!var.contains(rl.charAt(k)+"")&&rl.charAt(k)!='e') {
						set.add(rl.charAt(k)+"");
					}
				}
			}
		}
		set.add("$");
		String []ret=new String[set.size()];
		int i=0;
		for(String elem:set) {
			ret[i++]=elem;
		}
		return ret;
	}
	
//	public static String[]terminal(ArrayList<ArrayList<String>>first,ArrayList<ArrayList<String>>follow){
//		TreeSet<String>set=new TreeSet<>();
//		for(int i=0;i<first.size();i++) {
//			for(int j=1;j<first.get(i).size();j++) {
//				set.add(first.get(i).get(j));
//			}
//		}
//		for(int i=0;i<follow.size();i++) {
//			for(int j=1;j<follow.get(i).size();j++) {
//				set.add(follow.get(i).get(j));
//			}
//		}
//		set.add("$");                           // this is to make sure that the dollar sign exists
//		String []ret=new String[set.size()];
//		int i=0;
//		for(String elem:set) {
//			ret[i++]=elem;
//		}
//		return ret;
//	}
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
