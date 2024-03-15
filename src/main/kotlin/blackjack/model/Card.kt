package blackjack.model

import blackjack.util.display

class Card(val pattern: Pattern, val number: CardNumber) {
    override fun toString(): String {
        return "${number.display()}${pattern.display()}"
    }
}
