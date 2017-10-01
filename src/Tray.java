import java.util.Arrays;
import java.util.*;

class Tray {
	public Vector<Block> blocks;
	public Vector<Block> goal;
	public int hash;
	private int[] size;
	public final int MAXHASH = 1000000;
	public static int _tot = 0;
	public int num;
	
	public Tray(Scanner stray, Scanner sgoal){ // read tray from file
		goal = new Vector<Block>();
		while(sgoal.hasNextInt()){
			goal.addElement(new Block(new int[] {sgoal.nextInt(), sgoal.nextInt()}, new int[] {sgoal.nextInt(), sgoal.nextInt()}));
		}
		size = new int[]{stray.nextInt(), stray.nextInt()};
		blocks = new Vector<Block>();
		while(stray.hasNextInt()){
			blocks.addElement(new Block(new int[] {stray.nextInt(), stray.nextInt()}, new int[] {stray.nextInt(), stray.nextInt()}));
		}
		//this.hash = this.hashCode();
	}
	
	public Tray(Tray ctray){
		this.blocks = new Vector<Block>();
		for(Block b : ctray.blocks){
			this.blocks.addElement(new Block(b));
		}
		this.goal = ctray.goal;
		this.size = ctray.size;
		this.num = ++_tot;
		//this.hash = this.hashCode();
	}
	
	// initialize new tray with move m
	public Tray(Tray ctray, Move m){
		this.blocks = new Vector<Block>();
		for(Block b : ctray.blocks){
			this.blocks.addElement(new Block(b));
			Block tb = this.blocks.get(blocks.size()-1);
			if(tb.loc[0] == m.from[0] && tb.loc[1] == m.from[1]){
				this.blocks.set(blocks.size()-1, new Block(tb.size, m.to));
			}
		}
		this.goal = ctray.goal;
		this.size = ctray.size;
		this.num = ++_tot;		
		//this.hash = this.hashCode();
	}
	
	public boolean equals(Tray t){
		for(int i = 0; i < blocks.size(); ++i){
			if(!this.blocks.elementAt(i).equals(t.blocks.elementAt(i))) return false;
		}
		return true;
	}
	
	// there is probably a O(<n) way to do this where you check the surrounding blocks only but this O(n) way is easier to implement (if this is too slow, implementing the surroundingblock way will be better (potentially requires a lot of refactoring in the data representations)) 
	public boolean can_move(int move, int dir){
		
		if (move >= blocks.size()) return false; // bounds checking
		Tray test = new Tray(this);
		Block mb = test.blocks.get(move);
		test.blocks.get(move).move(dir);
		if(mb.loc[0] < 0 || mb.loc[1] < 0 || mb.loc[0]+mb.size[0] > test.size[0] || mb.loc[1]+mb.size[1] > test.size[1] ) return false; // bounds checking for block
		int[][] tr = new int[test.size[DIM.Y]][test.size[DIM.X]];
		int i = 1;
		for(Block b : test.blocks){
			for(int y = b.loc[DIM.Y]; y < b.size[DIM.Y]+b.loc[DIM.Y]; ++y){
				for(int x = b.loc[DIM.X]; x < b.size[DIM.X]+b.loc[DIM.X]; ++x){
					tr[y][x] += i;
					if(tr[y][x] != i) return false; // collision
				}
			}
			++i;
		}
		return true;
	}
	
	public Vector<Move> possible(){
		Vector<Move> p = new Vector<Move>();
		for(int b = 0; b < blocks.size(); ++b){
			for(int dir = 0; dir < 4; ++dir){
				if(can_move(b, dir)){
					Block bl = blocks.get(b);
					int dy = dir == DIR.UP ? -1 : (dir == DIR.DOWN ? 1 : 0);
					int dx = dir == DIR.LEFT ? -1 : (dir == DIR.RIGHT ? 1 : 0);
					Move m = new Move(bl.loc[DIM.Y], bl.loc[DIM.X], bl.loc[DIM.Y]+dy, bl.loc[DIM.X]+dx);
					p.addElement(m);
				}
			}
		}
		return p;
	}
	
	
	public boolean solved(){
		boolean found = false;
		for(Block g : goal){
			found = false;
			for(Block b : blocks){
				if(g.equals(b)){
					found = true;
					break;
				}
			}
			if(!found) return false;
		}
		return true;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(size[0]+" "+size[1]+"\n");
		for(int i = 0; i < blocks.size(); ++i){
			sb.append(blocks.elementAt(i).toString()+"\n");
		}
		return sb.toString();
	}
	
	public String draw(){
		StringBuilder sb = new StringBuilder();
		int[][] tr = new int[size[DIM.Y]][size[DIM.X]];
		int i = 1;
		for(Block b : blocks){
			for(int y = b.loc[DIM.Y]; y < b.size[DIM.Y]+b.loc[DIM.Y]; ++y){
				for(int x = b.loc[DIM.X]; x < b.size[DIM.X]+b.loc[DIM.X]; ++x){
					tr[y][x] += i;
				}
			}
			++i;
		}
		for(int j = 0; j < tr.length; ++j){
			sb.append(Arrays.toString(tr[j])+"\n");
		}
		return sb.toString();
	}
	
	
	public int hashCode(){
		int h = 0, p = 41;
		
		for(Block b : blocks){
			h *= p;
			h += b.hashCode();
			h %= MAXHASH;
		}
		return h;
		
	}
}