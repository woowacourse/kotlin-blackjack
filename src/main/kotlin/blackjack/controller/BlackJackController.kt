package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.game.GameResult
import blackjack.model.user.Participant.Dealer
import blackjack.model.user.Participant.Player
import blackjack.model.user.ParticipantInformation.PlayerInformation
import blackjack.view.ProgressInputView
import blackjack.view.ProgressOutputView
import blackjack.view.ResultOutputView
import blackjack.view.SettingInputView

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
        val playersName = SettingInputView.inputPlayersName()
        val playersBettingAmount =
            playersName.map { playerName ->
                SettingInputView.inputBettingAmount(playerName)
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
        ProgressOutputView.outputCardDistribution(dealer, players)
        judgePlayersDraw(players, cardDeck)
        dealer.judgeDrawOrNot(cardDeck) { ProgressOutputView.outputDealerDraw(dealer) }
    }

    private fun judgePlayersDraw(
        players: List<Player>,
        cardDeck: CardDeck,
    ) {
        players.forEach { player ->
            player.judgeDrawOrNot(
                cardDeck,
                { ProgressInputView.inputDrawDecision(player.participantInformation.name).judgeDecision() },
                { ProgressOutputView.outputParticipantCard(player) },
            )
        }
    }

    private fun displayBlackJackGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        ResultOutputView.outputGameScores(dealer, players)
        ResultOutputView.outputGameResult(GameResult(dealer, players))
    }
}
