package programming;

import java.util.Arrays;

public class fdfa {
	public int [][]states;
	public int[]goal;
	public String[]action;
	
	public static void main(String[] args) {
		fdfa fd=new fdfa("0,0,1,A;1,3,2,B;2,3,2,C;3,4,3,D;4,3,4,E#2,4");
		fd.run("001");
	}
	
	
	public fdfa(String discr) {
		
		
		String []states=discr.split("#")[0].split(";");
		String []goal=discr.split("#")[1].split(",");
		int n =states.length;
		this.states=new int [2][n];
		this.action = new String[n];
		
		for (int i=0;i<n;i++) {
			String splt[]=states[i].split(",");
			int stat=Integer.parseInt(splt[0]);
			int zero=Integer.parseInt(splt[1]);
			int one=Integer.parseInt(splt[2]);
			
			this.states[0][stat]=zero;
			this.states[1][stat]=one ;
			this.action[stat]=splt[3];
			
		}
		
		System.out.println(Arrays.toString(this.states[0]));
		
		this.goal=new int[goal.length];
		for(int i=0;i<goal.length;i++) {
			this.goal[i]=Integer.parseInt(goal[i]);
		}
		
		System.out.println(Arrays.toString(goal));

		
		
		
	}
	
	public boolean is_goal(int state)
	{
		for(int i=0;i<this.goal.length;i++) {
			if(state==this.goal[i])
				return true;
		}
		return false;
		
	}
	
	public void run(String fdfa  ) {
		char ch[]=fdfa.toCharArray();
		int fdf[]=new int[ch.length];
		for(int i=0;i<ch.length;i++) {
			fdf[i]=Integer.parseInt(ch[i]+"");
		}
		
		int idx=0;
		while(idx<fdf.length) {
			int cur=0;
			int acc=-1;
			int accidx=-1;
		
		for(int i=idx;i<fdf.length;i++) {
			cur=this.states[fdf[i]][cur];
			if(this.is_goal(cur)) {
				acc=cur;
				accidx=i;
			}
		}
		if(this.is_goal(cur)|acc==-1) {
			System.out.println(this.action[cur]);
			return;
		}
		else {
			System.out.print(this.action[acc]);
			idx=accidx+1;
		}
		
		}
		
		
		
		
	}

}







