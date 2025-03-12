package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player

interface BlackJackOutputView {
    fun printInitialDrawMessage(
        dealer: Dealer,
        players: List<Player>,
    )

    fun printPlayerDrawStatus(player: Player)

    fun printDealerDrawNotice()

    fun printPersonResult(person: Person)

    fun printGameResult(result: GameResult)

    fun printMessage(message: String?)
}
