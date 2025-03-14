package blackjack.utils

fun <T> retryWhenException(
    action: () -> T,
    onFailure: (Throwable) -> Unit,
): T {
    while (true) {
        runCatching {
            return action()
        }.onFailure { e ->
            onFailure(e)
        }
    }
}
