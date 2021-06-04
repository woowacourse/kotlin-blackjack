package domain

import java.lang.IllegalArgumentException

enum class ResponseType(val command: String, val drawable: Boolean) {
    HIT("y", true),
    STAY("n", false)
}

fun getResponse(command: String): ResponseType {
    return enumValues<ResponseType>().find { it.command == command }
        ?: throw IllegalArgumentException("올바른 입력이 아닙니다.")

}
