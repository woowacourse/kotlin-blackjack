package blackjack.model

import blackjack.exception.ErrorCode.INVALID_NAME_LENGTH_ERROR
import blackjack.exception.ExceptionsHandler.handleValidation

@JvmInline
value class Nickname(val name: String) {
    init {
        handleValidation(INVALID_NAME_LENGTH_ERROR, name) { name.length in NAME_RANGE }
    }

    override fun toString(): String = name

    companion object {
        val NAME_RANGE = 1..20
    }
}
