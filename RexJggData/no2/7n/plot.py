import matplotlib.pyplot as plt
import pandas as pd
 
df = pd.DataFrame.from_csv('RexJggKTableP7K5_no2_7n.csv', header=1);
print(df)
df.plot(legend=False);
 
plt.xlim(0, 80 * (10**4));
plt.ylim(10**(-8), 10**8);
plt.xlabel("Number of Evals");
plt.ylabel("Best Evaluation Value");
plt.yscale('log')
plt.savefig('no2_7n.pdf');
plt.show();
plt.close();
