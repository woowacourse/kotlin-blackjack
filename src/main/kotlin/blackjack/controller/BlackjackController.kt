package blackjack.controller

import blackjack.model.betting.BettingManager
import blackjack.model.card.CardDeck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Dealer.Companion.DEFAULT_DEALER_NAME
import blackjack.model.participant.DrawManager
import blackjack.model.participant.Money
import blackjack.model.participant.ParticipantManager
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.model.winning.WinningManager
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private lateinit var participantManager: ParticipantManager
    private lateinit var drawManager: DrawManager
    private lateinit var bettingManager: BettingManager
    private lateinit var winningManager: WinningManager
    private lateinit var cardDeck: CardDeck

    fun run() {
        setup()

        val participants =
            participantManager.prepareParticipants(
                dealerName = DEFAULT_DEALER_NAME,
                cardDeck = cardDeck,
                getPlayerNames = inputView::getPlayers,
            )
        val (dealer, players) = participants.dealer to participants.players

        progressBetting(bettingManager, players)

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.showInitialCards())

        progressPlayersDraw(players)
        progressDealerDraw(dealer)

        endGame(participants)
    }

    private fun setup() {
        participantManager = ParticipantManager()
        drawManager = DrawManager()
        bettingManager = BettingManager()
        winningManager = WinningManager()
        cardDeck = CardDeck()
    }

    private fun progressBetting(
        bettingManager: BettingManager,
        players: Players,
    ) {
        outputView.displayInitialMoney()
        bettingManager.getPlayersMoney(players) { name ->
            Money(inputView.getBettingMoney(name.toString()))
        }
    }

    private fun progressPlayersDraw(players: Players) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.cards)
        }
        players.value.forEach { player ->
            drawManager.progressPlayerDraw(
                player = player,
                draw = cardDeck::draw,
                getCommand = { inputView.getIsRecieveMore(player.name.toString()) },
                onCardReceived = { cards -> outputView.displayParticipantCards(player.name, cards) },
            )
        }
    }

    private fun progressDealerDraw(dealer: Dealer) {
        drawManager.progressDealerDraw(dealer, cardDeck::draw)

        outputView.displayDealerDrawInfo(dealer.additionalDrawCount())
        outputView.displayParticipantInfo(dealer.name, dealer.cards, dealer.score())
    }

    private fun endGame(participants: Participants) {
        participants.players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.cards, player.score())
        }

        val winningResult = winningManager.result(participants)
        val bettingResult = bettingManager.result(winningResult, participants)

        outputView.displayProfitTitle()
        bettingResult.value.forEach { (name, money) ->
            outputView.displayProfit(name.toString(), money.value)
        }
    }
}
