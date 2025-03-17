package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.winning.PlayersResult

class Participants(
    val dealer: Dealer,
    val players: Players,
) {
    init {
        require(players.value.find { player -> player.name == dealer.name } == null) {
            "[ERROR] 플레이어와 딜러의 이름은 중복될 수 없습니다."
        }
    }

    val playersResult: PlayersResult get() = players.winningResult(dealer)

    companion object {
        fun create(
            dealerName: Name,
            distributeCards: (CardCount) -> List<Card>,
            getPlayerNames: () -> List<Name>,
        ): Participants {
            val dealer = Dealer.create(dealerName)
            val players = Players.from(getPlayerNames())

            dealer.receiveCards(distributeCards)
            players.value.forEach { player -> player.receiveCards(distributeCards) }

            return Participants(dealer, players)
        }
    }
}
