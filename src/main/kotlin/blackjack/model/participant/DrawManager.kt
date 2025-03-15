package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.participant.UserCommand.HIT
import blackjack.model.participant.UserCommand.STAY
import blackjack.model.participant.UserCommand.UNKNOWN

class DrawManager {
    fun progressPlayerDraw(
        player: Player,
        cards: (Int) -> List<Card>,
        choice: () -> UserCommand,
        onCardReceived: (List<Card>) -> Unit,
    ) {
        while (true) {
            when (choice()) {
                HIT -> {
                    player.receiveCards(cards)
                    onCardReceived(player.cards)
                    if (!player.isDrawable) return
                }
                STAY -> break
                UNKNOWN -> throw IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.")
            }
        }
    }

    fun progressDealerDraw(
        dealer: Dealer,
        cards: (Int) -> List<Card>,
    ) {
        while (dealer.isDrawable) {
            dealer.receiveCards(cards)
        }
    }
}
