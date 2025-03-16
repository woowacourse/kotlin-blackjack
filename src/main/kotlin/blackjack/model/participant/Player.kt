package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState
import blackjack.model.participant.PlayerAction.HIT
import blackjack.model.participant.PlayerAction.STAY
import blackjack.model.participant.PlayerAction.UNKNOWN

class Player private constructor(
    name: Name,
    money: Money,
    hand: Hand,
) : Participant(name, money, hand) {
    override val isDrawable: Boolean get() = handState == HandState.ALIVE

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
