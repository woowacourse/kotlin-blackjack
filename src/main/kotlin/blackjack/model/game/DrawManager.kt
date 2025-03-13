package blackjack.model.game

import blackjack.model.card.Card
import blackjack.model.game.UserCommand.HIT
import blackjack.model.game.UserCommand.STAY
import blackjack.model.game.UserCommand.UNKNOWN
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player

class DrawManager {
    fun progressPlayerDraw(
        player: Player,
        draw: (Int) -> List<Card>,
        getCommand: () -> UserCommand,
        onCardReceived: (List<Card>) -> Unit,
    ) {
        while (true) {
            when (getCommand()) {
                HIT -> {
                    player.recieveCards(draw)
                    onCardReceived(player.cards)
                    if (!player.isDrawable()) return
                }
                STAY -> break
                UNKNOWN -> throw IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.")
            }
        }
    }

    fun progressDealerDraw(
        dealer: Dealer,
        draw: (Int) -> List<Card>,
    ) {
        while (dealer.isDrawable()) {
            dealer.recieveCards(draw)
        }
    }
}
