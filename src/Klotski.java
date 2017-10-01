import java.util.*;
//import java.time.*;
import java.io.*;

public class Klotski{
	public static boolean DEBUG = false;
	public static boolean TRAYPRINT = false;
	public static boolean TREEMODE = false;
	public static boolean TIME = false;
	
	public static void  main(String[] args){
		long start = System.currentTimeMillis();
		//ZonedDateTime st = ZonedDateTime.now();
		
		if(args.length != 2 && args.length != 3){
			System.out.println("Klotski Solver usage: java Klotski file.tray file.goal [--debug] "+args.length);
			return;
		}
		if(args.length == 3) {
			if(args[2].indexOf('t') != -1) TRAYPRINT = true;
			if(args[2].indexOf('d') != -1) DEBUG = true;
			if(args[2].indexOf('r') != -1) TREEMODE = true;
			if(args[2].indexOf('i') != -1) TIME = true;
		}
		//if(TIME) System.out.println("Start time: "+st);
		Tray tray;
		try{
			tray = new Tray(new Scanner(new File(args[0])), new Scanner(new File(args[1]))); // read .tray and .goal file data into a new Tray
		} catch (FileNotFoundException e){return;}
		
		if(TRAYPRINT){
			System.out.println(tray.toString());
			System.out.println(tray.goal.toString());
			System.out.println("");
			System.out.println(tray.draw());
		}

		Solver s = new Solver(tray, DEBUG, TREEMODE);
		if(TRAYPRINT) s.print_trays();
		else s.print_moves();
		long end = System.currentTimeMillis();
		//ZonedDateTime en = ZonedDateTime.now();
		if(TIME) System.out.print("Execution time: "+(end-start)+" ms");
		//if(TIME) System.out.println("END time: "+en);
	}
}
