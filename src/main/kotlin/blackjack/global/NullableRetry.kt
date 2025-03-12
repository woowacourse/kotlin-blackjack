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
        requireNotNull(result) { "$retryCount 번 이상 잘못된 값을 입력하였습니다" }
        return result
    }
}
