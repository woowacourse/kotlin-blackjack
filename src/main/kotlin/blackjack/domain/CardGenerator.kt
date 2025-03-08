package blackjack.domain

interface CardGenerator {
    fun generate(): List<Card>
}
