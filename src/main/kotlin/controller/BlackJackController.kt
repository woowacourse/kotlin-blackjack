package controller

import domain.card.Deck
import domain.person.Dealer
import domain.person.Persons
import domain.person.Player
import domain.result.Casino
import view.draw.DrawView
import view.onboarding.OnboardingView
import view.result.ResultView

class BlackJackController {
    fun runBlackJack() {
        val deck = Deck()
        val persons = Persons.getPersons(OnboardingView.requestInputNames())
        val bets = persons.players.map { OnboardingView.requestInputBet(it.name) }

        initialPhase(persons, deck)
        OnboardingView.printInitialSetting(persons)
        drawPhase(persons, deck)
        ResultView.printResult(Casino(persons, bets))
    }

    private fun initialPhase(persons: Persons, deck: Deck) {
        persons.dealer.apply {
            toNextState(deck.getCard())
            toNextState(deck.getCard())
        }
        persons.players.forEach { player ->
            player.toNextState(deck.getCard())
            player.toNextState(deck.getCard())
        }
    }

    private fun drawPhase(persons: Persons, deck: Deck) {
        val dealer = persons.dealer
        val players = persons.players

        players.forEach { playerTurn(it, deck) }
        dealerTurn(dealer, deck)
    }

    private fun dealerTurn(dealer: Dealer, deck: Deck) {
        if (dealer.isInProgress()) {
            dealer.toNextState(deck.getCard())
            DrawView.printDealerDrew()
            return
        }
        DrawView.printDealerDidNotDrew()
    }

    private fun playerTurn(player: Player, deck: Deck) {
        if (!player.isInProgress()) return
        val decision = DrawView.askPlayerDraw(player.name)
        if (!decision) {
            player.toStay()
            return
        }
        player.toNextState(deck.getCard())
        DrawView.printPlayerCards(player)
        playerTurn(player, deck)
    }
}
