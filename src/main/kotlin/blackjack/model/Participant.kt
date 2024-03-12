package blackjack.model

sealed class Participant(private val name: ParticipantName, private val hand: Hand) {
    fun getName() = name

    fun getCards() = hand.getCards()

    fun getCardsSum() = hand.calculateCardsSum()

    fun drawCard(card: Card) {
        hand.addCard(card)
    }

    fun getState() = hand.calculateState()

    fun isGameFinished() = getState() == State.Blackjack || getState() == State.Bust
}

class Dealer(name: ParticipantName = ParticipantName(ParticipantName.DEALER_NAME), hand: Hand) :
    Participant(name, hand) {
    fun shouldDrawCardForDealer(threshold: Int = THRESHOLD_DRAW_FOR_DEALER): Boolean = getCardsSum() <= threshold

    fun playRound(
        updateDealerInfo: (Dealer) -> Unit,
        deck: CardDeck,
    ) {
        while (!isGameFinished() && shouldDrawCardForDealer()) {
            drawCard(deck.pick())
            updateDealerInfo(this)
        }
    }

    fun calculateWinningStateWith(player: Player): WinningState {
        if (isWinAgainstPlayer(player)) return WinningState(1, 0)
        if (isLoseAgainstPlayer(player)) return WinningState(0, 1)
        return WinningState(0, 0)
    }

    private fun isWinAgainstPlayer(player: Player): Boolean {
        val state = this.getState()
        if (state == State.Blackjack && player.getState() != State.Blackjack) return true
        if (state == State.Normal && this.getCardsSum() > player.getCardsSum()) return true
        if (state == State.Bust && player.getState() == State.Bust) return true
        return false
    }

    private fun isLoseAgainstPlayer(player: Player): Boolean {
        val state = this.getState()
        if (state == State.Bust && player.getState() != State.Bust) return true
        if (state == State.Normal && this.getCardsSum() < player.getCardsSum() && player.getState() != State.Bust) return true
        return false
    }

    companion object {
        private const val THRESHOLD_DRAW_FOR_DEALER = 16
    }
}

class Player(name: ParticipantName, hand: Hand) : Participant(name, hand) {
    fun playRound(
        requestMoreCards: (ParticipantName) -> Boolean,
        updatePlayerInfo: (Player) -> Unit,
        deck: CardDeck,
    ) {
        while (!isGameFinished() && requestMoreCards(this.getName())) {
            drawCard(deck.pick())
            updatePlayerInfo(this)
        }
    }

    fun calculateWinningStateWith(dealer: Dealer): WinningState {
        if (isWinAgainstDealer(dealer)) return WinningState(1, 0)
        if (isLoseAgainstDealer(dealer)) return WinningState(0, 1)
        return WinningState(0, 0)
    }

    private fun isWinAgainstDealer(dealer: Dealer): Boolean {
        val state = this.getState()
        if (state == State.Blackjack && dealer.getState() != State.Blackjack) return true
        if (state == State.Normal && this.getCardsSum() > dealer.getCardsSum()) return true
        return false
    }

    private fun isLoseAgainstDealer(dealer: Dealer): Boolean {
        val state = this.getState()
        if (state == State.Bust) return true
        if (state == State.Normal && this.getCardsSum() < dealer.getCardsSum() && dealer.getState() != State.Bust) return true
        return false
    }
}
