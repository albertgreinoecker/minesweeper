package at.ac.htlinn.minesweeper;

import java.io.File;
import java.io.FileNotFoundException;

public class Runner {

	public static void main(String[] args) throws FileNotFoundException {
		Playground pg = new Playground(20,20,50);
		System.out.println(pg);
		Playground pg2 = new Playground(20, 20, new File("test/field1.txt"));
		System.out.println(pg2);
	}

}
