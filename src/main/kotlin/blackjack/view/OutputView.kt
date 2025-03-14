package blackjack.view

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Participant
import blackjack.domain.model.participant.Participants
import blackjack.domain.model.participant.Player

class OutputView {
    fun newLine() = println()

    fun showDistributeCardMessage(participants: List<Participant>) {
        val joinedNames: String = participants.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    fun showDealerCardsInfo(dealer: Dealer) {
        val dealerCard = dealer.showFirstHand().first()
        println(CARD_INFO_MESSAGE.format(dealer.name, dealerCard.toText()))
    }

    fun showPlayerCardsInfo(player: Player) {
        println(makeParticipantInfo(player))
    }

    fun showDealerDrawMessage() {
        println(DEALER_DRAW_MESSAGE)
    }

    fun showCardsResult(participants: Participants) {
        println(makeParticipantInfo(participants.dealer) + CARD_RESULT_MESSAGE + participants.dealer.hand.getScore())
        participants.players.forEach {
            println(makeParticipantInfo(it) + CARD_RESULT_MESSAGE + it.hand.getScore())
        }
    }

    fun showFinalResult(
        dealerGameResult: Map<GameResult, Int>,
        participants: Participants,
    ) {
        val dealerResultText: String =
            dealerGameResult.filter { it.value != 0 }.map { "${it.value}${it.key.toText()}" }.joinToString()

        println(FINAL_RESULT_MESSAGE)
        println("$DEALER_TEXT_MESSAGE $dealerResultText")
        participants.players.forEach {
            println("${it.name} : ${it.compareTo(participants.dealer).toText()}")
        }
    }

    fun showProfitResult(
        dealerProfit: Double,
        playersProfit: Map<Player, Double>,
    ) {
        println(FINAL_PROFIT_MESSAGE)
        println(DEALER_TEXT_MESSAGE + "$dealerProfit")
        playersProfit.forEach { (player, profit) ->
            println("${player.name}: $profit")
        }
    }

    private fun makeParticipantInfo(participant: Participant): String {
        val name = participant.name
        val cards = participant.hand.toList().joinToString { card -> card.toText() }
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

        fun Card.toText(): String {
            return cardNumber.toText() + suit.toText()
        }

        fun GameResult.toText(): String {
            return when (this) {
                GameResult.BLACKJACK_WIN -> "승"
                GameResult.WIN -> "승"
                GameResult.DRAW -> "무"
                GameResult.LOSE -> "패"
            }
        }

        private fun Suit.toText(): String {
            return when (this) {
                Suit.SPADE -> "스페이드 ♠"
                Suit.HEART -> "하트 ♥"
                Suit.DIAMOND -> "다이아몬드 ♦"
                Suit.CLUB -> "클로버 ♣"
            }
        }

        private fun CardNumber.toText(): String {
            return when (this) {
                CardNumber.ACE -> "A"
                CardNumber.JACK -> "J"
                CardNumber.QUEEN -> "Q"
                CardNumber.KING -> "K"
                else -> this.value.toString()
            }
        }
    }
}
