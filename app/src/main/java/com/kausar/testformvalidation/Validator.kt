package com.kausar.testformvalidation


private const val EMAIL_MESSAGE = "invalid email address"
private const val REQUIRED_MESSAGE = "this field is required"
private const val REGEX_MESSAGE = "value does not match the regex"
private const val LENGTH_MESSAGE = "length does not match"

sealed interface Validator
open class Email(var message: String = EMAIL_MESSAGE): Validator
open class Required(var message: String = REQUIRED_MESSAGE): Validator
open class Regex(var message: String = REGEX_MESSAGE, var regex: String): Validator
open class FixedLength(val length: Int, var message: String = LENGTH_MESSAGE): Validator