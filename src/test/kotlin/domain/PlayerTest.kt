package domain

import domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `21보다 작으면 더 받을 수 있도록 true를 반환한다`() {
        val player = Player(
            NameAndBet(Name("scott"), 0),
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                Card.of(CardCategory.SPADE, CardNumber.NINE)
            )
        )
        val actual = player.isPossibleDrawCard()
        assertThat(actual).isTrue
    }

    @Test
    fun `게임 승패를 알 수 있다`() {
        val player = Player(
            NameAndBet(Name("scott"), 0),
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                Card.of(CardCategory.SPADE, CardNumber.NINE)
            )
        )
        val dealer = Dealer(
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                Card.of(CardCategory.SPADE, CardNumber.NINE),
                Card.of(CardCategory.CLOVER, CardNumber.FOUR)
            )
        )
        val result = player.getGameResult(dealer)
        val expected = GameResult.LOSE
        assertThat(result).isEqualTo(expected)
    }
}
