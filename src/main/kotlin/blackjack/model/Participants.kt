package blackjack.model

class Participants(
    val dealer: Dealer,
    val playerGroup: PlayerGroup,
) {
    fun initParticipantsDeck() {
        dealer.addCard(card = GameDeck.drawCard())
        playerGroup.players.forEach { player ->
            player.addCard(card = GameDeck.drawCard())
        }
    }

    fun resetHand() {
        playerGroup.players.forEach { player -> player.getState().hand().reset() }
        dealer.getState().hand().reset()
    }

    fun calculateResult(): ProfitResults {
        val result = mutableListOf<ProfitResult>()
        var totalProfit = 0.0

        playerGroup.players.forEach { player ->
            val profit = player.calculateProfit(dealer)
            result.add(ProfitResult(player, Profit((profit))))
            totalProfit += profit
        }
        result.add(0, ProfitResult(dealer, Profit((-totalProfit))))

        return ProfitResults(result.toList())
    }

    companion object {
        const val INITIAL_CARD_COUNTS = 2
    }
}
