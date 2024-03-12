package blackjack.view

import blackjack.model.participant.Player

class CardDecisionInputView : CardDecision {
    override fun isMoreCardDecision(player: Player): Boolean {
        println(READ_MESSAGE.format(player.name))
        val moreCardDecision = readln()
        return getMoreCardDecision(moreCardDecision) ?: run {
            println(INVALID_MESSAGE)
            isMoreCardDecision(player)
        }
    }

    private fun getMoreCardDecision(moreCardDecision: String): Boolean? {
        if (moreCardDecision == MORE_CARD_MESSAGE) return true
        if (moreCardDecision == NO_MORE_CARD_MESSAGE) return false
        return null
    }

    companion object {
        private const val READ_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val INVALID_MESSAGE = "소문자 y 또는 n으로 입력해 주세요."
        private const val MORE_CARD_MESSAGE = "y"
        private const val NO_MORE_CARD_MESSAGE = "n"
    }
}
