package blackjack.model

class Dealer(wallet: Wallet = Wallet(Identification(DEFAULT_DEALER_NAME))) : Participant(wallet) {
    fun openFirstCard(): Card? {
        return getCards().firstOrNull()
    }

    override fun openInitCards(): List<Card> {
        return getCards().firstOrNull()?.let { listOf(it) } ?: listOf()
    }

    override fun checkShouldDrawCard(): Boolean {
        return getBlackJackScore() <= MIN_HAND_CARD_SCORE
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val MIN_HAND_CARD_SCORE: Int = 16
    }
}
