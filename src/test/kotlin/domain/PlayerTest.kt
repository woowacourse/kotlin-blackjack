package domain

import blackjack.domain.player.BattingMoney
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `21점 이상이 되기 전까지 추가적인 카드를 반복적으로 뽑을 수 있다`() {
        val player = Player(
            name = PlayerName("우기"),
            battingMoney = BattingMoney(1000),
        )

        player.drawCardsRepeatedly(
            isPlayerWantedAdditionalCards = { true }
        )

        assertThat(
            player.cards.getTotalCardsScore() >= 21
        ).isTrue
    }
}
