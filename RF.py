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
#print('Pre Parse Train: ', len(test))

# Handle missing values
train.isnull().sum()[train.isnull().sum()>0]

train = train.drop(['PoolQC','Fence','MiscFeature','Alley','FireplaceQu','ExterQual','ExterCond','BsmtCond','GarageQual','GarageCond'], axis=1)
#test = test.drop(['PoolQC','Fence','MiscFeature','Alley','FireplaceQu','ExterQual','ExterCond','BsmtCond','GarageQual','GarageCond'], axis=1)

train = train.dropna()
#test = test.dropna()

print('Post Parse\Clean Train', len(train))
#print('Post Parse\Clean Train', len(test))

#print(train)
corr = train.corr()
plt.figure(figsize=(8,8))
sns.heatmap(corr)
plt.yticks(rotation=0, size=7)
plt.xticks(rotation=90, size=7)
plt.show()

# Select columns with a correlation > 0.5
rel_vars = corr.SalePrice[(corr.SalePrice > 0.5)]
rel_cols = list(rel_vars.index.values)

corr2 = train[rel_cols].corr()
plt.figure(figsize=(8,8))
hm = sns.heatmap(corr2, annot=True, annot_kws={'size':10})
plt.yticks(rotation=0, size=10)
plt.xticks(rotation=90, size=10)
plt.show()


# Create matrix with independent variables
X = train[rel_cols[:-1]].iloc[:,0:].values
y = train.iloc[:, -1].values

# Create training and test dataset
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.1, random_state = 0)

print('FINAL:', len(X_train), len(y_train), len(X_test), len(y_test))

# Fit Random Forest on Training Set
from sklearn.ensemble import RandomForestRegressor
regressor = RandomForestRegressor(n_estimators=300, random_state=0)
regressor.fit(X_train, y_train)

# Score model
score = regressor.score(X_train, y_train)
print(score)

# Predict new result
y_pred = regressor.predict(X_test)
# Plot y_test vs y_pred
plt.figure(figsize=(12,8))
plt.plot(y_test, color='red')
plt.plot(y_pred, color='blue')
plt.show()

# Build a plot
plt.scatter(y_pred, y_test)
plt.xlabel('Prediction')
plt.ylabel('Real value')

# Now add the perfect prediction line
diagonal = np.linspace(0, np.max(y_test), 100)
plt.plot(diagonal, diagonal, '-r')
plt.show()

from sklearn.metrics import mean_squared_log_error, mean_absolute_error

print('MAE:\t$%.2f' % mean_absolute_error(y_test, y_pred))
print('MSLE:\t%.5f' % mean_squared_log_error(y_test, y_pred))



