package com.example.foody.utils

sealed class NetworkResult <T> (var data :T ?=null , val message :String ?=null ) {

    class Success<T>(data :T ) : NetworkResult<T>(data)
    class Error<T>(message: String? , data: T? =null ) : NetworkResult<T>(data=data , message = message)
    class Loading<T> : NetworkResult<T>()

}