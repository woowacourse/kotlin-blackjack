package blackjack.model

class Dealer(
    name: String = DEFAULT_DEALER_NAME,
    hand: Hand = Hand(emptyList()),
) : Participant(name, hand) {
    override val openCard: List<Card>
        get() = listOf(hand.value[OPEN_CARD_INDEX])

    fun isBlackjack(): Boolean = hand.isBlackjack()

    override fun canHit(): Boolean = getScore() <= DEALER_HIT_SCORE

    fun calculateProfits(players: List<Player>): Profit {
        var dealerProfit = Profit(DEFAULT_PROFIT)

        players.forEach { player ->
            val playerProfit: Profit = player.calculateProfit(hand)
            dealerProfit += playerProfit.reversed()
        }

        return dealerProfit
    }

    companion object {
        const val DEFAULT_DEALER_NAME = "딜러"
        private const val OPEN_CARD_INDEX = 0
        private const val DEFAULT_PROFIT = 0.0
        private const val DEALER_HIT_SCORE = 16
    }
}
