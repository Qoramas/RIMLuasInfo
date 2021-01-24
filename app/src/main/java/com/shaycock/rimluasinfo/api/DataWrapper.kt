package com.shaycock.rimluasinfo.api

import java.lang.Exception

class DataWrapper<T> (
    var data: T? = null,
    var error: ErrorObject? = null
)

class ErrorObject (exception: Exception)