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

    fun updateGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        players.playerGroup.forEach { player ->
            val gameResultType = dealer.decideGameResultType(player)
            dealer.result.add(gameResultType)
            players.playersResult.add(player.name, gameResultType.reverse())
        }
    }
}
