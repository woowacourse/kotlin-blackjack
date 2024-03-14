package blackjack.model.domain

import blackjack.model.entitiy.Card
import blackjack.model.entitiy.CardRank

data class ParticipantInfo(
    val name: String = "",
    val batingAmount: Int = 0,
    var cards: List<Card> = listOf(),
) {
    fun findWinner(dealerInfo: ParticipantInfo): Int {
        val dealerTotal = dealerInfo.sumCardValues()
        val playerTotal = this.sumCardValues()

        return when {
            isBlackJack(dealerTotal).not() && isBlackJack(playerTotal) && cards.size == 2 -> (batingAmount * 1.5).toInt()
            isBust(dealerTotal) && isBust(playerTotal) -> 0
            isBust(dealerTotal) -> batingAmount
            isBust(playerTotal) -> batingAmount.unaryMinus()
            playerTotal > dealerTotal -> batingAmount
            dealerTotal > playerTotal -> batingAmount.unaryMinus()
            else -> 0
        }
    }

    fun sumCardValues(): Int {
        var total = cards.sumOf { card -> card.cardRank.value }

        if (total <= 11 && hasAce()) total += 10

        return total
    }

    private fun isBlackJack(total: Int) = total == 21

    private fun isBust(total: Int) = total > 21

    private fun hasAce() = cards.any { it.cardRank == CardRank.ACE }
}
