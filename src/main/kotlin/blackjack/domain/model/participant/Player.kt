package blackjack.domain.model.participant

import blackjack.domain.model.Action
import blackjack.domain.model.Bet
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.Hand
import blackjack.domain.model.result.GameResult

class Player(
    name: String,
    cards: List<Card>,
) : Participant(name, cards) {
    lateinit var bet: Bet

    constructor(name: String, vararg cards: Card) : this(name, cards.toList())

    constructor(name: String, betAmount: Int, vararg cards: Card) : this(name, cards.toList()) {
        this.bet = Bet(betAmount)
    }

    override fun canHit(): Boolean {
        return !isBusted()
    }

    fun placeBet(input: (Player) -> Bet) {
        bet = input(this)
    }

    tailrec fun processHits(
        deck: Deck,
        input: (Player) -> Action,
        output: (Player) -> Unit,
    ) {
        if (!canHit()) return
        if (input(this) == Action.STAND) {
            if (openHand().size == Hand.STARTING_HAND_SIZE) output(this)
            return
        }
        accept(deck.draw())
        output(this)
        processHits(deck, input, output)
    }

    override fun compareAgainst(other: Participant): GameResult {
        if (isBusted()) return GameResult.LOSE
        if (other.isBusted()) return GameResult.WIN
        return super.compareAgainst(other)
    }
}
