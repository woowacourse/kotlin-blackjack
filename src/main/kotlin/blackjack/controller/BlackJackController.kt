package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.DrawDecision
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
        val players = initializePlayers()
        return Participants(dealer, players)
    }

    private fun readPlayerNames(): List<ParticipantName> {
        return runCatching {
            InputView.inputPlayerNames().map { name -> ParticipantName(name) }
        }.onFailure { error ->
            println(error.message)
            return readPlayerNames()
        }.getOrThrow()
    }

    private fun initializePlayers(): List<Player> {
        val playerNames = readPlayerNames()
        return playerNames.map { name ->
            Player(name)
        }
    }

    private fun blackJackGameStart(participants: Participants) {
        val cardDeck = CardDeck()
        participants.dealer.initialCardDealing(participants, cardDeck)
        OutputView.outputCardDistribution(participants)
        decideDrawOrNot(participants, cardDeck)
    }

    private fun decideDrawOrNot(
        participants: Participants,
        cardDeck: CardDeck,
    ) {
        participants.players.forEach { player ->
            while (readDrawDecision(player.name)) {
                player.gameInformation.drawCard(cardDeck.pickCard())
                OutputView.outputParticipantCard(player)
            }
        }
    }

    private fun readDrawDecision(name: ParticipantName): Boolean {
        return runCatching {
            DrawDecision(InputView.inputDrawDecision(name)).judgeDecision()
        }.onFailure { error ->
            print(error.message)
            return readDrawDecision(name)
        }.getOrThrow()
    }
}
