package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.DrawDecision
import blackjack.model.GameState
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
        displayGameResult(participants)
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
        decidePlayerDecisions(participants, cardDeck)
    }

    private fun decidePlayerDecisions(
        participants: Participants,
        cardDeck: CardDeck,
    ) {
        participants.players.forEach { player ->
            decidePlayerDecision(player, cardDeck)
        }
    }

    private fun decidePlayerDecision(
        player: Player,
        cardDeck: CardDeck,
    ) {
        while (player.gameInformation.state is GameState.Running) {
            val wantDraw = readDrawDecision(player.name)
            if (wantDraw) {
                player.gameInformation.drawCard(cardDeck.pickCard())
                OutputView.outputParticipantCard(player)
            }
            if (!wantDraw) player.gameInformation.changeState(GameState.Finished.STAY)
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

    private fun displayGameResult(participants: Participants) {
        OutputView.outputGameScores(participants)
    }
}
