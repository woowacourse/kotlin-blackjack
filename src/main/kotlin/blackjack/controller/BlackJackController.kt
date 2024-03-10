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
    private const val INITIAL_DEALING_COUNT = 2

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
        initialCardDealing(participants, cardDeck)
        OutputView.outputCardDistribution(participants)
        decidePlayerDecisions(participants, cardDeck)
        judgeDealerDraw(participants.dealer, cardDeck)
    }

    private fun initialCardDealing(
        participants: Participants,
        cardDeck: CardDeck,
    ) {
        repeat(INITIAL_DEALING_COUNT) {
            participants.getParticipants().forEach { participant ->
                participant.draw(cardDeck.pickCard())
            }
        }
        participants.getParticipants().forEach { participant ->
            participant.changeStateToHit()
        }
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

    private fun judgeDealerDraw(
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        if (dealer.gameInformation.score <= 16) {
            dealer.gameInformation.drawCard(cardDeck.pickCard())
        }
    }

    private fun displayGameResult(participants: Participants) {
        OutputView.outputGameScores(participants)
    }
}
