package blackjack.model

class GameManager {
    fun prepareDealer(
        dealerName: String,
        cardDeck: CardDeck,
        scoreCalculator: ScoreCalculator,
    ): Dealer {
        val dealer = Dealer(dealerName, Hand(scoreCalculator))
        dealer.recieveCards(cardDeck::draw)

        return dealer
    }

    fun preparePlayers(
        playerNames: List<String>,
        cardDeck: CardDeck,
        scoreCalculator: ScoreCalculator,
    ): Players {
        val players = Players.from(playerNames, scoreCalculator)
        players.value.forEach { player -> player.recieveCards(cardDeck::draw) }

        return players
    }

    fun progressDealerDraw(
        dealer: Dealer,
        draw: (Int) -> List<Card>,
    ) {
        while (dealer.isDrawable()) {
            dealer.recieveCards(draw)
        }
    }

    fun getResult(resultManager: ResultManager): GameResult {
        val dealerResult: Map<WinningResult, Int> = resultManager.dealerResult()
        val playerResults: Map<String, WinningResult> = resultManager.playerResults()

        return GameResult(dealerResult, playerResults)
    }
}
