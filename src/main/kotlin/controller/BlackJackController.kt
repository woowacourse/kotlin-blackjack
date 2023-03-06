package controller

import domain.card.Deck
import domain.constant.Decision
import domain.person.Dealer
import domain.person.PersonGenerator
import domain.person.Persons
import domain.person.Player
import domain.result.GameResult
import view.AdditionalCardView
import view.OnboardingView
import view.ResultView

class BlackJackController {
    fun runBlackJack() {
        val deck = Deck()
        val persons = runOnboarding(deck)
        runMain(deck, persons)
        runResult(persons)
    }

    private fun runOnboarding(deck: Deck): Persons {
        val persons = PersonGenerator.getPersons(OnboardingView.requestInputNames(), deck)
        OnboardingView.printInitialSetting(persons)
        return persons
    }

    private fun runMain(deck: Deck, persons: Persons) {
        persons.players.forEach { player -> handOutCardsToPlayer(deck, player) }
        handOutCardToDealer(deck, persons.dealer)
    }

    private fun handOutCardToDealer(deck: Deck, dealer: Dealer) {
        if (dealer.canReceiveMoreCard()) {
            dealer.receiveCard(deck.getCard())
            AdditionalCardView.printDealerGetMoreCard()
            return
        }
        AdditionalCardView.printDealerNoMoreCard()
    }

    private fun applyPlayerDecision(deck: Deck, player: Player) {
        val decision = Decision.of(AdditionalCardView.requestPlayerDecision(player.name))
            ?: throw IllegalArgumentException("y 또는 n 을 입력해야 합니다.")
        if (decision == Decision.NO) {
            player.rejectReceiveCard()
            return
        }
        player.receiveCard(deck.getCard())
        AdditionalCardView.printPlayerCards(player)
        if (player.isBust()) return
        handOutCardsToPlayer(deck, player)
    }

    private fun runResult(persons: Persons) {
        val gameResult = GameResult(persons)

        ResultView.printPersonsCards(persons.dealer, persons.players)
        ResultView.printFinalResult(gameResult)
    }
}
