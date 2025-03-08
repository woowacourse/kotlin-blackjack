package blackjack.domain.generator

import blackjack.domain.model.Card

fun interface CardGenerator {
    fun create(): List<Card>
}
