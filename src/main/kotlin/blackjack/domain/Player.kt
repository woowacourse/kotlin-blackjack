package blackjack.domain

class Player(val name: String, override val cardBunch: CardBunch) : Participant {

    fun chooseWinner(dealer: Dealer): Consequence =
        dealer.compareScore(cardBunch.getTotalScore(), cardBunch.isBlackJack())

    override fun isOverCondition(): Boolean = cardBunch.isBurst()

    fun progressAddCard(
        getDecision: (Player) -> Boolean,
        transferPlayerCard: (Player) -> Unit,
        getCard: () -> Card,
    ) {
        while (!isOverCondition()) {
            if (!progressEachTurn(getDecision, transferPlayerCard, getCard)) return
        }
    }

    private fun progressEachTurn(
        getDecision: (Player) -> Boolean,
        transferPlayerCard: (Player) -> Unit,
        getCard: () -> Card,
    ): Boolean {
        if (getDecision(this)) {
            receiveCard(getCard())
            transferPlayerCard(this)
            return true
        }
        transferPlayerCard(this)
        return false
    }
}
