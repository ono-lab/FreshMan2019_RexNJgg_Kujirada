package REX_JGG_package;

import java.util.Random;

public class TRex {
	private Random fRandom;

	public TRex(Random random) {
		fRandom = random;
	}

	// 子個体の生成
	// parents : 親個体
	// noOfOffspring : 生成子個体数
	public TPopulation makeOffspring(TPopulation parents, int noOfOffspring) {

		int parentsSize = parents.getSize();	// 親の個体数
		int dimension = parents.getIndividual(0).getVector().getDimension();	// 個体の次元数

		// 重心<y>の生成
		TVector yMean = new TVector();
		yMean.setDimension(dimension);

		// <y>を0に初期化
		yMean.fill(0.0);

		// <y>の計算
		for(int i = 0; i < parentsSize; ++i) {
			yMean.add(parents.getIndividual(i).getVector());
		}
		yMean.scalarProduct(1.0 /(double)parentsSize);

		// σの計算
		double sigma = Math.sqrt(1.0 /(double) (parentsSize - 1));

		// 子個体の生成
		TPopulation children = new TPopulation();
		children.setSize(noOfOffspring);

		TVector temp = new TVector();

		// 子個体の実数ベクトルの計算
		for (int i = 0; i < noOfOffspring; ++i) {
			// x_i = <y>
			children.getIndividual(i).getVector().copyFrom(yMean);

			for(int j = 0; j < parentsSize; ++j) {
				// nextGaussian()：平均0.0、標準偏差1.0で乱数を取り出す
				//double epsilon = sigma * sigma * fRandom.nextGaussian();
				double epsilon = sigma * fRandom.nextGaussian();

				// y_iを計算用にセット
				temp = parents.getIndividual(j).getVector().clone();
				//temp.copyFrom(parents.getIndividual(j).getVector()); ←だめ．

				// y_i - <y>
				temp.substract(yMean);

				//ε(y_i - <y>)
				temp.scalarProduct(epsilon);

				// <y> + ε(y_i - <y>)
				children.getIndividual(i).getVector().add(temp);
			}
		}

		return children;
	}

}
