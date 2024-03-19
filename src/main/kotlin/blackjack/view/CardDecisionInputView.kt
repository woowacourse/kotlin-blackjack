package blackjack.view

import blackjack.model.participant.Player

class CardDecisionInputView : CardDecision {
    override fun hasMoreCardDecision(player: Player): Boolean {
        println(READ_MESSAGE.format(player.name))
        return readln().toDecisionOrNull() ?: run {
            println(INVALID_MESSAGE)
            hasMoreCardDecision(player)
        }
    }

    private fun String.toDecisionOrNull(): Boolean? {
        if (this == MORE_CARD_MESSAGE) return true
        if (this == NO_MORE_CARD_MESSAGE) return false
        return null
    }

    companion object {
        private const val READ_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val INVALID_MESSAGE = "소문자 y 또는 n으로 입력해 주세요."
        private const val MORE_CARD_MESSAGE = "y"
        private const val NO_MORE_CARD_MESSAGE = "n"
    }
}
