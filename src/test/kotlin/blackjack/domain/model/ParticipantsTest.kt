package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    @Test
    fun `플레이어들의 이름 중 중복이 있을 시 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { Participants(Dealer(), listOf(Player("A"), Player("A"))) }
    }

    @Test
    fun `딜러와 플레이어의 이름이 중복될 시 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { Participants(Dealer("딜러"), listOf(Player("딜러"))) }
    }

    @Test
    fun `플레이어별 최종 수익을 반환한다`() {
        val dealer = Dealer("딜러", Card(Suit.HEART, Rank.ACE)) // 11점
        val player1 = Player("A", 11111, Card(Suit.HEART, Rank.TWO)) // 2점
        val player2 = Player("B", 33333, Card(Suit.HEART, Rank.ACE)) // 11점
        val player3 = Player("C", 55555, Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)) // 21점
        val verdicts: Map<Player, Result> = dealer.getPlayerResults(listOf(player1, player2, player3))
        val profits: Map<Player, Int> = dealer.getPlayersProfits(verdicts)
        val actual: Map<Player, Int> =
            mapOf(
                player1 to -11111,
                player2 to 0,
                player3 to 55555,
            )
        assertThat(profits).isEqualTo(actual)
    }
}
