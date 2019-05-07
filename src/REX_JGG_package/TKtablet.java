package REX_JGG_package;

public class TKtablet {
	private int fk;
	private int fdim;

	public TKtablet(int k, int dim) {
		// fk = x.getDimension() / 4
		// fk = n/4
		fk = k;
		fdim = dim;
	}

	public double evaluate(TVector x) {
		double eval = 0.0;

		for(int i = 0; i < fk; ++i) {
			eval += x.getElement(i) * x.getElement(i);
		}
		for(int i = fk; i < x.getDimension(); ++i) {
			eval += 10000.0 * x.getElement(i) * x.getElement(i);
		}

		return eval;

	}

	public int getDimension() {
		return fdim;
	}

}
