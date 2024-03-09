package blackjack.controller

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import blackjack.model.GameInformation
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
        val dealer = Dealer(gameInformation = GameInformation())
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
            Player(name, GameInformation())
        }
    }

    private fun blackJackGameStart(participants: Participants) {
        val cardDeck = initializeCardDeck()
        participants.dealer.initialDealing(participants, cardDeck)
        OutputView.outputCardDistribution(participants)
    }

    private fun initializeCardDeck(): CardDeck {
        val cards =
            CardNumber.entries.flatMap { number -> CardSymbol.entries.map { symbol -> Card(number, symbol) } }
                .shuffled()
        return CardDeck(cards)
    }
}
