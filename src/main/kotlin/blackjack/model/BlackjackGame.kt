package blackjack.model

object BlackjackGame {
    private const val INIT_RECEIVE_CARD_COUNT = 2

    fun initCard(
        dealer: Dealer,
        players: Players,
        cardProvider: CardProvider,
    ) {
        dealer.initReceiveCard(cardProvider)
        players.playerGroup.forEach {
            it.initReceiveCard(cardProvider)
        }
    }

    private fun Role.initReceiveCard(cardProvider: CardProvider) {
        repeat(INIT_RECEIVE_CARD_COUNT) {
            receiveCard(Card.from(cardProvider))
        }
    }

    fun calculateGameResult(
        dealer: Dealer,
        players: Players,
    ): GameResultStorage {
        val dealerResult = DealerResult()
        val playersResult = PlayersResult()

        players.playerGroup.forEach { player ->
            val gameResultType = dealer.decideGameResultType(player)
            dealerResult.add(gameResultType)
            playersResult.add(player.name, gameResultType.reverse())
        }
        return GameResultStorage(dealerResult, playersResult)
    }
}
