# Imports
import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import matplotlib.pyplot as plt
import seaborn as sns
#import matplotlib.pyplot as plt
#%matplotlib inline

# Input data files are available in the "../input/" directory.
# For example, running this (by clicking run or pressing Shift+Enter) will list the files in the input directory

#from subprocess import check_output
#print(check_output(["ls", "C:/Python27/input"]).decode("utf8"))

# Import the train and test set
train = pd.read_csv('C:/Python27/train.csv')
#test = pd.read_csv('C:/Python27/test_kaggle.csv')
train.head()

print('Pre Parse Train: ', len(train))

# Handle missing values
train.isnull().sum()[train.isnull().sum()>0]

train = train.drop(['PoolQC','Fence','MiscFeature','Alley','FireplaceQu','ExterQual','ExterCond','BsmtCond','GarageQual','GarageCond'], axis=1)
#test = test.drop(['PoolQC','Fence','MiscFeature','Alley','FireplaceQu','ExterQual','ExterCond','BsmtCond','GarageQual','GarageCond'], axis=1)

train = train.dropna()
#test = test.dropna()
print('Post Parse\Clean Train', len(train))
#print(train)
corr = train.corr()
plt.figure(figsize=(8,8))
sns.heatmap(corr)
plt.yticks(rotation=0, size=7)
plt.xticks(rotation=90, size=7)

# Select columns with a correlation > 0.5
rel_vars = corr.SalePrice[(corr.SalePrice > 0.5)]
rel_cols = list(rel_vars.index.values)

# Create matrix with independent variables
x = train[rel_cols[:-1]].iloc[:,0:].values
y = train.iloc[:, -1].values

# Create training and test dataset
from sklearn.model_selection import train_test_split
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.1, random_state = 0)

print('FINAL:', len(x_train), len(y_train), len(x_test), len(y_test))

from sklearn import ensemble
clf = ensemble.GradientBoostingRegressor(n_estimators = 400, max_depth = 5, min_samples_split = 2,
          learning_rate = 0.1, loss = 'ls')
clf.fit(x_train, y_train)

score_b = clf.score(x_test,y_test)
print(score_b)

y_pred = clf.predict(x_test)
from sklearn.metrics import mean_squared_log_error, mean_absolute_error

print('MAE:\t$%.2f' % mean_absolute_error(y_test, y_pred))
print('MSLE:\t%.5f' % mean_squared_log_error(y_test, y_pred))

from sklearn.model_selection import cross_val_predict

fig, ax = plt.subplots()
ax.scatter(y_test, y_pred, edgecolors=(0, 0, 0))
ax.plot([y_test.min(), y_test.max()], [y_test.min(), y_test.max()], 'k--', lw=4)
ax.set_xlabel('Actual')
ax.set_ylabel('Predicted')
ax.set_title("Ground Truth vs Predicted")
plt.show()