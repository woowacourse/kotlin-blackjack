package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.GameResult
import blackjack.model.Participant
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
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
        val participants = mutableListOf<Participant>()
        participants.add(dealer)
        participants.addAll(players)
        return Participants(participants)
    }

    private fun initializePlayers(): List<Player> {
        val playerNames = InputView.inputPlayersName()
        return playerNames.map { name ->
            Player(name)
        }
    }

    private fun blackJackGameStart(participants: Participants) {
        val cardDeck = CardDeck()
        participants.getDealer().initialCardDealing(participants, cardDeck)
        OutputView.outputCardDistribution(participants)
        judgePlayersDraw(participants.getPlayers(), cardDeck)
        participants.getDealer().judgeDrawOrNot(cardDeck) { OutputView.outputDealerDraw(participants.getDealer()) }
        OutputView.outputGameScores(participants)
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

    private fun displayGameResult(participants: Participants) {
        val gameResult = GameResult(participants)
        OutputView.outputGameResult(gameResult)
    }
}
