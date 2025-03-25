package blackjack.domain

import blackjack.BlackJackGame
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.view.GameViewListener
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val participants = getParticipants()

        BlackJackGame(
            object : GameViewListener {
                override fun onPlayerHit(name: String): Boolean {
                    return inputView.askPlayerHit(name)
                }
            },
            outputView,
        ).play(participants = participants).apply {
        }
    }

    private fun getParticipants(): Participants {
        return Participants(dealer = Dealer(), players = getPlayersWithBets())
    }

    private fun getPlayersWithBets(): List<Player> {
        val playerNames = InputView.readPlayerNames()

        return playerNames.map { name ->
            val betAmount = InputView.readBetAmounts(name)
            Player(name, BetAmount(betAmount))
        }
    }
}
