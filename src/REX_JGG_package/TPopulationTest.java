// 鯨田
package REX_JGG_package;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;
class TPopulationTest {

	@Test
	public void testTPopulation() {
		// デフォルトコンストラクタ
		TPopulation p1 = new TPopulation();
		assertEquals(0, p1.getSize());

		// コピーコンストラクタ
		p1.setSize(5);
		TPopulation p2 = new TPopulation(p1);
		assertEquals(5, p2.getSize());
	}

	@Test
	public void testClone() {
		TPopulation p1 = new TPopulation();

		// 5個体の評価値を設定
		p1.setSize(5);
		for(int i = 0; i < p1.getSize(); ++i) {
			p1.getIndividual(i).setEvaluationValue((double)i);
		}

		// 何も用意していない状態からコピー
		TPopulation p2 = p1.clone();

		// 各評価値の確認
		for(int i = 0; i < p1.getSize(); ++i) {
			assertEquals(p1.getIndividual(i).getEvaluationValue(),
						p2.getIndividual(i).getEvaluationValue());
		}
	}

	@Test
	public void testSetsize() {
		TPopulation p = new TPopulation();
		p.setSize(5);
		assertEquals(p.getSize(), 5);
	}

	@Test
	public void testGetsize() {
		TPopulation p = new TPopulation();
		assertEquals(p.getSize(), 0);
	}

	@Test
	public void testCopyFrom() {
		TPopulation p1 = new TPopulation();
		TPopulation p2 = new TPopulation();

		// 5個体の評価値を設定
		p1.setSize(5);
		for(int i = 0; i < p1.getSize(); ++i) {
			p1.getIndividual(i).setEvaluationValue((double)i);
		}

		// 元あるオブジェクトにコピー
		p2.copyFrom(p1);

		// 各評価値の確認
		for(int i = 0; i < p1.getSize(); ++i) {
			assertEquals(p1.getIndividual(i).getEvaluationValue(),
						p2.getIndividual(i).getEvaluationValue());
		}
	}

	@Test
	public void testToString() {
		TPopulation p = new TPopulation();

		// 2個体の評価値，ベクトルの次元を設定
		p.setSize(2);
		for(int i = 0; i < p.getSize(); ++i) {
			p.getIndividual(i).setEvaluationValue((double)i);
			p.getIndividual(i).getVector().setDimension(2);
			// i番目の個体のベクトル値を設定
			for(int j = 0; j < p.getIndividual(i).getVector().getDimension(); ++j) {
				p.getIndividual(i).getVector().setElement(j, (double) j);
			}
		}
		// 各評価値の確認
		assertEquals(p.toString(), "Size : 2\n0.0\n[0.0,1.0]\n1.0\n[0.0,1.0]\n");
	}

	@Test
	public void testWriteTo() {
		TPopulation p = new TPopulation();

		// 1個体の評価値0，ベクトルの次元2を設定
		p.setSize(1);
		for(int i = 0; i < p.getSize(); ++i) {
			p.getIndividual(i).setEvaluationValue((double)i);
			p.getIndividual(i).getVector().setDimension(2);
			// i番目の個体のベクトル値[0.0 1.0]を設定
			for(int j = 0; j < p.getIndividual(i).getVector().getDimension(); ++j) {
				p.getIndividual(i).getVector().setElement(j, (double) j);
			}
		}

		String filename = ".\\TPopulationTestWriteTo.txt";
		try {
			// ファイル書き込み
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			p.writeTo(pw);
			pw.close();

			// ファイル読み込みのためのBufferedReaderのインスタンス生成
			BufferedReader br = new BufferedReader(new FileReader(filename));

			// 個体数の確認
			assertEquals(Integer.parseInt(br.readLine()), p.getSize());

			for(int i = 0; i < p.getSize(); ++i) {
				// 評価値の確認
				assertEquals(Double.parseDouble(br.readLine()),
							p.getIndividual(i).getEvaluationValue());

				// ベクトル次元の確認
				assertEquals(Integer.parseInt(br.readLine()),
							p.getIndividual(i).getVector().getDimension());

				// i番目の個体のベクトル値の確認(最後に空白が入る)
				assertEquals(br.readLine(), "0.0 1.0 ");
			}
			br.close();

		}catch(FileNotFoundException e) {
			System.out.println(filename + "が見つかりません");
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	@Test
	public void testReadFrom() {
		TPopulation p1 = new TPopulation();

		// 個体の評価値，ベクトルの次元を設定
		p1.setSize(2);
		for(int i = 0; i < p1.getSize(); ++i) {
			p1.getIndividual(i).setEvaluationValue((double)i);
			p1.getIndividual(i).getVector().setDimension(2);

			// i番目の個体のベクトル値[0.0 1.0]を設定
			for(int j = 0; j < p1.getIndividual(i).getVector().getDimension(); ++j) {
				p1.getIndividual(i).getVector().setElement(j, (double) j);
			}
		}

		String filename = ".\\TPopulationTestReadFrom.txt";
		try {
			// ファイル書き込み
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			p1.writeTo(pw);
			pw.close();

			TPopulation p2 = new TPopulation();
			// ファイル読み込み
			BufferedReader br = new BufferedReader(new FileReader(filename));
			p2.readFrom(br);
			br.close();

			// 個体数の確認
			assertEquals(p2.getSize(), p1.getSize());

			for(int i = 0; i < p2.getSize(); ++i) {
				// 評価値の確認
				assertEquals(p2.getIndividual(i).getEvaluationValue(),
							p1.getIndividual(i).getEvaluationValue());

				// ベクトル次元の確認
				assertEquals(p2.getIndividual(i).getVector().getDimension(),
							p1.getIndividual(i).getVector().getDimension());

				// i番目の個体のベクトル値の確認(jはfDataのインデックス)
				for(int j = 0; j < p1.getIndividual(i).getVector().getDimension(); ++j) {
					assertEquals(p2.getIndividual(i).getVector().getElement(j),
								p1.getIndividual(i).getVector().getElement(j));
				}
			}
			br.close();

		}catch(FileNotFoundException e) {
			System.out.println(filename + "が見つかりません");
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	@Test
	public void testGetIndividual() {
		TPopulation p = new TPopulation();
		p.setSize(1);
		p.getIndividual(0).setEvaluationValue(0.0);
		assertEquals(p.getIndividual(0).getEvaluationValue(), 0.0);
	}

	@Test
	public void testSortByEvaluationValue() {
		TPopulation p = new TPopulation();
		p.setSize(5);

		// 降順に設定
		for(int i = 0; i < p.getSize(); ++i) {
			p.getIndividual(i).setEvaluationValue((double)5-i);
		}
		// 昇順ソート
		p.sortByEvaluationValue();

		for(int i = 0; i < p.getSize()-1; ++i) {
			assertTrue(p.getIndividual(i).getEvaluationValue() < p.getIndividual(i+1).getEvaluationValue());
		}
	}

}
