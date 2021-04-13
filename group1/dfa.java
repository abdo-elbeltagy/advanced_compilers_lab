package group1;

import java.util.Arrays;

public class dfa {
	public int [][]states;
	public int[]goal;
 	public static void main(String[] args) {
		dfa dfa1=new dfa("0,0,1;1,2,1;2,3,3;3,4,1;4,5,2;5,5,4#1,2");
		System.out.println(dfa1.run("01010101"));
		
		System.out.println(dfa1.run("10101010"));
		
		System.out.println(dfa1.run("0111"));
		
		System.out.println(dfa1.run("11001100"));
		
		System.out.println(dfa1.run("01010001"));
		System.out.println();
		
		dfa dfa2=new dfa("0,1,0;1,1,2;2,1,3;3,4,3;4,4,4#2");
		System.out.println(dfa2.run("001110101"));
		
		System.out.println(dfa2.run("01010101"));
		
		System.out.println(dfa2.run("10101011"));
		
		System.out.println(dfa2.run("11001100"));
		
		System.out.println(dfa2.run("10010101011010"));

		
	}
 	
 	/*
 	DFA 1: 
 	0,0,1;1,2,1;2,0,3;3,3,3#1,3


 	1) 11 -> True
 	2) 01010100 -> True
 	3) 100010010 -> False
 	4) 101 -> True
 	5) 0010 -> False
 	
 	DFA 2:
0,3,1;1,2,1;2,2,1;3,3,3#2


6) 010 -> false
7) 10101010 -> true
8) 10010 -> true
9) 100010011 -> false
10) 010010 -> false

 	*/
	
	public dfa(String discr) {
		String []states=discr.split("#")[0].split(";");
		String []goal=discr.split("#")[1].split(",");
		int n=states.length;
		this.states=new int [2][n];
		
		for (int i=0;i<n;i++) {
			String splt[]=states[i].split(",");
			int stat=Integer.parseInt(splt[0]);
			int zero=Integer.parseInt(splt[1]);
			int one=Integer.parseInt(splt[2]);
			
			this.states[0][stat]=zero;
			this.states[1][stat]=one;
			
		}
		
		this.goal=new int[goal.length];
		for(int i=0;i<goal.length;i++) {
			this.goal[i]=Integer.parseInt(goal[i]);
		}
		
		
		
		
		
	}
	
	public boolean is_goal(int state)
	{
		for(int i=0;i<this.goal.length;i++) {
			if(state==this.goal[i])
				return true;
		}
		return false;
		
	}
	
	public boolean run(String dfa) {
		char ch[]=dfa.toCharArray();
		int curr=0;
		for (int i=0;i<ch.length;i++) {
			int idx=Integer.parseInt(ch[i]+"");
			curr=this.states[idx][curr];
			
		}
		return is_goal(curr);
	}
	
	
}

