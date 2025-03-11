package blackjack.view

import java.lang.IllegalArgumentException

object BlackJackInputView {
    fun askPlayerHit(name: String): Boolean {
        println(requestAdditionalCard(name))
        val input = readln()
        if (input == YES) return true
        if (input == NO) return false
        throw IllegalArgumentException()
    }

    private fun requestAdditionalCard(name: String): String = "${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    private const val YES = "y"
    private const val NO = "n"
}
