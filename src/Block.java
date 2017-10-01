import java.util.Arrays;

class Block {
	public int[] size; // [0] --> Y       [1] --> X
	public int[] loc;

	public Block(int[] sz, int[] lc){
		size = sz;
		loc = lc;
	}
	
	public Block(Block b){
		this(b.size, new int[]{b.loc[0], b.loc[1]});
	}
	
	

	public void move(int dir){
		switch(dir){
			case DIR.UP:
				--loc[DIM.Y];
				break;
			case DIR.DOWN:
				++loc[DIM.Y];
				break;
			case DIR.LEFT:
				--loc[DIM.X];
				break;
			case DIR.RIGHT:
				++loc[DIM.X];
				break;
		}
	}
	
	public void move(Move m){
		loc[DIM.Y] = m.to[DIM.Y];
		loc[DIM.X] = m.to[DIM.X];
	}
	
	public boolean move_backtrace(int dir, int prev_dir) { // will not move in the opposite direction of prev_dir
		if((dir+2)%4 != prev_dir) {
			move(dir);
			return true;
		}
		return false;
	}
	
	public boolean equals(Block b){
		return Arrays.equals(this.size, b.size) && Arrays.equals(this.loc, b.loc);
	}
	
	public int hashCode(){
		int h = 0, p = 23;
		h+=size[0];
		h*=29;
		h+=size[1];
		h*=31;
		h+=loc[0];
		h*=37;
		h+=loc[1];
		return h;
	}
	
	public String toString(){
		return new String(size[0]+" "+size[1]+" "+loc[0]+" "+loc[1]);
	}
}