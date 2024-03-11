package blackjack.exception

import blackjack.exception.Exceptions.InvalidNameLengthErrorException
import blackjack.exception.Exceptions.InvalidPlayersCountErrorException
import blackjack.exception.Exceptions.NoCardErrorException

object ExceptionsHandler {
    fun handleValidation(
        errorCode: ErrorCode,
        value: () -> Boolean,
    ) {
        if (!value()) {
            val errorException = createErrorException(errorCode.status, errorCode.reason)
            errorException?.let { e -> throw e }
        }
    }

    private fun createErrorException(
        status: Int,
        reason: String,
    ): Throwable? =
        when (status) {
            1000 -> NoCardErrorException(reason = reason)
            1001 -> InvalidNameLengthErrorException(reason = reason)
            1002 -> InvalidPlayersCountErrorException(reason = reason)
            else -> null
        }
}
