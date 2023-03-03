package controller

import domain.card.Deck
import domain.constant.Decision
import domain.person.Dealer
import domain.person.GameState
import domain.person.Player
import domain.result.GameResult
import view.MainView
import view.OnboardingView
import view.ResultView

class BlackJackController {
    fun runBlackJack() {
        val deck = Deck()
        val dealer = Dealer()
        val players = runOnboarding(deck, dealer)
        runMain(deck, dealer, players)
        runResult(dealer, players)
    }

    private fun runOnboarding(deck: Deck, dealer: Dealer): List<Player> {
        val players = OnboardingView.requestInputNames().map { name -> Player(name) }
        dealer.receiveCard(deck.getCard(), deck.getCard())
        players.forEach {
            it.receiveCard(deck.getCard(), deck.getCard())
        }
        OnboardingView.printInitialSetting(players, dealer)
        return players
    }

    private fun runMain(deck: Deck, dealer: Dealer, players: List<Player>) {
        players.forEach { player -> handOutCardsToPlayer(deck, player) }
        handOutCardToDealer(deck, dealer)
    }

    private fun handOutCardsToPlayer(deck: Deck, player: Player) {
        while (player.gameState == GameState.HIT) {
            applyPlayerDecision(deck, player)
        }
    }

    private fun handOutCardToDealer(deck: Deck, dealer: Dealer) {
        if (dealer.gameState == GameState.HIT) {
            dealer.receiveCard(deck.getCard())
            MainView.printDealerGetMoreCard()
            return
        }
        MainView.printDealerNoMoreCard()
    }

    private fun applyPlayerDecision(deck: Deck, player: Player) {
        val decision = Decision.of(MainView.requestPlayerDecision(player.name))
        if (decision == Decision.NO) {
            player.rejectReceiveCard()
            return
        }
        player.receiveCard(deck.getCard())
        MainView.printPlayerCards(player)
    }

    private fun runResult(dealer: Dealer, players: List<Player>) {
        val gameResult = GameResult(dealer, players)

        ResultView.printPersonsCards(dealer, players)
        ResultView.printFinalResult(gameResult)
    }
}
