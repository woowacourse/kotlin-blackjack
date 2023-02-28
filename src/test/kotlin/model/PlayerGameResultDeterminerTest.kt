package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards
import entity.GameResultType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerGameResultDeterminerTest {
    @Test
    fun `플레이어가 다른 플레이어 비교했을 때 21에 가장 가까운 수를 달성하여 승리`() {
        // given
        val user1 = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TWO), Card(CardType.SPADE, CardNumber.EIGHT),
                    Card(
                        CardType.CLUB, CardNumber.ACE
                    )
                )
            )
        )
        val user2 = User(Cards(mutableListOf(Card(CardType.CLUB, CardNumber.SEVEN), Card(CardType.SPADE, CardNumber.KING))))
        val user3 = User(Cards(mutableListOf(Card(CardType.CLUB, CardNumber.SEVEN), Card(CardType.SPADE, CardNumber.KING))))

        val playerGameResultDeterminer = PlayerGameResultDeterminer()
        val results = playerGameResultDeterminer.determine(user1, listOf(user1, user2, user3))

        // when
        val except = mapOf(GameResultType.WIN to 1)

        // then
        Assertions.assertThat(results.value).isEqualTo(except)
    }
}
