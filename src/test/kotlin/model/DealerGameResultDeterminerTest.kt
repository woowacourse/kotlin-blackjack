package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards
import entity.GameResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerGameResultDeterminerTest {
    @Test
    fun `딜러가 두명의 플레이어보다 점수가 높고 한명의 플레이어보다 점수가 낮아 2승 1패 기록`() {
        // given
        val dealer = User(Cards(mutableListOf(Card(CardType.DIAMOND, CardNumber.THREE), Card(CardType.CLUB, CardNumber.NINE), Card(CardType.DIAMOND, CardNumber.EIGHT))))
        val user1 = User(Cards(mutableListOf(Card(CardType.HEART, CardNumber.TWO), Card(CardType.SPADE, CardNumber.EIGHT), Card(CardType.CLUB, CardNumber.ACE))))
        val user2 = User(Cards(mutableListOf(Card(CardType.CLUB, CardNumber.SEVEN), Card(CardType.SPADE, CardNumber.KING))))
        val user3 = User(Cards(mutableListOf(Card(CardType.CLUB, CardNumber.SEVEN), Card(CardType.SPADE, CardNumber.KING))))

        val dealerGameResultDeterminer = DealerGameResultDeterminer()
        val results = dealerGameResultDeterminer.determine(dealer, listOf(user1, user2, user3))

        // when
        val except = mapOf(GameResultType.WIN to 2, GameResultType.LOSE to 1)

        // then
        assertThat(results.value).isEqualTo(except)
    }
}
