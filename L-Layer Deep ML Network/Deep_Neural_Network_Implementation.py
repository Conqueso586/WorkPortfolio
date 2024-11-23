import numpy as np
import matplotlib.pyplot as plt
import copy

plt.rcParams['figure.figsize'] = (5.0, 4.0)
plt.rcParams['image.interpolation'] = 'nearest'
plt.rcParams['image.cmap'] = 'gray'

np.random.seed(1)


def initialize_parameters_deep(layer_dims):
    np.random.seed(3)
    parameters = {}
    layer_count = len(layer_dims)

    for layerCount in range(1, layer_count):
        parameters['W' + str(layerCount)] = np.random.randn(layer_dims[layerCount], layer_dims[layerCount - 1]) * 0.01
        parameters['b' + str(layerCount)] = np.zeros((layer_dims[layerCount], 1))

        assert (parameters['W' + str(layerCount)].shape == (layer_dims[layerCount], layer_dims[layerCount - 1]))
        assert (parameters['b' + str(layerCount)].shape == (layer_dims[layerCount], 1))

    return parameters


def update_parameters(parameters, gradients, learning_rate):
    new_parameters = copy.deepcopy(parameters)
    parameter_count = len(new_parameters) // 2

    for layers in range(parameter_count):
        new_parameters["W" + str(layers + 1)] = (
                new_parameters["W" + str(layers + 1)] - np.multiply(learning_rate,
                                                                    gradients["dW" + str(layers + 1)]))
        new_parameters["b" + str(layers + 1)] = (
                new_parameters["b" + str(layers + 1)] - np.multiply(learning_rate,
                                                                    gradients["db" + str(layers + 1)]))

    return new_parameters


def sigmoid(linear_output):
    activation_cache = linear_output
    activation_output = np.divide(1, 1 + np.exp(-linear_output))
    return activation_output, activation_cache


def relu(linear_output):
    activation_cache = linear_output
    activation_output = np.max(0, linear_output)
    return activation_output, activation_cache


def l_model_forward(x_input, parameters):
    caches = []
    activation_output = x_input
    parameter_count = len(parameters) // 2
    for layers in range(1, parameter_count):
        previous_activation = activation_output
        activation_output, cache = (
            linear_activation_forward(previous_activation,
                                      parameters["W" + str(layers)],
                                      parameters["b" + str(layers)], activation="relu"))
        caches.append(cache)
    linear_activation_output, cache = (
        linear_activation_forward(activation_output,
                                  parameters["W" + str(parameter_count)],
                                  parameters['b' + str(parameter_count)], activation="sigmoid"))
    caches.append(cache)

    return linear_activation_output, caches


def linear_forward(activation_output, weights, bias):
    linear_output = np.dot(weights, activation_output) + bias
    cache = (activation_output, weights, bias)

    return linear_output, cache


def linear_activation_forward(previous_activation, weights, bias, activation):
    linear_cache = None
    activation_cache = None
    activation_output = None
    if activation == "sigmoid":
        linear_output, linear_cache = linear_forward(previous_activation, weights, bias)
        activation_output, activation_cache = sigmoid(linear_output)

    elif activation == "relu":
        linear_output, linear_cache = linear_forward(previous_activation, weights, bias)
        activation_output, activation_cache = relu(linear_output)
    cache = (linear_cache, activation_cache)

    return activation_output, cache


def compute_cost(linear_activation_output, ground_truth_labels):
    label_count = ground_truth_labels.shape[1]

    cost = np.multiply((-1 / label_count), np.sum(np.multiply(ground_truth_labels,
                                                              np.log(linear_activation_output)) +
                                                  np.multiply((1 - ground_truth_labels),
                                                              np.log(1 - linear_activation_output))))

    cost = np.squeeze(cost)
    return cost


def l_model_backward(last_layer_activation, ground_truth_labels, caches):
    gradients = {}
    cache_count = len(caches)
    ground_truth_labels = ground_truth_labels.reshape(last_layer_activation.shape)

    last_layer_activation_derivative = - (np.divide(ground_truth_labels,
                                                    last_layer_activation) - np.divide(1 - ground_truth_labels,
                                                                                       1 - last_layer_activation))

    current_cache = caches[cache_count - 1]
    (gradients["dA" + str(cache_count - 1)],
     gradients["dW" + str(cache_count)],
     gradients["db" + str(cache_count)]) = (
        linear_activation_backward(last_layer_activation_derivative, current_cache, activation="sigmoid"))

    # Loop from l=L-2 to l=0
    for layer in reversed(range(cache_count - 1)):
        current_cache = caches[layer]
        gradients["dA" + str(layer)], gradients["dW" + str(layer + 1)], gradients["db" + str(layer + 1)] = (
            linear_activation_backward(gradients["dA" + str(layer)], current_cache, activation="relu"))

    return gradients


def linear_backward(linear_derivative, cache):
    previous_activation, weights, bias = cache
    activation_element_count = previous_activation.shape[1]

    weight_derivative = np.multiply((1 / activation_element_count), np.dot(linear_derivative, previous_activation.T))
    bias_derivative = np.multiply((1 / activation_element_count), np.sum(linear_derivative, axis=1, keepdims=True))
    previous_activation_derivative = np.dot(weights.T, linear_derivative)

    return previous_activation_derivative, weight_derivative, bias_derivative


def linear_activation_backward(activation_derivative, cache, activation):
    linear_cache, activation_cache = cache
    previous_activation_derivative = None
    weight_derivative = None
    bias_derivative = None
    if activation == "relu":
        linear_output_derivative = relu_backward(activation_derivative, activation_cache)
        previous_activation_derivative, weight_derivative, bias_derivative = (
            linear_backward(linear_output_derivative, linear_cache))

    elif activation == "sigmoid":
        linear_output_derivative = sigmoid_backward(activation_derivative, activation_cache)
        previous_activation_derivative, weight_derivative, bias_derivative = (
            linear_backward(linear_output_derivative, linear_cache))

    return previous_activation_derivative, weight_derivative, bias_derivative


def sigmoid_backward(activation_derivative, activation_cache):
    linear_output = activation_cache

    activation_output = float(sigmoid(linear_output))
    linear_output_derivative = (
        np.multiply(activation_derivative,
                    np.multiply(activation_output, (1 - activation_output))))
    return linear_output_derivative


def relu_backward(activation_derivative, activation_cache):
    linear_output = activation_cache
    if linear_output < 0:
        activation_output = 0
    else:
        activation_output = 1
    linear_output_derivative = np.multiply(activation_derivative, activation_output)
    return linear_output_derivative
