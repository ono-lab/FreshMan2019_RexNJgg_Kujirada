/**
 *
 */
package REX_JGG_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class TIndividual {
	private TVector fVector;
	private double fEvaluationValue;

	// コンストラクタ
	public TIndividual() {
		fVector = new TVector();
		fEvaluationValue = Double.NaN;
	}

	public TIndividual(TIndividual src) {
		fVector = new TVector(src.fVector);
		fEvaluationValue = src.fEvaluationValue;
	}

	@Override
	public TIndividual clone() {
		return new TIndividual(this);
	}

	public TIndividual copyFrom(TIndividual src) {
		fVector.copyFrom(src.fVector);
		fEvaluationValue = src.fEvaluationValue;
		return this;
	}

	public String toString() {
		String str = fEvaluationValue + "\n";
		str += fVector.toString();
		return str;
	}

	public void writeTo(PrintWriter pw) {
		pw.println(fEvaluationValue);
		fVector.writeTo(pw);
	}

	public void readFrom(BufferedReader br) throws IOException{
		fEvaluationValue = Double.parseDouble(br.readLine());
		fVector.readFrom(br);
	}

	public void setEvaluationValue(double eval) {
		fEvaluationValue = eval;
	}

	public double getEvaluationValue() {
		return fEvaluationValue;
	}

	public TVector getVector() {
		return fVector;
	}


}
