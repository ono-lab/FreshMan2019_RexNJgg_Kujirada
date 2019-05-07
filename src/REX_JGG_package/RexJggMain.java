package REX_JGG_package;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class RexJggMain {

	public static void main(String[] args)throws IOException {
        int seed = 10;
        int dim = 20;						// ベクトルの次元数n
        int k = (int)(dim/4.0);				// k-tablet関数用パラメータk
        int sizeOfPopulation = 14*dim;		// 集団サイズ
        int noOfOffspring = 5*dim;			// 子個体生成数

        double min = 1.0;					// 初期化領域[-5.0, 5.0]
        double max = 5.0;
        double threshold = 1.0e-7;			// 打ち切り最良評価値
        int maxNoOfEvals = 4* dim * 10000;	// 打ち切り評価回数
        int maxTrials = 3;					// 試行回数


		try {
			for(int trial = 0; trial < maxTrials; ++trial) {
		        // 書き込み用ファイル
		        //String fileName = ".\\RexJggData/no4/" + "RexJggKTable" + "P"+ sizeOfPopulation/dim
		        //							+ "K" + noOfOffspring/dim +  "_" + trial + ".csv";
		        String fileName = ".\\RexJggData/" + "RexJggKTable" + "P"+ sizeOfPopulation/dim
						+ "K" + noOfOffspring/dim +  "_" + trial + ".csv";
		 		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

		 		// インスタンス生成
		        TJgg jgg = new TJgg(new TKtablet(k, dim), noOfOffspring, new Random(seed));

				int generation = 0;							// 世代数
				int evalNum = noOfOffspring * generation;	// 評価回数
		        double bestEvalValue = 0.0;					// 最高評価地


				jgg.initializePopulation(sizeOfPopulation, min, max);	// 初期化
				bestEvalValue = jgg.getBestEvaluation();				// 集団中の最良個体の評価値の取得

				System.out.println("evalNum:" +evalNum +" BestEval:" + bestEvalValue);

				pw.print("evalNum" + ",");
				pw.println("bestEvalValue_" + trial);
				pw.print(evalNum + ",");
				pw.println(bestEvalValue);

				// 打ち切り条件まで繰り返す
				while(evalNum < maxNoOfEvals && bestEvalValue > threshold) {
					jgg.doOneGeneration();
					bestEvalValue =jgg.getBestEvaluation();

					generation++;
					evalNum = noOfOffspring * generation;

					// ファイル書き込み
					if(evalNum % 1000 == 0) {
						System.out.println("evalNum:" +evalNum +" BestEval:" + bestEvalValue);
						pw.print(evalNum + ",");
						pw.println(bestEvalValue);
					}
				}
				pw.close();
				seed++;		// 異なる乱数系列に変更．
			}

		} catch(IOException e) {
			System.out.println(e);
		}

		System.out.println("end");

    }

}
