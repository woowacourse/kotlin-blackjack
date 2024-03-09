package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.ParticipantName
import blackjack.model.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackJackController {
    fun run() {
        val participants = registerParticipants()
        blackJackGameStart(participants)
    }

    private fun registerParticipants(): Participants {
        val dealer = Dealer()
        val players = registerPlayers()

        return Participants(dealer, players)
    }

    private fun registerPlayerNames(): List<ParticipantName> {
        return runCatching {
            InputView.inputPlayerNames().map { name -> ParticipantName(name) }
        }.onFailure { error ->
            println(error.message)
            return registerPlayerNames()
        }.getOrThrow()
    }

    private fun registerPlayers(): List<Player> {
        val playerNames = registerPlayerNames()
        return playerNames.map { name ->
            Player(name)
        }
    }

    private fun blackJackGameStart(participants: Participants) {
        val cardDeck = CardDeck()
        participants.dealer.initialDealing(participants, cardDeck)
        OutputView.outputCardDistribution(participants)
    }
}
