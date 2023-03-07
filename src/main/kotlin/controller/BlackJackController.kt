package controller

import domain.Decision
import domain.card.Deck
import domain.constant.GameState.HIT
import domain.person.Dealer
import domain.person.Player
import domain.result.GameResult
import view.RequestView
import view.ResultView

class BlackJackController {
    fun runBlackJack() {
        val deck = Deck.create()
        val dealer = Dealer(deck.getCards(2))
        val players = runOnboarding(deck, dealer)
        runMain(deck, dealer, players)
        runResult(dealer, players)
    }

    private fun runOnboarding(deck: Deck, dealer: Dealer): List<Player> {
        val players = RequestView.requestInputNames().map { name -> Player(deck.getCards(2), name) }
        ResultView.printInitialSetting(players, dealer)
        return players
    }

    private fun runMain(deck: Deck, dealer: Dealer, players: List<Player>) {
        players.forEach { player -> handOutCardsToPlayer(deck, player) }
        handOutCardToDealer(deck, dealer)
    }

    private fun handOutCardsToPlayer(deck: Deck, player: Player) {
        var decision = Decision.YES
        while (player.isState(HIT) && decision == Decision.YES) {
            decision = applyPlayerDecision(deck, player)
        }
    }

    private fun handOutCardToDealer(deck: Deck, dealer: Dealer) {
        if (dealer.isState(HIT)) {
            dealer.receiveCard(deck.getCards(1))
            ResultView.printDealerGetMoreCard()
            return
        }
        ResultView.printDealerNoMoreCard()
    }

    private fun applyPlayerDecision(deck: Deck, player: Player): Decision {
        val decision = Decision.of(RequestView.requestPlayerDecision(player.name))
        if (decision == Decision.NO) {
            return decision
        }
        player.receiveCard(deck.getCards(1))
        ResultView.printPlayerCards(player)
        return decision
    }

    private fun runResult(dealer: Dealer, players: List<Player>) {
        val gameResult = GameResult(dealer, players)

        ResultView.printPersonsCardsResult(dealer, players)
        ResultView.printFinalResult(gameResult)
    }
}
