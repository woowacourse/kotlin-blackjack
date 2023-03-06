package blackjack.domain

import blackjack.domain.carddeck.CardDeck

class Participants(names: List<String>, private val cardDeck: CardDeck) {
    val dealer: Dealer = Dealer(makeCardBunch())
    val players: List<Player> = names.map { Player(it, makeCardBunch()) }

    private fun makeCardBunch(): CardBunch = CardBunch(cardDeck.drawCard(), cardDeck.drawCard())
}
