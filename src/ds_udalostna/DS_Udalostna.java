/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_udalostna;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import sk.uniza.fri.duracik2.dis.generators.ExponentialGenerator;
import sk.uniza.fri.duracik2.dis.generators.IGenerator;
import sk.uniza.fri.duracik2.dis.generators.TriangularGenerator;
import sk.uniza.fri.duracik2.dis.generators.UniformGenerator;

/**
 *
 * @author Unlink
 */
public class DS_Udalostna {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("C:\\Users\\Unlink\\Desktop\\input analyzer\\cisla.txt", "UTF-8");
		//IGenerator g = new TriangularGenerator(10,60,40);
		IGenerator g = new ExponentialGenerator(120);
		for (int i = 0; i < 1000000; i++) {
			writer.println(g.next());
		}
		writer.close();
	}

}
