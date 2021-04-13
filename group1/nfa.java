package group1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;




public class nfa {
	TreeMap<Integer, TreeSet<Integer>>zero=new TreeMap<Integer, TreeSet<Integer>>();
	TreeMap<Integer, TreeSet<Integer>>one=new TreeMap<Integer, TreeSet<Integer>>();
	TreeMap<Integer, TreeSet<Integer>>eps=new TreeMap<Integer, TreeSet<Integer>>();
	TreeMap<Integer, String>states=new TreeMap<Integer, String>();

	TreeMap<Integer, Integer>zerotrans;
	TreeMap<Integer, Integer>onetrans;
	int goal[];
	public static void main(String[] args) {
		
		nfa nn=new nfa("1,2;4,5;6,7;8,9#2,3;5,6#0,1;0,4;3,1;3,4;7,8;7,10;9,8;9,10#10");
		System.out.println(nn.run("0100"));
		System.out.println();
		System.out.println(nn.run("01010"));
		System.out.println();
		System.out.println(nn.run("01"));
		System.out.println();
		System.out.println(nn.run("0101"));
		System.out.println();
		System.out.println(nn.run("01001"));
		System.out.println();
		
		System.out.println("*****************************************************************************************");
		System.out.println();
		
		nfa nn1=new nfa("4,5;6,7#0,1;2,3#1,2;1,4;3,2;3,4;5,6;5,8;7,6;7,8#8");
		System.out.println(nn1.run("1"));
		System.out.println();
		System.out.println(nn1.run("1001"));
		System.out.println();
		System.out.println(nn1.run("111"));
		System.out.println();
		System.out.println(nn1.run("100"));
		System.out.println();
		System.out.println(nn1.run("110"));
		System.out.println();
		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.println(nn.eps.toString());
//	
//		System.out.println(nn.getclosure(1).toString());
//		System.out.println();
//		System.out.println(nn.zerotrans);
//		System.out.println(nn.onetrans);
//		System.out.println(nn.states);
//		System.out.println(Arrays.toString(nn.goal));
		
		


//		[0, 1, 3, 4]
		
		
		
		
//		TreeMap< Integer, TreeSet<Integer>>map=new TreeMap<Integer, TreeSet<Integer>>();
//		TreeSet<Integer>set=new TreeSet<Integer>();
//		set.add(1);
//		set.add(2);
//		map.put(1,set);
//		System.out.println(map.toString());
//		TreeSet<Integer>set1=new TreeSet<Integer>();
//		set1.add(1);
//		set1.add(10);
//		set1.add(3);
//		map.put(1,set1);
//		System.out.println(map.toString());
//		map.get(1).add(50);
//		System.out.println(map.toString());
//		map.get(1).add(450);
//		System.out.println(map.toString());
//		map.get(1).add(490);
//		System.out.println(map.toString());
//		TreeSet<Integer>ts=map.get(1);
//		TreeSet<Integer>def=new TreeSet<Integer>();
//		TreeSet<Integer>ged=map.getOrDefault(19, def);
//		System.out.println(ged.toString());
//		map.put(10,ged);
//		System.out.println(map.toString());

		
		



		

		
//		TreeSet<String> a = new TreeSet<String>() ; 
//	
//		TreeSet<Integer> b = new TreeSet<Integer>() ;
//		b.add(1) ; 
//		b.add(2) ; 
//		b.add(3) ; 
//		
//		
//		TreeSet<Integer> c = new TreeSet<Integer>() ;
//		c.add(3) ; 
//		c.add(2) ; 
//		c.add(1) ;
//		a.add(b.toString()) ; 
//		a.add(c.toString()) ; 
//		
//		TreeMap<Integer, String> vv = new TreeMap<Integer, String>() ;
//		vv.put(1, b.toString()) ; 
//		System.out.println(vv.values().contains(c.toString()) );
////		System.out.println(c.toString());
////		System.out.println(a.size());
	}
	
