package blackjack.domain.model

class Dealer(
    name: String = DEFAULT_NAME,
) : GameParticipant(name = name) {
    override fun play() {
        // todo
    }

    fun showFirstCard(): Card = showCards().first()

    override fun isDrawFinish(): Boolean = !Rule.calculateShouldDrawByCards(showCards())

    companion object {
        private const val DEFAULT_NAME = "딜러"
    }
}
