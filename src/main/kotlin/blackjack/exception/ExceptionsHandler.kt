package blackjack.exception

import blackjack.exception.Exceptions.InvalidNameLengthErrorException
import blackjack.exception.Exceptions.InvalidPlayersCountErrorException
import blackjack.exception.Exceptions.NoCardErrorException

object ExceptionsHandler {
    fun <T> handleValidation(
        errorCode: ErrorCode,
        data: T? = null,
        value: () -> Boolean,
    ) {
        if (!value()) {
            val errorException = createErrorException(errorCode.status, errorCode.reason, data)
            errorException?.let { e -> throw e }
        }
    }

    private fun <T> createErrorException(
        status: Int,
        reason: String,
        data: T? = null,
    ): Throwable? =
        when (status) {
            1000 -> NoCardErrorException(reason = reason)
            1001 -> InvalidNameLengthErrorException(reason = reason.format(data))
            1002 -> InvalidPlayersCountErrorException(reason = reason)
            else -> null
        }
}
