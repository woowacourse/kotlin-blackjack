package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.participants.Participants

class BlackJackSetting(
    val cardDeck: CardDeck,
    val participants: Participants,
) {
    val dealer: Dealer
        get() = participants.dealer

    val guests: List<Guest>
        get() = participants.guests
}
