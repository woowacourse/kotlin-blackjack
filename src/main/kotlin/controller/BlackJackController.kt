package controller

import domain.card.CardsGenerator
import domain.card.Deck
import domain.constant.Decision
import domain.person.Dealer
import domain.person.Player
import domain.result.GameResult
import view.RequestView
import view.ResultView

class BlackJackController {
    fun runBlackJack() {
        val deck = Deck(CardsGenerator().createCards()).apply { this.shuffleDeck() }
        val dealer = Dealer()
        val players = runOnboarding(deck, dealer)
        runMain(deck, dealer, players)
        runResult(dealer, players)
    }

    private fun runOnboarding(deck: Deck, dealer: Dealer): List<Player> {
        val players = RequestView.requestInputNames().map { name -> Player(name) }
        dealer.receiveCard(deck.getCard(), deck.getCard())
        players.forEach {
            it.receiveCard(deck.getCard(), deck.getCard())
        }
        ResultView.printInitialSetting(players, dealer)
        return players
    }

    private fun runMain(deck: Deck, dealer: Dealer, players: List<Player>) {
        players.forEach { player -> handOutCardsToPlayer(deck, player) }
        handOutCardToDealer(deck, dealer)
    }

    private fun handOutCardsToPlayer(deck: Deck, player: Player) {
        while (player.isStateHit()) {
            applyPlayerDecision(deck, player)
        }
    }

    private fun handOutCardToDealer(deck: Deck, dealer: Dealer) {
        if (dealer.isStateHit()) {
            dealer.receiveCard(deck.getCard())
            ResultView.printDealerGetMoreCard()
            return
        }
        ResultView.printDealerNoMoreCard()
    }

    private fun applyPlayerDecision(deck: Deck, player: Player) {
        val decision = Decision.of(RequestView.requestPlayerDecision(player.name))
        if (decision == Decision.NO) {
            player.rejectReceiveCard()
            return
        }
        player.receiveCard(deck.getCard())
        ResultView.printPlayerCards(player)
    }

    private fun runResult(dealer: Dealer, players: List<Player>) {
        val gameResult = GameResult(dealer, players)

        ResultView.printPersonsCardsResult(dealer, players)
        ResultView.printFinalResult(gameResult)
    }
}
