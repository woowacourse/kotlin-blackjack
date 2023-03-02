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
    fun `플레이어 카드 숫자의 합이 21이고 딜러 카드 숫자의 합이 21이면 무승부이다`() {
        // given
        val user1 = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )
        val dealer = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )

        val playerGameResultDeterminer = PlayerGameResultDeterminer()
        val results = playerGameResultDeterminer.determine(dealer, listOf(user1))

        // when
        val except = mapOf(user1 to GameResultType.DRAW)

        // then
        Assertions.assertThat(results.value).isEqualTo(except)
    }
    @Test
    fun `플레이어 카드 숫자의 합이 22이고 딜러 카드 숫자의 합이 22이면 무승부이다`() {
        // given
        val user1 = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.TWO)
                )
            )
        )
        val dealer = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.TWO)
                )
            )
        )

        val playerGameResultDeterminer = PlayerGameResultDeterminer()
        val results = playerGameResultDeterminer.determine(dealer, listOf(user1))

        // when
        val except = mapOf(user1 to GameResultType.DRAW)

        // then
        Assertions.assertThat(results.value).isEqualTo(except)
    }
    @Test
    fun `플레이어 카드 숫자의 합이 19이고 딜러 카드 숫자의 합이 22이면 플레이어의 승리이다`() {
        // given
        val user1 = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.NINE)
                )
            )
        )
        val dealer = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.TWO)
                )
            )
        )

        val playerGameResultDeterminer = PlayerGameResultDeterminer()
        val results = playerGameResultDeterminer.determine(dealer, listOf(user1))

        // when
        val except = mapOf(user1 to GameResultType.WIN)

        // then
        Assertions.assertThat(results.value).isEqualTo(except)
    }
    @Test
    fun `플레이어 카드 숫자의 합이 21이고 딜러 카드 숫자의 합이 20이면 플레이어의 승리이다`() {
        // given
        val user1 = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.HEART, CardNumber.SEVEN),
                )
            )
        )
        val dealer = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN)
                )
            )
        )

        val playerGameResultDeterminer = PlayerGameResultDeterminer()
        val results = playerGameResultDeterminer.determine(dealer, listOf(user1))

        // when
        val except = mapOf(user1 to GameResultType.WIN)

        // then
        Assertions.assertThat(results.value).isEqualTo(except)
    }
    @Test
    fun `플레이어 카드 숫자의 합이 20이고 딜러 카드 숫자의 합이 21이면 플레이어의 패배이다`() {
        // given
        val user1 = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN)
                )
            )
        )
        val dealer = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )

        val playerGameResultDeterminer = PlayerGameResultDeterminer()
        val results = playerGameResultDeterminer.determine(dealer, listOf(user1))

        // when
        val except = mapOf(user1 to GameResultType.LOSE)

        // then
        Assertions.assertThat(results.value).isEqualTo(except)
    }
    @Test
    fun `플레이어 카드 숫자의 합이 22이고 딜러 카드 숫자의 합이 21이면 플레이어의 패배이다`() {
        // given
        val user1 = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.TWO)
                )
            )
        )
        val dealer = User(
            Cards(
                mutableListOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )

        val playerGameResultDeterminer = PlayerGameResultDeterminer()
        val results = playerGameResultDeterminer.determine(dealer, listOf(user1))

        // when
        val except = mapOf(user1 to GameResultType.LOSE)

        // then
        Assertions.assertThat(results.value).isEqualTo(except)
    }
}
