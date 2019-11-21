package com.example.dicodingexpert2.utils

sealed class Result<out T> {
    data class Success<out T >(val data: T) : Result<T>()
    data class Erorr<out T>(val message: String) : Result<T>()
    class Loading <out T>(data: T? = null) : Result<T>()
}