package blackjack.domain

data class BlackJack(
    val cardDeck: CardDeck,
    val participants: Participants,
) {
    val result: List<Outcome>
        get() = participants.guests.map { guest -> Outcome.of(participants.dealer, guest) }

    val dealer: Dealer
        get() = participants.dealer

    val guests: List<Guest>
        get() = participants.guests
}
