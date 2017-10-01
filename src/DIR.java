// the order of these directions is the order in which the directions will be tried
// change the numbers to change the order (future: implement an optimisation which would figure out the optimal order of directions to try)

// future: refactor this so DIR.opp works for any order of directions

class DIR {
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	
	public int opp(int dir){
		return (dir+2)%4;
	}
}