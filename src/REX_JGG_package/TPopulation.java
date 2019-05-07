// 鯨田
package REX_JGG_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TPopulation {
	private TIndividual[] fArray;

	// コンストラクタ
	public TPopulation() {
		fArray = new TIndividual[0];
	}

	// コピーコンストラクタ
	public TPopulation(TPopulation src) {
		fArray = new TIndividual[src.fArray.length];
		for(int i = 0; i < fArray.length; ++i) {
			fArray[i] = new TIndividual(src.fArray[i]);
		}
	}

	// 自身と同一のオブジェクトを生成
	public TPopulation clone() {
		return new TPopulation(this);
	}

	// 個体数を設定
	public TPopulation setSize(int size) {
		if(fArray.length != size) {
			fArray = new TIndividual[size];
			for(int i = 0; i < size; ++i) {
				fArray[i] = new TIndividual();
			}
		}
		return this;
	}

	// 個体数を返す
	public int getSize() {
		return fArray.length;
	}

	// srcの内容をコピー
	public TPopulation copyFrom(TPopulation src) {
		setSize(src.getSize());
		for(int i = 0; i < getSize(); ++i) {
			fArray[i].copyFrom(src.fArray[i]);
		}
		return this;
	}

	// 内容を文字列へ変換
	public String toString() {
		String str = "Size : " + fArray.length + "\n";
		for(int i = 0; i < fArray.length; ++i ) {
			str += fArray[i].toString() + "\n";
		}
		return str;
	}

	// ファイルに個体数と，各個体の評価値，ベクトル
	public void writeTo(PrintWriter pw) {
		pw.println(fArray.length);
		for(int i = 0; i < fArray.length; ++i) {
			fArray[i].writeTo(pw);
		}
	}

	// ファイルから読み込み，個体数と各個体の情報を設定
	public void readFrom(BufferedReader br) throws IOException {
		int size = Integer.parseInt(br.readLine());
		setSize(size);
		for(int i = 0; i < fArray.length; ++i) {
			fArray[i].readFrom(br);
		}
	}

	// index番目の個体情報を返す
	public TIndividual getIndividual(int index) {
		return fArray[index];
	}

	// 評価値で昇順バブルソート
	public void sortByEvaluationValue() {
		//計算値の小さいもの順に並べる．
		//Arrays.sort(fArray);
		for(int i = 0; i < getSize() - 1; ++i) {
			for(int j = i + 1; j < getSize(); ++j) {
				if(fArray[i].getEvaluationValue() > fArray[j].getEvaluationValue()) {
					TIndividual temp = fArray[i];
					fArray[i] = fArray[j];
					fArray[j] = temp;
				}
			}
		}
	}


}
