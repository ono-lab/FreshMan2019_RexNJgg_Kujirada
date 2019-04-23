/**
 *
 */
package REX_JGG_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TIndividualTest {

	private static void TIndividualTest() {
		TIndividual id1 = new TIndividual();
		System.out.println("id1 = new TIndividual():" + id1);

		//-----v1の設定-----
		TVector v1 = id1.getVector();
		System.out.println("id1.getVector():"+v1);

		v1.setDimension(3);
		for(int i = 0; i < v1.getDimension(); ++i) {
			v1.setElement(i, (double)i);
		}
		System.out.println("v1.setElement:"+v1);
		//------------------

		id1.setEvaluationValue(1.0);
		System.out.println("id1.setEvaluationValue(1.0):");
		System.out.println("id1.getEvaluationValue():" + id1.getEvaluationValue());
		System.out.println("id1:" + id1);


		TIndividual id2 = new TIndividual(id1);
		TIndividual id3 = id1.clone();
		System.out.println("id2 = new TIndividual(id1):" + id2);
		System.out.println("id3 = id1.clone():" + id3);

		TIndividual id4 = new TIndividual();
		System.out.println("id4.copyFrom(id1):" + id4.copyFrom(id1));


		//---------------------------------------------------------------
		System.out.println("//---------------------------------------------------------------");
		System.out.println("id1.toString():" + id1.toString());


		//-----id1の再設定-----
		v1.setDimension(2);
		for(int i = 0; i < v1.getDimension(); ++i) {
			v1.setElement(i, (double)i*100);
		}
		id1.setEvaluationValue(10000.0);
		System.out.println("v1.setElement:"+v1);
		//------------------


		String filename = ".\\TIndividualTest.txt";
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			id1.writeTo(pw);
			pw.close();
			System.out.println("id1.writeTo("+filename+"):");

			BufferedReader br = new BufferedReader(new FileReader(filename));
			id2.readFrom(br);
			br.close();
			System.out.println("id2.readFrom("+filename+"):"+id2);
		}catch(FileNotFoundException e) {
			System.out.println(filename + "が見つかりません");
		}catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("//---------------------------------------------------------------");
		//---------------------------------------------------------------

	}


	public static void main(String[] args) {
		TIndividualTest();

	}

}
