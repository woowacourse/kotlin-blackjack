package blackjack.domain.generator

import blackjack.domain.model.card.Card

interface CardsGenerator {
    fun createCards(): List<Card>
}
