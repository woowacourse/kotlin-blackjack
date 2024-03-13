package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(private val cardDeck: CardDeck) {
    private lateinit var participants: Participants
    private lateinit var gameResult: GameResult

    fun startGameFlow() {
        val dealer = Dealer()
        val players = InputView.inputPlayers()
        participants =
            Participants(
                participants = listOf(dealer) + players,
            )
        setParticipants(participants)
        OutputView.outputParticipantsName(
            dealerName = dealer.getName(),
            players = participants.getPlayers(),
        )
        OutputView.outputDealerCurrentHandCard(
            name = dealer.getName(),
            firstCard = dealer.openFirstCard(),
        )
        OutputView.outputPlayersCurrentHandCard(participants.getPlayers())
    }

    private fun setParticipants(participants: Participants) {
        participants.getParticipants().forEach { participant ->
            participant.initDraw(cardDeck)
        }
    }

    fun playGame() {
        participants.getAlivePlayers().forEach { player ->
            playPlayer(player as Player)
        }
        playDealer()
    }

    private fun playPlayer(player: Player) {
        player.drawAdditionalCard(
            deck = cardDeck,
            inputDecision = { name ->
                InputView.inputPlayerDecision(name)
            },
            outputAction = { player ->
                OutputView.outputPlayerCurrentHandCard(player)
            },
        )
    }

    private fun playDealer() {
        val dealer = participants.getDealer()
        dealer.drawAdditionalDraw(
            deck = cardDeck,
            outputAction = {
                OutputView.outputDealerRule()
            },
        )
    }

    fun calculateResult() {
        gameResult = GameResult()
        gameResult.calculateResult(
            participants.getDealer(),
            participants.getPlayers(),
        )
    }

    fun showResult() {
        OutputView.outputParticipantsHandCard(participants.getParticipants())
        OutputView.outputBlackResult()
        OutputView.outputDealerResult(
            dealerName = participants.getDealer().getName(),
            dealerResults = gameResult.getDealerResults(),
        )
        OutputView.outputPlayersResult(
            playersResult = gameResult.getPlayerResults(),
        )
    }
}
