package blackjack.view

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Participant
import blackjack.domain.model.participant.Player

class OutputView {
    fun newLine() = println()

    fun showParticipantFirstCardsInfo(participants: List<Participant>) {
        showDistributeCardMessage(participants)

        participants.forEach { participant ->
            val participantHandCards: List<Card> = participant.showFirstHand()
            println(CARD_INFO_MESSAGE.format(participant.name, participantHandCards.joinToString { it.toText() }))
        }
    }

    fun showParticipantCardsInfo(participant: Participant) {
        println(makeParticipantInfo(participant))
    }

    fun showDealerDrawMessage() {
        println(DEALER_DRAW_MESSAGE)
    }

    fun showCardsResult(participants: List<Participant>) {
        participants.forEach {
            println(makeParticipantInfo(it) + CARD_RESULT_MESSAGE + it.hand.getScore())
        }
    }

    fun showGameResults(
        dealerGameResult: Map<GameResult, Int>,
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerResultText: String =
            dealerGameResult.filter { it.value != 0 }.map { "${it.value}${it.key.toText()}" }.joinToString()

        println(FINAL_RESULT_MESSAGE)
        println("$DEALER_TEXT_MESSAGE $dealerResultText")
        players.forEach {
            println("${it.name} : ${it.compareTo(dealer).toText()}")
        }
    }

    fun showProfitResults(
        dealerProfit: Double,
        playersProfit: Map<Player, Double>,
    ) {
        println(FINAL_PROFIT_MESSAGE)
        println(DEALER_TEXT_MESSAGE + "$dealerProfit")
        playersProfit.forEach { (player, profit) ->
            println("${player.name}: $profit")
        }
    }

    private fun showDistributeCardMessage(participants: List<Participant>) {
        val joinedNames: String = participants.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    private fun makeParticipantInfo(participant: Participant): String {
        val name: String = participant.name
        val cards: String = participant.hand.cards.joinToString { card -> card.toText() }
        return CARD_INFO_MESSAGE.format(name, cards)
    }

    companion object {
        private const val DISTRIBUTE_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val CARD_INFO_MESSAGE = "%s카드: %s"
        private const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val CARD_RESULT_MESSAGE = " - 결과: "
        private const val FINAL_RESULT_MESSAGE = "## 최종 승패"
        private const val DEALER_TEXT_MESSAGE = "딜러:"
        private const val FINAL_PROFIT_MESSAGE = "## 최종 수익"

        fun Card.toText(): String = cardNumber.toText() + suit.toText()

        fun GameResult.toText(): String =
            when (this) {
                GameResult.BLACKJACK_WIN -> "승"
                GameResult.WIN -> "승"
                GameResult.DRAW -> "무"
                GameResult.LOSE -> "패"
            }

        private fun Suit.toText(): String =
            when (this) {
                Suit.SPADE -> "스페이드 ♠"
                Suit.HEART -> "하트 ♥"
                Suit.DIAMOND -> "다이아몬드 ♦"
                Suit.CLUB -> "클로버 ♣"
            }

        private fun CardNumber.toText(): String =
            when (this) {
                CardNumber.ACE -> "A"
                CardNumber.JACK -> "J"
                CardNumber.QUEEN -> "Q"
                CardNumber.KING -> "K"
                else -> this.value.toString()
            }
    }
}
