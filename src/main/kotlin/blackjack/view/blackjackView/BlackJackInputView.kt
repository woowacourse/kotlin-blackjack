package blackjack.view.blackjackView

import blackjack.global.NullableRetry

object BlackJackInputView : NullableRetry {
    fun askPlayerHit(name: String): Boolean {
        println(requestAdditionalCard(name))

        return retryUntilValidInput(RETRY_COUNT) {
            val input = readln()
            when (input) {
                YES -> true
                NO -> false
                else -> null
            }
        }
    }

    override fun onOnceFailure() {
        println("올바르지 않은 값입니다 다시 시도해주세요")
    }

    private fun requestAdditionalCard(name: String): String = "${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    private const val YES = "y"
    private const val NO = "n"
    private const val RETRY_COUNT = 3
}
