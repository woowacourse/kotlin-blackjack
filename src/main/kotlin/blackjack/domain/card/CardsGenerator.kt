package blackjack.domain.card

interface CardsGenerator {

    fun generate(): List<Card>
}
