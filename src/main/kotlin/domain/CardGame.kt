package domain

import model.CardDeck
import model.Cards
import model.Dealer
import model.Name
import model.Participant
import model.Player
import model.Players

class CardGame(private val cardDeck: CardDeck) {
    fun initPlayers(names: List<Name>): Players {
        return Players(names.map { Player(pickTwice(), it) })
    }

    fun initDealer(): Dealer {
        return Dealer(pickTwice())
    }

    fun pickTwice(): Cards = Cards(
        buildSet {
            add(cardDeck.drawCard())
            add(cardDeck.drawCard())
        },
    )

    fun drawPlayersCard(players: Players, input: (String) -> Boolean, output: (Participant) -> Unit) {
        players.filter { it.isPossibleDrawCard() }.forEach {
            drawPlayerCard(it, input, output)
        }
    }

    private fun drawPlayerCard(participant: Participant, input: (String) -> Boolean, output: (Participant) -> Unit) {
        while (participant.isPossibleDrawCard() && input(participant.name.value)) {
            participant.cards.add(cardDeck.drawCard())
            output(participant)
        }
    }

    fun drawDealerCard(participant: Participant, output: () -> Unit) {
        while (participant.isPossibleDrawCard()) {
            output()
            participant.cards.add(cardDeck.drawCard())
        }
    }
}
