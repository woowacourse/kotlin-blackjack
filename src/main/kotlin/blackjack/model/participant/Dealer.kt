package blackjack.model.participant

import blackjack.model.CompetitionResult
import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards

class Dealer(deck: Deck) : GameParticipant(HandCards(deck)) {
    init {
        initializeCards()
    }

    fun getFirstCard(): String = handCards.getFirstCard()

    fun isAddCard(): Boolean = calculateScore() < DEALER_HIT_THRESHOLD

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
            player.calculateScore() < calculateScore() -> CompetitionResult.LOSE
            player.calculateScore() > calculateScore() -> CompetitionResult.WIN
            else -> CompetitionResult.SAME
        }

    companion object {
        private const val DEALER_HIT_THRESHOLD = 17
    }
}
