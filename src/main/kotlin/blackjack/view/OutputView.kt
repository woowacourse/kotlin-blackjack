package blackjack.view

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.Rank
import blackjack.domain.model.card.Suit

class OutputView {
    fun printInitialDeals(
        dealerName: String,
        playerNames: List<String>,
    ) {
        println(MESSAGE_INITIAL_HAND_DISTRIBUTED.format(dealerName, playerNames.joinToString(CARDS_DELIMITER)))
        println()
    }

    fun printParticipantStatus(
        name: String,
        cards: List<Card>,
    ) {
        println(renderParticipantCards(name, cards))
    }

    fun printParticipantResult(
        name: String,
        cards: List<Card>,
        point: Int,
    ) {
        println(renderParticipantCards(name, cards) + PARTICIPANT_STATUS_RESULT_DELIMITER + point)
    }

    private fun renderParticipantCards(
        name: String,
        cards: List<Card>,
    ): String {
        return name + PARTICIPANT_NAME_CARDS_DELIMITER +
            cards.joinToString { card ->
                card.rank.stringRepresentation() + card.suit.stringRepresentation()
            }
    }

    fun printDealerHit(
        name: String,
        hitThreshold: Int,
    ) {
        println(MESSAGE_DEALER_HIT.format(name, hitThreshold))
    }

    fun printFinalResult(): (String, Int) -> Unit {
        println()
        println(FINAL_RESULT_HEADER)
        return { name: String, profit: Int -> printParticipantProfit(name, profit) }
    }

    private fun printParticipantProfit(
        name: String,
        profit: Int,
    ) {
        println(name + PARTICIPANT_PROFIT_DELIMITER + profit)
    }

    private fun Suit.stringRepresentation(): String {
        return when (this) {
            Suit.HEART -> SUIT_HEART
            Suit.DIAMOND -> SUIT_DIAMOND
            Suit.CLUB -> SUIT_CLUB
            Suit.SPADE -> SUIT_SPADE
        }
    }

    private fun Rank.stringRepresentation(): String {
        return when (this) {
            Rank.ACE -> RANK_ACE
            Rank.JACK -> RANK_JACK
            Rank.QUEEN -> RANK_QUEEN
            Rank.KING -> RANK_KING
            else -> point.toString()
        }
    }

    companion object {
        private const val MESSAGE_INITIAL_HAND_DISTRIBUTED = "%s와(과) %s에게 ${Hand.STARTING_HAND_SIZE}장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HIT = "%s은(는) %s점 이하라 한 장의 카드를 더 받았습니다."

        private const val CARDS_DELIMITER = ", "
        private const val PARTICIPANT_NAME_CARDS_DELIMITER = " 카드: "
        private const val PARTICIPANT_STATUS_RESULT_DELIMITER = " - 결과: "
        private const val PARTICIPANT_PROFIT_DELIMITER = ": "
        private const val FINAL_RESULT_HEADER = "## 최종 수익"

        private const val SUIT_HEART = "하트"
        private const val SUIT_DIAMOND = "다이아몬드"
        private const val SUIT_SPADE = "스페이드"
        private const val SUIT_CLUB = "클로버"

        private const val RANK_ACE = "A"
        private const val RANK_JACK = "J"
        private const val RANK_QUEEN = "Q"
        private const val RANK_KING = "K"
    }
}
