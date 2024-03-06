package blackjack.model

sealed class State(val hand: Hand)

open class Running(hand: Hand) : State(hand)

open class Finished(hand: Hand) : State(hand)

class Hit(hand: Hand) : Running(hand) {
    fun draw(card: Card): State {
        hand.addCard(card)
        return if (hand.isBust()) Bust(hand) else Hit(hand)
    }

    fun stay(): State = Stay(hand)
}

class Stay(hand: Hand) : Finished(hand)

class Blackjack(hand: Hand) : Finished(hand)

class Bust(hand: Hand) : Finished(hand)
