package blackjack.global

interface ThrowableRetry {
    fun onOnceFailure(e: Throwable)

    fun <T> runCatchingUntilValidInput(
        retryCount: Int,
        action: () -> T,
    ): T {
        var tried = 0
        var lastException: Throwable? = null
        while (tried < retryCount) {
            runCatching {
                return action()
            }.onFailure { e ->
                onOnceFailure(e)
                lastException = e
                tried++
            }
        }
        throw IllegalStateException(lastException)
    }
}
