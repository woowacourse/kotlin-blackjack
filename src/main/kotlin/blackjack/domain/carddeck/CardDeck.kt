package blackjack.domain.carddeck

import blackjack.domain.Card

interface CardDeck {

    fun drawCard(): Card
}
