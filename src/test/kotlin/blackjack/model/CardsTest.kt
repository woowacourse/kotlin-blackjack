package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `에이스가 없을 때 카드의 총 합을 구하는 기능`() {
        val cards = buildCards(two, ten)
        val actual = cards.scoreSum()
        val expected = 12
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 11인 것이 유리할 때 카드의 총 합을 구하는 기능`() {
        val cards = buildCards(ace, ten)
        val actual = cards.scoreSum()
        val expected = 21
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 1인 것이 유리할 때 카드의 총 합을 구하는 기능`() {
        val cards = buildCards(ace, two, three, ten)
        val actual = cards.scoreSum()
        val expected = 16
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 총 합이 21을 초과하면 Busted된다`() {
        val cards = buildCards(ten, ten, two)
        val actual = cards.isBusted()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드 총 합이 21이면 MaxScore이다`() {
        val cards = buildCards(ten, ten, ace)
        val actual = cards.isMaxScore()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드가 2장이고 총 합이 21이면 Blackjack이다`() {
        val cards = buildCards(ace, ten)
        val actual = cards.isBlackJack()
        assertThat(actual).isEqualTo(true)
    }
}
