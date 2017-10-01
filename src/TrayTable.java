import java.util.*;


class TrayTable{
	private ArrayList<LinkedList<Tray>> table;
	private int size;
	
	public TrayTable(int siz){
		size = siz;
		table = new ArrayList<LinkedList<Tray>>(size);
		for(int i = 0; i < size; ++i){
			table.add(null);
		}
	}
	
	public boolean add(Tray tray){
		int chash = tray.hashCode();
		
		if(table.get(chash) == null){
			table.set(chash, new LinkedList<Tray>());
			table.get(chash).addLast(tray);
			return true;
		}

		for(Tray t : table.get(chash)){
			if(t.equals(tray)) return false;
		}
		table.get(chash).addLast(tray);
		return true;
	}
	
}