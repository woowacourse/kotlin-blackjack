package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participants.* // ktlint-disable no-wildcard-imports
import blackjack.domain.result.Outcome
import blackjack.domain.result.Outcome.Companion.getOutcome

data class BlackJack(
    val cardDeck: CardDeck,
    val participants: Participants,
) {
    val dealer: Dealer
        get() = participants.dealer

    val guests: List<Guest>
        get() = participants.guests

    fun getResult(): List<Outcome> = participants.guests.map { guest -> getOutcome(guest, participants.dealer) }

    fun betMoney(input: (String) -> Int?): UsersBettingMoney {
        return UsersBettingMoney(
            participants.guests.map {
                it to BettingMoney(input(it.name.toString())!!)
            }.toMap(),
        )
    }
}
