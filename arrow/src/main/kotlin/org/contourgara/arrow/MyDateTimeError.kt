package org.contourgara.arrow

sealed interface MyDateTimeError {
    data class DateError(val message: String) : MyDateTimeError
    data class TimeError(val message: String) : MyDateTimeError
}
