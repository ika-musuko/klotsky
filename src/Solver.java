import java.util.*;

class Solver {
	private LinkedList<TrayElement> moves;
	private TrayTable table;
	private Tray start;
	public boolean debug;
	public boolean tree_mode;
	

	
	public Solver(Tray tray){
		debug = false;
		tree_mode = false;
		start = tray;
		table = new TrayTable(start.MAXHASH);
		moves = this.solve();

	}
	
	public Solver(Tray tray, boolean d){
		debug = d;
		tree_mode = true;
		start = tray;
		table = new TrayTable(start.MAXHASH);
		moves = new LinkedList<>();
		this.solve();

	}
	
	public Solver(Tray tray, boolean d, boolean tr){
		debug = d;
		tree_mode = tr;
		start = tray;
		table = new TrayTable(start.MAXHASH);
		moves = new LinkedList<>();
		this.solve();

		
	}
	
	public void print_moves(){
		for(TrayElement te : moves){
			System.out.println(te.move.toString());
		}
	}

	public void print_trays(){
		for(TrayElement te : moves){
			System.out.println(te.toString());
		}
	}
	
	public LinkedList<TrayElement> to_ancestor(TrayElement te){
		LinkedList<TrayElement> m = new LinkedList<>();
		Move blank = new Move(-1, -1, -1, -1);
		while(te != null){
			if(!te.move.equals(blank)) m.addFirst(te);
			te = te.parent;
		}
		return m;	
	}
	
	public LinkedList<TrayElement> solve(){
		Vector<Move> possible;
		TrayElement cc;
		TrayQueue q = new TrayQueue(); // current children
		q.push(new TrayElement(start));
		while(!q.empty()){
			// get current child
			cc = q.pop();
			if(debug) System.out.println("current tray: "+cc.tray.hashCode()+"\n"+cc.tray.draw());
			
			// generate possible trays
			possible = cc.tray.possible();
			int sol = generate_trays(cc, possible);
			
			// solution found
			if(sol != -1) return moves = to_ancestor(cc.children.get(sol));
			
			// push new children
			for(TrayElement child : cc.children){
				if(debug) System.out.println("new child of: "+cc.tray.hashCode()+"\t"+child.tray.hashCode());
				q.push(child);
			}
		}
		return moves; // no possible moves; 
	}
	
	public int generate_trays(TrayElement te, Vector<Move> moves){
		//if(debug) System.out.println("generating for:\n"+te.tray.draw());
		te.children = new Vector<TrayElement>();
		Tray ctray;
		for(int i = 0; i < moves.size(); ++i){
			//if(debug) System.out.println("Move::: "+moves.get(i).toString());
			ctray = new Tray(te.tray, moves.get(i));
			if(table.add(ctray)){
				te.children.addElement(new TrayElement(ctray, moves.get(i), te));
				if(te.children.get(te.children.size()-1).tray.solved()){
					return te.children.size()-1;
				}
			}
		}
		return -1;
	}
	
}