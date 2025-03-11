package blackjack.domain.model.participant

class Dealer(
    name: String = DEFAULT_NAME,
) : GameParticipant(name = name) {
    override fun play() {
        // todo
    }

    override fun isDrawFinish(): Boolean {
        val bestCardValue = handCards.calculateBestCardValue()
        return bestCardValue <= DEALER_DRAW_LIMIT
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_LIMIT = 16
    }
}
