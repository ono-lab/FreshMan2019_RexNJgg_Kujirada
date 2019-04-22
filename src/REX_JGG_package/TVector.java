package REX_JGG_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TVector {
	private double[] fData;

	// 引数を取らないコンストラクタ
	public TVector() {
		fData = new double[0];
	}

	// コピーコンストラクタ
	public TVector(TVector src) {
		fData = new double[src.fData.length];
		for(int i = 0; i < fData.length; ++i) {
			fData[i] = src.fData[i];
		}
	}

	@Override
	public TVector clone() {
		return new TVector(this);
	}

	// コピーメソッド
	public TVector copyFrom(TVector src) {
		if(fData.length != src.fData.length) {
			fData = new double[src.fData.length];
		}
		for(int i = 0; i < fData.length; ++i) {
			fData[i] = src.fData[i];
		}
		// ポインタを返す
		return this;
	}

	@Override
	public String toString() {
		String str = "[";
		for(int i = 0; i < fData.length; i++) {
			str += fData[i];
			if(i != fData.length -1) {
				str += ",";
			}
		}
		str += "]";
		return str;
	}

	public void writeTo(PrintWriter pw) {
		pw.println(fData.length);
		for(int i = 0; i < fData.length; ++i) {
			pw.print(fData[i] + " ");
		}
		pw.println();
	}

	public void readFrom(BufferedReader br) throws IOException {
		int  dimension = Integer.parseInt(br.readLine());
		if(fData.length != dimension) {
			fData = new double[dimension];
		}
		String line = br.readLine();

		String[] tokens = line.split(" ");
		for(int i = 0; i < dimension; ++i) {
			fData[i] = Double.parseDouble(tokens[i]);
		}
	}

	//getter
	public double getElement(int index) {
		return fData[index];
	}

	// setter
	public void setElement(int index, double x) {
		fData[index] = x;
	}



	public TVector add(TVector v) {
		for(int i = 0; i < fData.length; ++i) {
			fData[i] += v.fData[i];
		}
		return this;
	}



	// ここから----------
	// 差
	public TVector substract(TVector v) {
		for(int i = 0; i < fData.length; ++i) {
			fData[i] -= v.fData[i];
		}
		return this;
	}

	// 内積
	public double innerProduct(TVector v) {
		double sum = 0.0;
		for(int i = 0; i < fData.length; ++i) {
			//fData[i] *= v.fData[i];
			sum += fData[i] * v.fData[i];
		}
		return sum;
	}

	// スカラー積(a倍)
	public TVector scolarProduct(double a) {
		for(int i = 0; i < fData.length; ++i) {
			fData[i] *= a;
		}
		return this;
	}

	// 要素ごとの積
	public TVector elementwiseProduict(TVector v) {
		for(int i = 0; i < fData.length; ++i) {
			fData[i] *= v.fData[i];
		}
		return this;
	}

	// L2ノルム：各次元の値を2乗した和の平方根
	public double getL2Norm() {
		double sum = 0.0;
		for(int i = 0; i < fData.length; ++i) {
			sum += fData[i] * fData[i];
		}
		return Math.sqrt(sum);

	}

	// 正規化：単位ベクトル（X / ベクトル長さ）
	public TVector normalize() {
		double l2Norm = this.getL2Norm();
		for (int i = 0; i < fData.length; ++i) {
			fData[i] /= l2Norm;
		}
		return this;
	}

	// ???????????
	public TVector fill(double a) {
		for(int i = 0; i < fData.length; ++i) {
			fData[i] = a;
		}
		return this;
	}
	// ここまで----------


	public void setDimension(int dim) {
		if(fData.length != dim) {
			fData = new double[dim];
		}
	}

	public int getDimension() {
		return fData.length;
	}

}

