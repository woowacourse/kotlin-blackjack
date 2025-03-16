package blackjack.controller

import blackjack.model.betting.BettingMachine
import blackjack.model.card.CardDeck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Dealer.Companion.DEFAULT_DEALER_NAME
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val cardDeck = CardDeck()
        val participants =
            Participants.create(
                dealerName = DEFAULT_DEALER_NAME,
                distributeCards = cardDeck::draw,
                getPlayerNames = inputView::getPlayers,
            )
        val (dealer, players) = participants.dealer to participants.players

        val bettingMachine = progressBetting(players)

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.showInitialCards())

        progressPlayersDraw(players, cardDeck)
        progressDealerDraw(dealer, cardDeck)

        endGame(participants, bettingMachine)
    }

    private fun progressBetting(players: Players): BettingMachine {
        val bettingMachine = BettingMachine()

        outputView.displayInitialMoney()
        bettingMachine.betMoney(players) { name ->
            inputView.getBettingMoney(name)
        }

        return bettingMachine
    }

    private fun progressPlayersDraw(
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.cards)
        }
        players.draw(
            newCards = cardDeck::draw,
            choice = { inputView.getIsReceiveMore(it) },
            onCardReceived = { name, cards -> outputView.displayParticipantCards(name, cards) },
        )
    }

    private fun progressDealerDraw(
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        dealer.draw(cardDeck::draw)

        outputView.displayDealerDrawInfo(dealer.additionalDrawCount)
        outputView.displayParticipantInfo(dealer.name, dealer.cards, dealer.score)
    }

    private fun endGame(
        participants: Participants,
        bettingMachine: BettingMachine,
    ) {
        participants.players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.cards, player.score)
        }
        val gameResult = participants.winningResult()
        val bettingResult = bettingMachine.result(gameResult, participants)

        outputView.displayProfitTitle()
        bettingResult.value.forEach { (name, money) ->
            outputView.displayProfit(name, money)
        }
    }
}
