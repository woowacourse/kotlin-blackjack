package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState.ALIVE
import blackjack.model.hand.HandState.BLACKJACK
import blackjack.model.hand.HandState.BUST
import blackjack.model.participant.PlayerAction.HIT
import blackjack.model.participant.PlayerAction.STAY
import blackjack.model.participant.PlayerAction.UNKNOWN
import blackjack.model.winning.WinningState
import blackjack.model.winning.WinningState.LOSE
import blackjack.model.winning.WinningState.PUSH
import blackjack.model.winning.WinningState.WIN_BY_BLACKJACK
import blackjack.model.winning.WinningState.WIN_DEFAULT

class Player private constructor(
    name: Name,
    money: Money,
    hand: Hand,
) : Participant(name, money, hand) {
    override val isDrawable: Boolean get() = handState == ALIVE

    override fun showInitialCards(): List<Card> = cards.take(FIRST_SHOWN_COUNT)

    fun draw(
        newCards: (CardCount) -> List<Card>,
        choice: (Name) -> PlayerAction,
        onCardReceived: (Name, List<Card>) -> Unit,
    ) {
        while (true) {
            when (choice(name)) {
                HIT -> {
                    receiveCards(newCards)
                    onCardReceived(name, cards)
                    if (!isDrawable) return
                }
                STAY -> break
                UNKNOWN -> throw IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.")
            }
        }
    }

    fun winningState(dealer: Dealer): WinningState =
        when {
            handState == BLACKJACK && dealer.handState != BLACKJACK -> WIN_BY_BLACKJACK
            handState == BLACKJACK && dealer.handState == BLACKJACK -> PUSH
            dealer.handState == BLACKJACK -> LOSE

            handState == BUST -> LOSE
            dealer.handState == BUST -> WIN_DEFAULT

            score > dealer.score -> WIN_DEFAULT
            score < dealer.score -> LOSE

            else -> PUSH
        }

    companion object {
        val PLAYER_DEFAULT_MONEY = Money(1_000_000.0)
        private const val FIRST_SHOWN_COUNT = 2

        fun create(
            name: Name,
            money: Money = PLAYER_DEFAULT_MONEY,
            hand: Hand = Hand(),
        ): Player = Player(name, money, hand)
    }
}
