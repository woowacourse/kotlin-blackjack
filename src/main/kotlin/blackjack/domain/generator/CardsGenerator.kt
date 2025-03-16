package blackjack.domain.generator

import blackjack.domain.model.card.Card

fun interface CardsGenerator {
    fun createCards(): List<Card>
}
