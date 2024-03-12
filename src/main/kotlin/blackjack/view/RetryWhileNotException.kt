package blackjack.view

fun <T> retryWhileNotException(block: () -> T): T {
    return try {
        block()
    } catch (e: IllegalArgumentException) {
        println(e.localizedMessage)
        retryWhileNotException(block)
    }
}
