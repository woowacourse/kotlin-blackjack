package blackjack.global

interface NullableRetry {
    fun onOnceFailure()

    fun <T> retryUntilValidInput(
        retryCount: Int,
        nullableAction: () -> T?,
    ): T {
        var tried = 0
        var result: T? = nullableAction()
        while (result == null && tried < retryCount) {
            onOnceFailure()
            result = nullableAction()
            tried++
        }
        result ?: throw RetryException()
        return result
    }
}
