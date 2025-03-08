package blackjack.domain.model.participant

import blackjack.domain.model.GameResult

class Player(
    name: String = DEFAULT_NAME,
) : Participant(name = name) {
    override fun compareTo(opponent: Participant): GameResult {
        return handCards.compareTo(opponent.handCards)
    }

    override fun isDrawable(): Boolean = !handCards.isBurst()

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
