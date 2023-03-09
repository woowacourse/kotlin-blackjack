package domain

import domain.card.Card
import domain.card.Deck
import domain.money.Money
import domain.person.Dealer
import domain.person.Participants
import domain.person.Player

class BlackJackBuilder(private val deck: Deck) {
    fun getInitialPersons(
        getPlayerNames: () -> List<String>,
    ): Participants {
        val dealer = Dealer(listOf<Card>())
        val players = getPlayerNames().map { name -> Player(listOf<Card>(), name) }
        return Participants(dealer, players)
    }

    fun getPlayerBettingMoneys(participants: Participants, getBettingMoney: (String) -> Int): Map<String, Money> =
        participants.players.associate { player -> player.name to Money(getBettingMoney(player.name)) }

    fun handOutInitialCards(participants: Participants, printInitParticipants: (Participants) -> Unit) {
        participants.dealer.receiveCard(deck.getCards(2))
        participants.players.forEach { it.receiveCard(deck.getCards(2)) }
        printInitParticipants(participants)
    }
}
