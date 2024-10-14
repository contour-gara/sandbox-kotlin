package org.contourgara.arrow

import arrow.core.Either
import org.apache.commons.lang3.StringUtils.isNumeric
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.ResolverStyle

data class MyDateTime private constructor(val dateTime: LocalDateTime) {
    companion object {
        fun of(date: String, time: Int): Either<MyDateTimeError, MyDateTime> {
            if (!isNumeric(date)) return Either.Left(MyDateTimeError.DateError("日付が不正です。"))
            if (time !in 0..2359) return Either.Left(MyDateTimeError.TimeError("時間が不正です。"))
            if (time % 100 >= 60) return Either.Left(MyDateTimeError.TimeError("時間が不正です。"))
            return try {
                Either.Right(
                    MyDateTime(
                        LocalDateTime.parse(
                            "$date${time.toString().padStart(4, '0')}",
                            DateTimeFormatter.ofPattern("uuuuMMddHHmm").withResolverStyle(ResolverStyle.STRICT)
                        )
                    )
                )
            } catch (dateTimeParseException: DateTimeParseException) {
                Either.Left(MyDateTimeError.DateError("日付が不正です。"))
            }
        }
    }
}
