package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards
import blackjack.model.participant.state.Bust
import blackjack.model.participant.state.ParticipantState

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
            val result = competitionResult(player.getState())
            player.name to result
        }

    private fun competitionResult(playerState: ParticipantState): CompetitionResult {
        return when {
            playerState is Bust -> CompetitionResult.LOSE
            isBust() -> CompetitionResult.WIN
            else -> playerState.getResult(getScore())
        }
    }

    companion object {
        private const val DEALER_HIT_THRESHOLD = 17
        private const val INIT_CARD_AMOUNT = 2

        fun withInitCards(deck: Deck): Dealer {
            return Dealer().also { it.initCards(deck.draw(INIT_CARD_AMOUNT)) }
        }
    }
}
