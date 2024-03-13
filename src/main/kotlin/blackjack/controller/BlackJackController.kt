package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.GameResult
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.ResultView

object BlackJackController {
    fun run() {
        val dealer = Dealer()
        val players = registerParticipants()
        try {
            blackJackGameStart(dealer, players)
            displayGameResult(dealer, players)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
        }
    }

    private fun registerParticipants(): List<Player> {
        val playerNames = InputView.inputPlayersName()
        return playerNames.map { name ->
            Player(name)
        }
    }

    private fun blackJackGameStart(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val cardDeck = CardDeck()
        dealer.initialCardDealing(players, cardDeck)
        OutputView.outputCardDistribution(dealer, players)
        judgePlayersDraw(players, cardDeck)
        dealer.judgeDrawOrNot(cardDeck) { OutputView.outputDealerDraw(dealer) }
    }

    private fun judgePlayersDraw(
        players: List<Player>,
        cardDeck: CardDeck,
    ) {
        players.forEach { player ->
            player.judgeDrawOrNot(
                cardDeck,
                { InputView.inputDrawDecision(player.name) },
                { OutputView.outputParticipantCard(player) },
            )
        }
    }

    private fun displayGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        ResultView.outputGameScores(dealer, players)
        val gameResult = GameResult(dealer, players)
        ResultView.outputGameResult(gameResult)
    }
}
