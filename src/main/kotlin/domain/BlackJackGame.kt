package domain

import controller.BlackJackGameBluePrint
import domain.card.Deck
import domain.person.Dealer
import domain.person.Decision
import domain.person.Persons
import domain.person.Player
import domain.result.GameResult

class BlackJackGame : BlackJackGameBluePrint {
    private val deck = Deck()

    override fun makePersons(names: List<String>): Persons = Persons.getPersons(names, deck)

    override fun handOutCardsToDealer(dealer: Dealer, printDealerDrew: () -> Unit, printDealerDidNotDrew: () -> Unit) {
        if (dealer.canReceiveMoreCard()) {
            dealer.receiveCard(deck.getCard())
            printDealerDrew()
            return
        }
        printDealerDidNotDrew()
    }

    override fun handOutCardsToPlayers(
        players: List<Player>,
        askDrawCard: (String) -> Boolean,
        printCards: (Player) -> Unit,
    ) {
        players.forEach {
            playPlayerTurn(it, askDrawCard, printCards)
        }
    }

    private fun playPlayerTurn(
        player: Player,
        askDrawCard: (String) -> Boolean,
        printCards: (Player) -> Unit,
    ) {
        val decision = Decision.of(askDrawCard(player.name))
        if (decision == Decision.NO) return

        player.receiveCard(deck.getCard())
        printCards(player)
        if (player.isBust()) return

        playPlayerTurn(player, askDrawCard, printCards)
    }

    override fun drawResult(persons: Persons): GameResult = GameResult(persons)
}
