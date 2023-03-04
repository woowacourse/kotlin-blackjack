package blackjack.domain.dealer

sealed class DrawResult {

    object Success : DrawResult()
    object Failure : DrawResult()
}
