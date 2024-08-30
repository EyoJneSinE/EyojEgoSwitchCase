package com.eniskaner.eyojegoswitchcase.data.remote.util

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}
