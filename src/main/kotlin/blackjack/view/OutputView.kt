package blackjack.view

import blackjack.domain.model.Card
import blackjack.domain.model.Dealer
import blackjack.domain.model.Player

class OutputView {
    fun requestPlayerNames() {
        println(MESSAGE_ENTER_PLAYER_NAMES)
    }

    fun requestPlayerAction() {
        println(MESSAGE_ENTER_PLAYER_YES_OR_NO)
    }

    fun printInitialDeals(dealer: Dealer, players: List<Player>) {
        println(
            MESSAGE_INITIAL_HAND_DISTRIBUTED.format(
                dealer.name,
                players.map(Player::name).joinToString(PLAYER_CARDS_DELIMITER),
                2
            )
        )
    }

    fun printPlayerStatus(player: Player) {
        println(buildPlayerStatus(player))
    }

    private fun buildPlayerStatus(player: Player): String {
        var status = ""
        status += player.name + PLAYER_NAME_STATUS_SEPARATOR
        status += player.cards.joinToString(PLAYER_CARDS_DELIMITER) { card: Card ->
            card.rank.value + card.suit.value
        }
        return status
    }

    companion object {
        private const val MESSAGE_ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_ENTER_PLAYER_YES_OR_NO = "%s은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
        private const val MESSAGE_INITIAL_HAND_DISTRIBUTED = "%s와(과) %s에게 %s장의 카드를 나누었습니다."

        private const val PLAYER_CARDS_DELIMITER = ", "
        private const val PLAYER_NAME_STATUS_SEPARATOR = " 카드: "
    }
}
