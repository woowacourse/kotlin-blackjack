package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards
import blackjack.domain.UserChoice
import blackjack.domain.card.TrumpCard
import blackjack.domain.deck.Deck

class Player(
    val name: String,
    cards: ParticipantCards,
) : Participant(cards) {
    override fun getInitialCards(): List<TrumpCard> = cards.allCards.take(PLAYER_INITIAL_CARD_COUNT)

    override fun isDrawable(): Boolean = cards.sumOfCards <= BUST_STANDARD

    fun choice(
        deck: Deck,
        getPlayerChoice: (String) -> UserChoice,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        while (isDrawable()) {
            val choice = getPlayerChoice(name)
            when (choice) {
                UserChoice.HIT -> {
                    receiveCard(deck.pop())
                    onPlayerStateUpdated(this)
                }
                UserChoice.STAY -> return
            }
        }
    }

    override fun getResult(other: Participant): GameResult {
        val myScore = this.finalScore()
        val otherScore = other.finalScore()

        return when {
            this.isBust() -> GameResult.LOSE
            other.isBust() && !this.isBust() -> GameResult.WIN
            myScore > otherScore -> GameResult.WIN
            myScore < otherScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    companion object {
        const val PLAYER_INITIAL_CARD_COUNT = 2
    }
}
