class Move{
	public int[] from;
	public int[] to;
	public Move(int[] fr, int[] t){
		from = new int[]{fr[0], fr[1]};
		to = new int[]{t[0], t[1]};
	}
	
	public Move(Move m){
		from = new int[]{m.from[0], m.from[1]};
		to = new int[]{m.to[0], m.to[1]};
	}
	
	public Move(int fry, int frx, int toy, int tox){
		from = new int[2];
		to = new int[2];
		from[0] = fry;
		from[1] = frx;
		to[0] = toy;
		to[1] = tox;
	}  
	
	public String toString(){
		return new String(from[0]+" "+from[1]+" "+to[0]+" "+to[1]);
	}
	
	public boolean equals(Move m){
		return from[0] == m.from[0] && from[1] == m.from[1] && to[0] == m.to[0] && to[1] == m.to[1];
	}
}