package blackjack.view

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Participant
import blackjack.domain.model.participant.Player
import blackjack.view.OutputView.Companion.toText

class OutputView {
    fun newLine() = println()

    fun showDistributeCardMessage(participants: List<Participant>) {
        val joinedNames: String = participants.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    fun showDealerCardsInfo(dealer: Dealer) {
        val cardsInfoText: String = dealer.showFirstCard().toText()
        println(CARD_INFO_MESSAGE.format(dealer.name, cardsInfoText))
    }

    fun showPlayerCardsInfo(player: Player) {
        println(makeParticipantInfo(player))
    }

    fun showDealerDrawMessage() {
        println(DEALER_DRAW_MESSAGE)
    }

    fun showCardsResult(participants: List<Participant>) {
        participants.forEach {
            println(makeParticipantInfo(it) + CARD_RESULT_MESSAGE + it.handCards.getScore())
        }
    }

    fun showFinalResult(
        dealerGameResult: Map<GameResult, Int>,
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerResultText: String =
            dealerGameResult.filter { it.value != 0 }.map { "${it.value}${it.key.toText()}" }.joinToString()

        println("## 최종 승패")
        println("딜러: $dealerResultText")
        players.forEach { player -> player.compareTo(dealer).toText() }
    }

    private fun makeParticipantInfo(participant: Participant): String {
        val name = participant.name
        val cards = participant.handCards.show().joinToString { card -> card.toText() }
        return CARD_INFO_MESSAGE.format(name, cards)
    }

    companion object {
        private const val DISTRIBUTE_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val CARD_INFO_MESSAGE = "%s카드: %s"
        private const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val CARD_RESULT_MESSAGE = " - 결과: "

        fun Suit.toText(): String {
            return when (this) {
                Suit.SPADE -> "스페이드"
                Suit.HEART -> "하트"
                Suit.DIAMOND -> "다이아몬드"
                Suit.CLUB -> "클로버"
            }
        }

        fun CardNumber.toText(): String {
            return when (this) {
                CardNumber.ACE -> "A"
                CardNumber.JACK -> "J"
                CardNumber.QUEEN -> "Q"
                CardNumber.KING -> "K"
                else -> this.value.toString()
            }
        }

        fun Card.toText(): String {
            return cardNumber.toText() + suit.toText()
        }

        fun GameResult.toText(): String {
            return when (this) {
                GameResult.WIN -> "승"
                GameResult.DRAW -> "무"
                GameResult.LOSE -> "패"
            }
        }
    }
}
