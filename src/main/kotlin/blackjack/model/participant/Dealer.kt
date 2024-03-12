package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards
import blackjack.util.CompetitionResult

class Dealer private constructor() : GameParticipant(HandCards()) {
    fun getFirstCard() = handCards.cards.first()

    override fun add(cards: List<Card>): Boolean =
        if (handCards.calculateCardScore() < DEALER_HIT_THRESHOLD) {
            handCards.add(cards)
            true
        } else {
            false
        }

    fun gameResult(players: List<Player>): Map<String, CompetitionResult> =
        players.associate { player ->
            val result = competitionResult(player)
            player.name to result
        }

    private fun competitionResult(player: Player): CompetitionResult =
        when {
            player.isBust() -> CompetitionResult.LOSE
            isBust() -> CompetitionResult.WIN
            player.isBlackjack() && isBlackjack() -> CompetitionResult.SAME
            player.isBlackjack() -> CompetitionResult.WIN
            isBlackjack() -> CompetitionResult.LOSE
            player.getScore() < getScore() -> CompetitionResult.LOSE
            player.getScore() > getScore() -> CompetitionResult.WIN
            else -> CompetitionResult.SAME
        }

    companion object {
        private const val DEALER_HIT_THRESHOLD = 17
        private const val INIT_CARD_AMOUNT = 2

        fun withInitCards(deck: Deck): Dealer {
            return Dealer().also { it.initCards(deck.draw(INIT_CARD_AMOUNT)) }
        }
    }
}
