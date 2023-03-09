package controller

import domain.person.Dealer
import domain.person.Persons
import domain.person.Player
import domain.result.GameResult

interface BlackJackGameBluePrint {
    fun makePersons(names: List<String>): Persons
    fun handOutCardsToDealer(dealer: Dealer, printDealerDrew: () -> Unit, printDealerDidNotDrew: () -> Unit)
    fun handOutCardsToPlayers(players: List<Player>, askDrawCard: (String) -> Boolean, printCards: (Player) -> Unit)
    fun drawResult(persons: Persons): GameResult
}
