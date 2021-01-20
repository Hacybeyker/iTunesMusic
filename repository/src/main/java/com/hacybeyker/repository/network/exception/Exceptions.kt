package com.hacybeyker.repository.network.exception

class GenericException(
    val code: Int = 123,
    val title: String = "Generic Error",
    val description: String = "Ups, has occurred error!"
) : Exception()


class EmptyError(
    val code: Int = 456,
    val title: String = "Music not found",
    val description: String = "Ups, music not found!"
) : Exception()

class NetworkException(
    val code: Int = 789,
    val title: String = "Network Exception",
    val description: String = "Ups, has occurred error!"
) : Exception()