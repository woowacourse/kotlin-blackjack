package model

sealed class InputState<out T> {
    class Success<out T>(val input: T) : InputState<T>()
    class Error(val error: String) : InputState<Nothing>()
}
