package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame(
    private val cardPack: CardPack,
    private val dealer: Dealer = Dealer(CardHand(listOf(cardPack.draw(), cardPack.draw())))
) {
    private lateinit var players: List<Player>

    fun entryPlayers() {
        players = InputView.requestPlayersName().map { name ->
            Player(
                name = PlayerName(name),
                cardHand = CardHand(listOf(cardPack.draw(), cardPack.draw())),
                betAmount = BetAmount(InputView.requestBetAmount(name))
            )
        }
    }

    fun showDividingCards() = OutputView.printCardDividingMessage(dealer, players)

    fun drawAdditionalCards() = players.forEach { player ->
        askToDrawAdditionalCardForPlayer(player)
    }

    private fun askToDrawAdditionalCardForPlayer(player: Player) {
        do {
            val drawFlag = InputView.requestAdditionalDraw(player)
        } while (drawFlag && drawAdditionalCardForPlayer(player) == DrawState.POSSIBLE)

        if (player.cardHand.size == CardHand.INITIAL_CARDS_SIZE) {
            OutputView.printCardResults(player)
        }
    }

    private fun drawAdditionalCardForPlayer(player: Player): DrawState {
        val drawState = player.drawCard(cardPack.draw())

        OutputView.printCardResults(player)

        return drawState
    }

    fun drawAdditionalCardForDealer() {
        val drawResult = dealer.drawCard(cardPack.draw())

        OutputView.printIsDealerReceivedCard(drawResult)
    }

    fun showFinalCards() = OutputView.printFinalCards(dealer, players)

    fun judgeGameResults() {
        val playersGameResult = BlackJackReferee.judgeGameResult(players, dealer)
        // val dealerGameResult = playersGameResult.map { playerGameResult ->
        //     !playerGameResult.gameResult
        // }

        val dealerGameResult = listOf(GameResult.DRAW)
        OutputView.printGameResults(playersGameResult, dealerGameResult)
    }
}
