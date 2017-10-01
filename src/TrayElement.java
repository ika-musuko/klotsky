import java.util.*;

class TrayElement{
	public Tray tray;
	public Move move;
	public TrayElement parent;
	public Vector<TrayElement> children;
	
	TrayElement(Tray t){
		tray = t;
		move = new Move(-1, -1, -1, -1);
		parent = null;
	}
	
	TrayElement(Tray t, Move m){
		tray = t;
		move = m;
	}
	
	TrayElement(Tray t, Move m, TrayElement p){
		tray = t;
		move = m;
		parent = p;
	}
	
	public String toString(){
		return new String(move.toString()+"\n\n"+tray.draw());
	}
};