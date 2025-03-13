package blackjack.model.game

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.game.UserCommand.HIT
import blackjack.model.game.UserCommand.STAY
import blackjack.model.game.UserCommand.UNKNOWN
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players

class ParticipantManager {
    fun prepareDealer(
        dealerName: String,
        cardDeck: CardDeck,
    ): Dealer {
        val dealer = Dealer.create(dealerName)
        dealer.recieveCards(cardDeck::draw)

        return dealer
    }

    fun preparePlayers(
        playerNames: List<String>,
        cardDeck: CardDeck,
    ): Players {
        val players = Players.from(playerNames)
        players.value.forEach { player -> player.recieveCards(cardDeck::draw) }

        return players
    }

    fun progressPlayerDrawUntilFinished(
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
