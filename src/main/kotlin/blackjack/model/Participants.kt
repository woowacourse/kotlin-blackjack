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

    fun calculateResult(): WinningState {
        val result = mutableMapOf<CardHolder, GameResult>()
        playerGroup.players.forEach { player ->
            calculateDealerResult(result, player)
            calculatePlayerResult(result, player)
        }
        return WinningState(result)
    }

    private fun calculateDealerResult(
        result: MutableMap<CardHolder, GameResult>,
        player: Player,
    ) {
        val originalDealerWinningState = result.getOrDefault(dealer, GameResult(0, 0))
        val addDealerWinningState = dealer.calculateWinningStateAgainst(player)
        result[dealer] =
            GameResult(
                win = originalDealerWinningState.win + addDealerWinningState.win,
                lose = originalDealerWinningState.lose + addDealerWinningState.lose,
            )
    }

    private fun calculatePlayerResult(
        result: MutableMap<CardHolder, GameResult>,
        player: Player,
    ) {
        result[player] = player.calculateWinningStateAgainst(dealer)
    }

    companion object {
        const val INITIAL_CARD_COUNTS = 2
    }
}
