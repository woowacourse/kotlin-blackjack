package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards

class Player(
    val name: String,
    cards: ParticipantCards,
) : Participant(cards) {
    fun choice(
        deck: Deck,
        getPlayerChoice: (String) -> UserChoice,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        while (!isBust()) {
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

    fun getResult(other: Participant): GameResult {
        val myScore = this.finalScore()
        val otherScore = other.finalScore()

        return when {
            this.isBust() && !other.isBust() -> GameResult.LOSE
            other.isBust() && !this.isBust() -> GameResult.WIN
            myScore > otherScore -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }
}
