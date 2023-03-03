package blackjack.domain

sealed class DrawResult {

    object Success : DrawResult()
    object Failure : DrawResult()
}
