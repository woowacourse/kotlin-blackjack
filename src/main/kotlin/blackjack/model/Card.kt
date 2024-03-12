package blackjack.model

class Card(val pattern: Pattern, val number: CardNumber) {
    override fun toString(): String {
        return "${number.display()}${pattern.display()}"
    }
}
