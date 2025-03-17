package blackjack.view

import blackjack.model.Money
import blackjack.model.state.CardDrawDecision
import blackjack.model.user.Player

class InputView : BlackjackInput {
    override fun readPlayerNames(): List<String> {
        println(PLAYER_NAME_MESSAGE_GUIDE)
        val playerNames: List<String> = readln().split(PLAYER_NAME_DELIMITER).map { name -> name.trim() }
        if (playerNames.any { playerName -> playerName.isBlank() }) return readPlayerNames()
        if (playerNames.size != playerNames.toSet().size) return readPlayerNames()
        return playerNames
    }

    override fun readPlayerBetAmount(name: String): Money {
        println(PLAYER_BET_AMOUNT_MESSAGE_FORMAT.format(name))
        val amount = readln().toLongOrNull() ?: return readPlayerBetAmount(name)
        return Money.from(amount)
    }

    override fun readCardDrawChoice(player: Player): CardDrawDecision {
        println(PLAYER_CARD_DRAW_DECISION_MESSAGE_GUIDE.format(player.name))
        val response: String = readln().trim()
        if (response == PLAYER_CARD_DRAW_POSITIVE_RESPONSE) return CardDrawDecision.YES
        if (response == PLAYER_CARD_DRAW_NEGATIVE_RESPONSE) return CardDrawDecision.NO
        return readCardDrawChoice(player)
    }

    companion object {
        private const val PLAYER_NAME_MESSAGE_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val PLAYER_BET_AMOUNT_MESSAGE_FORMAT = "\n%s의 배팅 금액은?"
        private const val PLAYER_CARD_DRAW_DECISION_MESSAGE_GUIDE = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val PLAYER_NAME_DELIMITER = ","
        private const val PLAYER_CARD_DRAW_POSITIVE_RESPONSE = "y"
        private const val PLAYER_CARD_DRAW_NEGATIVE_RESPONSE = "n"
    }
}
