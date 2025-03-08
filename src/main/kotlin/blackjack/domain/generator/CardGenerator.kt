package blackjack.domain.generator

import blackjack.domain.model.card.Card

fun interface CardGenerator {
    fun create(): List<Card>
}
