package REX_JGG_package;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TKtabletTest {

	@Test
	void tesEvaluate() {
		// ベクトルの設定
		TVector v = new TVector();
		v.setDimension(5);
		for(int i = 0; i < v.getDimension(); ++i) {
			v.setElement(i, (double)i);
		}

		int k = v.getDimension() / 4;	// k = 1;
		int d = v.getDimension();
		TKtablet kt = new TKtablet(k, d);

		assertEquals(0 + 10000 + 10000*4 + 10000*9 + 10000*16, kt.evaluate(v));
		assertEquals(v.getDimension(), kt.getDimension());
	}

}
