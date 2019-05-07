// 鯨田連也

package REX_JGG_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TVectorTest {

	private static void TVectorTest() {
		// オブジェクト生成
		TVector v1 = new TVector();
		System.out.println("v1 = new TVector():"+v1);

		v1.setDimension(3);
		System.out.println("v1.setDimension(3)");
		System.out.println("v1:"+v1+", v1.getDimension():"+v1.getDimension());

		System.out.println("v1:"+v1);
		TVector v2 = new TVector(v1);
		TVector v3 = v1.clone();
		System.out.println("v2 = new TVector(v1):"+v2);
		System.out.println("v3 = v1.clone():"+v3);


		for(int i = 0; i < v1.getDimension(); ++i) {
			v1.setElement(i, (double)i);
		}
		System.out.println("v1.setElement:"+v1);

		for(int i = 0; i < v1.getDimension(); ++i) {
			System.out.println("v1.getElement("+i+"):"+v1.getElement(i));
		}

		v2.copyFrom(v1);
		v3.copyFrom(v1);
		System.out.println("v2.copyFrom(v1):"+v2);
		System.out.println("v3.copyFrom(v1):"+v3);



		//---------------------------------------------------------------
		System.out.println("//---------------------------------------------------------------");
		System.out.println("v1.toString():" + v1.toString());


		String filename = ".\\TVectorTest.txt";
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			v1.writeTo(pw);
			pw.close();
			v1.setDimension(3);
			System.out.println("v1.writeTo("+filename+"); v1.setDimension(3):");
			System.out.println("v1:"+v1);

			System.out.println("v1の次元を変更");
			v1.setDimension(5);
			System.out.println("v1.writeTo("+filename+"); v1.setDimension(5):");
			System.out.println("v1:"+v1);

			BufferedReader br = new BufferedReader(new FileReader(filename));
			v1.readFrom(br);
			br.close();
			System.out.println("v1.readFrom("+filename+"); v1:"+v1);
		}catch(FileNotFoundException e) {
			System.out.println(filename + "が見つかりません");
		}catch(IOException e) {
			System.out.println(e);
		}

		System.out.println("//---------------------------------------------------------------");
		//---------------------------------------------------------------


		v1.add(v2);
		System.out.println("v1.add(v2):"+v1);

		v1.substract(v2);
		System.out.println("v1.subtract(v2):"+v1);


		System.out.println("v1.innerProduct(v2):"+v1.innerProduct(v2));

		v1.scalarProduct(3.0);
		System.out.println("v1.scalerProduct(3.0):"+v1);

		v1.elementwiseProduct(v3);
		System.out.println("v1.elementwiseProduct(v3):"+v1);

		System.out.println("v1.getL2Norm():"+(v1.getL2Norm()));
		double l2Norm = Math.sqrt(0.0*0.0+3.0*3.0+0.3*0.3+12.0*12.0);
		System.out.println("answer:" + l2Norm);

		System.out.println("v1.normalize():"+v1.normalize());
		System.out.println("answer:" + 12.0/l2Norm);

		System.out.println("v1.fill(100):"+v1.fill(100));


	}


	public static void main(String[] args) {
		TVectorTest();

	}

}
