package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.GameRevenue
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.ParticipantInformation.PlayerInformation
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.ResultView

object BlackJackController {
    fun run() {
        val players = registerPlayers()
        val dealer = Dealer()
        try {
            blackJackGameStart(dealer, players)
            displayBlackJackGameResult(dealer, players)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
        }
    }

    private fun registerPlayers(): List<Player> {
        val playersName = InputView.inputPlayersName()
        val playersBettingAmount =
            playersName.map { playerName ->
                InputView.inputBettingAmount(playerName)
            }
        val players = mutableListOf<Player>()
        for (index in playersName.indices) {
            val participantInformation =
                PlayerInformation(playersName[index], playersBettingAmount[index])
            val player = Player(participantInformation)
            players.add(player)
        }
        return players
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
                { InputView.inputDrawDecision(player.participantInformation.name).judgeDecision() },
                { OutputView.outputParticipantCard(player) },
            )
        }
    }

    private fun displayBlackJackGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        ResultView.outputGameScores(dealer, players)
        ResultView.outputGameResult(GameRevenue(dealer, players))
    }
}
