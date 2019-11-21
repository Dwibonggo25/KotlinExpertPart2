package com.example.dicodingexpert2.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dicodingexpert2.utils.Result
import retrofit2.Response

open class BaseViewmodel : ViewModel() {
    suspend fun <T> getApiResult(call: suspend () -> Response<T>): Result<T> {
        try {

            val response = call()
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    return Result.Success(body)
                }
            }
            return Result.Erorr("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return Result.Erorr(e.message ?: e.toString())
        }
    }
}