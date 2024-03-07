package blackjack.model.participant

import blackjack.model.CompetitionResult
import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards

class Dealer(deck: Deck) : GameParticipant(HandCards(deck)) {
    fun getFirstCard() =
        with(handCards.cards.first()) {
            "${cardNumber.value}${pattern.shape}"
        }

    fun addCard(): Boolean =
        if (handCards.calculateCardScore() < DEALER_HIT_THRESHOLD) {
            handCards.add()
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
    }
}
