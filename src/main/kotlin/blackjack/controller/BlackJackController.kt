package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(private val cardDeck: CardDeck) {
    private lateinit var participants: Participants

    fun startGameFlow() {
        val dealer = Dealer()
        val players = InputView.inputPlayers()
        val participants = setupGame(dealer, players)
        displayGameInfo(dealer, participants)
    }

    private fun setupGame(
        dealer: Dealer,
        players: List<Player>,
    ): Participants {
        cardDeck.cardShuffle()
        betMoney(players)
        val participants = Participants(participants = listOf(dealer) + players)
        setParticipants(participants)
        return participants
    }

    private fun betMoney(players: List<Player>) {
        players.forEach { player ->
            val bettingMoney = InputView.inputPlayerBettingMoney(player.getName())
            player.setMoney(bettingMoney)
        }
    }

    private fun setParticipants(participants: Participants) {
        participants.getParticipants().forEach { participant ->
            participant.initDraw(cardDeck)
        }
    }

    private fun displayGameInfo(
        dealer: Dealer,
        participants: Participants,
    ) {
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

    fun playGame() {
        participants.getAlivePlayers().forEach { player ->
            playPlayer(player as Player)
        }
        playDealer()
    }

    private fun playPlayer(player: Player) {
        player.drawAdditionalCard(
            drawFunction = { cardDeck.draw() },
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
            drawFunction = { cardDeck.draw() },
            outputAction = {
                OutputView.outputDealerRule()
            },
        )
    }

    fun calculateResult() {
        val gameResult = GameResult()
        gameResult.calculateResult(
            participants.getDealer(),
            participants.getPlayers(),
        )
        displayResult(gameResult.calculateProfitResult())
    }

    private fun displayResult(profitResult: MutableMap<Participant, Double>) {
        OutputView.outputParticipantsHandCard(participants.getParticipants())
        OutputView.outputBlackResult()
        OutputView.outputProfitResult(profitResult)
    }
}
