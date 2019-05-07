package REX_JGG_package;

import java.util.Random;

import org.junit.jupiter.api.Test;

class TRexTest {

	@Test
	void test() {
		int seed = 0;
        int k = 3;
        int dim = 20;
        int noOfOffspring = 15*dim;
        int sizeOfPopulation = 14*dim;
        double min = -5.0;
        double max = 5.0;
        int generation = 4*dim*10000;

        TRex fRex = new TRex(new Random(seed));

        TJgg jgg = new TJgg(new TKtablet(k, dim), noOfOffspring, new Random(seed));

        jgg.initializePopulation(sizeOfPopulation, min, max);

		TPopulation children = fRex.makeOffspring(jgg.initializePopulation(sizeOfPopulation, min, max), noOfOffspring);
	}

}
