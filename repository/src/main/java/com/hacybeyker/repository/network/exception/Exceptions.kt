package com.hacybeyker.repository.network.exception

import java.lang.Exception

class GenericException(
    val code: Int=15,
    val title: String="Generic Error",
    val description: String="Ups, has occurred error!") : Exception()