package blackjack.global

interface ThrowableRetry {
    fun onOnceFailure(e: Throwable)

    fun <T> runCatchingUntilValidInput(
        retryCount: Int,
        action: () -> T,
    ): T {
        repeat(retryCount) {
            runCatching {
                return action()
            }.onFailure { e ->
                // 재시도 실패 시 생긴 예외는 핸들링 안함
                if (e is RetryException) throw RetryException()
                onOnceFailure(e)
            }
        }
        throw RetryException()
    }
}