	public nfa(String s) {
		String []zoef=s.split("#");
		String z[]=zoef[0].split(";");
		String o[]=zoef[1].split(";");
		String e[]=zoef[2].split(";");
		String []f=zoef[3].split(",");
		goal=new int[f.length];
		
		for (int i=0;i<goal.length;i++) {
			goal[i]=Integer.parseInt(f[i]);
		}
		
		for (int i=0;i<z.length;i++) {
			String []kv=z[i].split(",");
			int k=Integer.parseInt(kv[0]);
			int v=Integer.parseInt(kv[1]);
			TreeSet<Integer>def=new TreeSet<Integer>();
			TreeSet<Integer>god=zero.getOrDefault(k, def);
			god.add(v);
			zero.put(k,god);
		}
		
		for (int i=0;i<o.length;i++) {
			String []kv=o[i].split(",");
			int k=Integer.parseInt(kv[0]);
			int v=Integer.parseInt(kv[1]);
			TreeSet<Integer>def=new TreeSet<Integer>();
			TreeSet<Integer>god=one.getOrDefault(k, def);
			god.add(v);
			one.put(k,god);
		}
		
		for (int i=0;i<e.length;i++) {
			String []kv=e[i].split(",");
			int k=Integer.parseInt(kv[0]);
			int v=Integer.parseInt(kv[1]);
			TreeSet<Integer>def=new TreeSet<Integer>();
			TreeSet<Integer>god=eps.getOrDefault(k, def);
			god.add(v);
			eps.put(k,god);
		}
		
		Queue<TreeSet<Integer>>q=new LinkedList<TreeSet<Integer>>();
		states=new TreeMap<Integer, String>();
		zerotrans=new TreeMap<Integer, Integer>();
		onetrans=new TreeMap<Integer, Integer>();
		q.add(this.getclosure(0));
		int idx=0;
		states.put(idx++,this.getclosure(0).toString());
		int currindex=0;
		while(!q.isEmpty()) {
//			System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
			TreeSet<Integer>curstat=q.remove();
			
			
			
			currindex=this.get_key(curstat.toString(), states);
			
			TreeSet<Integer>zts=new TreeSet<Integer>();
			TreeSet<Integer>ots=new TreeSet<Integer>();

			Iterator<Integer>itr=curstat.iterator(); 

			// getting the zero transition
			while(itr.hasNext()) {


				int vv=itr.next();
				if(this.zero.get(vv)!=null) {
					Iterator<Integer>initr=this.zero.get(vv).iterator();
					while(initr.hasNext()){
						zts.addAll(this.getclosure(initr.next()));
					}
				}
				
			}
			
			if(states.containsValue(zts.toString())) {     // if the zero transition has been visited before
				int idxzero=this.get_key(zts.toString(), states);
				zerotrans.put(currindex, idxzero);
			}
			else {
				q.add(zts);                           // add in the queue
				states.put(idx,zts.toString());
				zerotrans.put(currindex, idx++);
			}
			
			
			
			itr=curstat.iterator();

			while(itr.hasNext()) {  
				                        // getting the one transtion
				int vv=itr.next();
				if(this.one.get(vv)!=null) {
				Iterator<Integer>initr=this.one.get(vv).iterator();
				while(initr.hasNext()){
					ots.addAll(this.getclosure(initr.next()));
				}}
			}
			
			if(states.containsValue(ots.toString())) {     // if the zero transition has not been visited before
				int idxone=this.get_key(ots.toString(), states);
				onetrans.put(currindex, idxone);
			}
			else {
				q.add(ots);                           // add in the queue
				states.put(idx,ots.toString());
				onetrans.put(currindex, idx++);
			}
			
			
			
			
			
		}
		
		
		

		
		
	}
	
	boolean run(String s) {
		
		char c[]=s.toCharArray();
		int []input=new int [c.length];
		for (int i=0;i<c.length;i++) {
			input[i]=Integer.parseInt(c[i]+"");
		}
		
		int curr=0;
		for(int i=0;i<input.length;i++) {
			if(input[i]==0) {
				curr=zerotrans.get(curr);
			}
			else {
				curr=onetrans.get(curr);
			}
		}
		
		
		
		return is_goal(states.get(curr));
		
	}
	boolean is_goal(String s) {
		if(s.length()<=2)return false;

		String[] new_s=s.substring(1,s.length()-1).split(", ");
		int gg[]=new int [new_s.length];
		for (int i=0;i<gg.length;i++) {
			gg[i]=Integer.parseInt(new_s[i]);
		}
		
		for(int i=0;i<gg.length;i++) {
			for(int j=0;j<this.goal.length;j++) {
				if(gg[i]==this.goal[j])
					return true;
			}
		}
		return false;
	}
	
	TreeSet<Integer> getclosure(int x){
		TreeSet<Integer>ret=new TreeSet<Integer>();
		ret.add(x);
		int size=0;
		while(ret.size()!=size) {
			size=ret.size();
			Iterator<Integer>itr=ret.iterator();
			TreeSet<Integer>temp=new TreeSet<Integer>();
			while(itr.hasNext()){
				TreeSet<Integer> god=this.eps.getOrDefault(itr.next().intValue(), new TreeSet<Integer>());
				temp.addAll(god);
			}
			if(temp.size()==0) {
				break;
			}
			else {
				ret.addAll(temp);
			}
		
			
		}
		return ret;
		
	}
	
	int get_key(String value, TreeMap<Integer, String> tm) {
		for (Map.Entry<Integer,String> entry : tm.entrySet()) {
			if(entry.getValue().equals(value)) {
				return entry.getKey();
			}
		} 
		return 0;
		
	}
	
	
	
	

}
