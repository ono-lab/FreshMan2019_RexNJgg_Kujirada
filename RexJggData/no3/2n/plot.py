import matplotlib.pyplot as plt
import pandas as pd
 
df = pd.DataFrame.from_csv('RexJggKTableP14K2_no3_2n.csv', header=1);
print(df)
df.plot(legend=False);
 
plt.xlim(0, 80 * (10**4));
plt.ylim(10**(-8), 10**4);
plt.xlabel("Number of Evals");
plt.ylabel("Best Evaluation Value");
plt.yscale('log')
plt.savefig('no3_2n.pdf');
plt.show();
plt.close();
