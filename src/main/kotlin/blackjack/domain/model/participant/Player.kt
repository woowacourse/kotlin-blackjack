package blackjack.domain.model.participant

import blackjack.domain.model.Action
import blackjack.domain.model.Bet
import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.Hand

class Player(
    name: String,
    cards: List<Card>,
) : Participant(name, cards) {
    private lateinit var bet: Bet

    constructor(name: String, vararg cards: Card) : this(name, cards.toList())

    constructor(name: String, bet: Int, vararg cards: Card) : this(name, cards.toList()) {
        this.bet = Bet(bet)
    }

    override fun canHit(): Boolean {
        return !isBusted()
    }

    fun placeBet(input: (Player) -> Bet) {
        bet = input(this)
    }

    fun processHits(
        deck: Deck,
        input: (Player) -> Action,
        output: (Player) -> Unit,
    ) {
        if (!canHit()) return
        if (input(this) == Action.STAND) {
            if (showHand().size == Hand.STARTING_HAND_SIZE) output(this)
            return
        }
        accept(deck.draw())
        output(this)
        processHits(deck, input, output)
    }

    fun computeProfitAgainst(dealer: Dealer): Int {
        val gameResult: GameResult = compareAgainst(dealer)
        return Math.round(bet.amount * gameResult.profitRate).toInt()
    }

    fun compareAgainst(dealer: Dealer): GameResult {
        if (isBusted()) return GameResult.LOSE
        if (dealer.isBusted()) return GameResult.WIN

        if (isBlackJack() && dealer.isBlackJack()) return GameResult.PUSH
        if (isBlackJack()) return GameResult.BLACKJACK

        val point: Int = computePoint()
        val dealerPoint: Int = dealer.computePoint()
        return when {
            point > dealerPoint -> GameResult.WIN
            point < dealerPoint -> GameResult.LOSE
            else -> GameResult.PUSH
        }
    }
}
