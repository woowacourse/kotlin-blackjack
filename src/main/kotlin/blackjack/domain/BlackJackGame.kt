package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.dealer.DrawResult
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.BlackJackPlayers
import blackjack.domain.player.Player

class BlackJackGame(
    val dealer: Dealer = Dealer(),
) {

    lateinit var blackJackPlayers: BlackJackPlayers
        private set

    fun initPlayers(
        playerDataSources: List<Pair<String, Int>>,
    ) {
        val players = playerDataSources.map { (playerName, battingMoney) ->
            Player(playerName, battingMoney)
        }

        blackJackPlayers = BlackJackPlayers(players)
    }

    fun drawAdditionalCardsForPlayers(
        isPlayerWantedAdditionalCards: (player: Player) -> Boolean,
        checkCurrentCards: (player: Player) -> Unit = { },
    ) {
        blackJackPlayers.drawAdditionalCardsForPlayers(
            isPlayerWantedAdditionalCards = isPlayerWantedAdditionalCards,
            checkCurrentCards = checkCurrentCards
        )
    }

    fun drawAdditionalCardsForDealer(checkDrawResult: (DrawResult) -> Unit) {
        dealer.drawCard(checkDrawResult)
    }

    fun judgeResult(): TotalGameResult {
        val playersGameResults = blackJackPlayers.judgePlayerGameResults(dealer.cards.state)
        val dealerGameResult = dealer.judgeDealerGameResults(playersGameResults.getPlayersTotalProfit())

        return TotalGameResult(playersGameResults, dealerGameResult)
    }

}
