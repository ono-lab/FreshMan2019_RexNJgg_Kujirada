package REX_JGG_package;

import java.util.Random;

public class TJgg {
	private Random fRandom;					// 乱数生成器
	private TKtablet fFunction;				// Ktablet関数の評価値
	private TRex fRex;						// 子個体生成器
	private TPopulation fPopulation;		// 集団
	private int fNoOfOffspring;			// 子個体生成数


	public TJgg(TKtablet func, int noOfOffspring, Random random) {
		fFunction = func;
		fNoOfOffspring = noOfOffspring;
		fRandom = random;
		fRex = new TRex(fRandom);
		fPopulation = new TPopulation();
	}

	public TPopulation initializePopulation(int size, double max, double min) {
		// fPopulationのサイズ設定
		fPopulation.setSize(size);

		for(int i = 0; i < fPopulation.getSize(); ++i) {
			// vの設定
			TVector v = new TVector();
			v = fPopulation.getIndividual(i).getVector();
			v.setDimension(fFunction.getDimension());

			for(int j = 0; j < v.getDimension(); ++j) {
				// 個体iのベクトルの[min. max]の一様乱数で初期化
				// double d = r.nextDouble();      //0.0～1.0の乱数を取得する
				double randomValue = fRandom.nextDouble() * (max - min) + min;
				v.setElement(j, randomValue);
			}
			// 個体iの評価値をfFunctionを用いて計算し，各個体を設定
			double evaluationValue = fFunction.evaluate(v);
			fPopulation.getIndividual(i).setEvaluationValue(evaluationValue);
		}
		return fPopulation;
	}

	public void doOneGeneration() {
		// 1. 複製に用いる親個体の選択

		// 選択する親個体
		TPopulation parents = new TPopulation();
		parents.setSize(fFunction.getDimension() + 1);

		// 選択された親個体の番号
		int[] fNoOfSelParents = new int[fPopulation.getSize()];

		// n+1個の親個体の選択
		for(int i = 0; i < parents.getSize(); ++i) {
			// 選択した元集団の親個体のインデックスを保存
			int index = fRandom.nextInt(fPopulation.getSize());
			fNoOfSelParents[i] = index;
			// 親個体の選択
			parents.getIndividual(i).copyFrom(fPopulation.getIndividual(index));
		}


		// 2. 子個体の生成
		TPopulation children = fRex.makeOffspring(parents, fNoOfOffspring);
		//System.out.println(children.getIndividual(0).getEvaluationValue());


		// 3. 子個体の評価
		for(int i = 0; i < children.getSize(); ++i) {
			// 評価値の取得
			TVector childrenVector = children.getIndividual(i).getVector();
			double evaluationValue = fFunction.evaluate(childrenVector);

			//System.out.println(evaluationValue);

			// 評価値のセット
			children.getIndividual(i).setEvaluationValue(evaluationValue);
			//System.out.println(children.getIndividual(i).getEvaluationValue());
		}


		// 4. 複製選択
		// fNoOfSelParents have information of parents who joined crossover

		// 子個体のソート
		children.sortByEvaluationValue();

		// 親と子を入れ替え
		for(int i = 0; i < parents.getSize(); ++i) {
			fPopulation.getIndividual(fNoOfSelParents[i]).copyFrom(children.getIndividual(i));
		}


	}

	// 集団中の最良個体の評価値を返す
	public double getBestEvaluation() {
		fPopulation.sortByEvaluationValue();
		return fPopulation.getIndividual(0).getEvaluationValue();
	}

	// 集団中の最良個体を返す
	public TIndividual getBestIndividual() {
		fPopulation.sortByEvaluationValue();
		return fPopulation.getIndividual(0);
	}

}
