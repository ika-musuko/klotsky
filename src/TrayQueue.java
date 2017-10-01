import java.util.*;
class TrayQueue{
	private Vector<TrayElement> tr;
	private int size;
	
	public TrayQueue(){
		tr = new Vector<TrayElement> ();
		size = 0;
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	public void push(TrayElement te){
		tr.addElement(te);
		++size;
	}
	
	public TrayElement pop(){
		if(size == 0) return null;
		--size;
		return tr.remove(0);
	}
	
	public TrayElement front(){
		return tr.get(0);
	}
}