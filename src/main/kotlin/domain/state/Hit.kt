package domain.state

import domain.card.Card
import domain.card.Cards
import domain.participant.BettingMoney

class Hit(hand: Cards, bettingMoney: BettingMoney) : Running(hand, bettingMoney) {
    override fun draw(card: Card): State {
        hand.add(card)
        return next(cards = hand)
    }

    override fun stay(): State {
        return Stay(hand, bettingMoney)
    }

    private fun next(cards: Cards): State {
        return when {
            cards.getScore().isBlackJack() -> BlackJack(cards, bettingMoney)
            cards.getScore().isBurst() -> Burst(cards, bettingMoney)
            else -> Hit(cards, bettingMoney)
        }
    }
}
