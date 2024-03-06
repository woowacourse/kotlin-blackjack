package blackjack.model

sealed class State(val deck: Cards)

open class Running(deck: Cards) : State(deck)

open class Finished(deck: Cards) : State(deck)

class Hit(deck: Cards) : Running(deck) {
    fun draw(card: Card): State {
        deck.cards.add(card)
        return if (deck.isBust()) Bust(deck) else Hit(deck)
    }

    fun stay(): State = Stay(deck)
}

class Stay(deck: Cards) : Finished(deck)

class Blackjack(deck: Cards) : Finished(deck)

class Bust(deck: Cards) : Finished(deck)
