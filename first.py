import tensorflow as tf
from tensorflow import keras
import numpy as np

# Example dataset
xs = np.array([-1.0, 0.0, 1.0, 2.0, 3.0, 4.0], dtype=float)
ys = np.array([-3.0, -1.0, 1.0, 3.0, 5.0, 7.0], dtype=float)

# Building a model
model = keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])
model.compile(optimizer='sgd', loss='mean_squared_error')
model.fit(xs, ys, epochs=100)

# Save the model
# model.save("E:/springAI/linear_model")
model.export('linear_model')
# tf.saved_model.save(model,"E:/springAI/linear_model")
