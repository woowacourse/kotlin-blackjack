package blackjack.domain

import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("peto")
    }

    @Test
    fun `플레이어가 게임을 시작하면 카드를 2장 지급받는다`() {
        val fixture = trumpCardFixture()
        fixture.forEach {
            player.addCard(it)
        }
        assertThat(player.cards).containsExactly(*fixture.toTypedArray())
    }

    @Test
    fun `플레이어 카드의 총합이 21을 초과하면 버스트된다`() {
        repeat(3) {
            player.addCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(player.isBust()).isEqualTo(true)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되지 않았으면 카드 총합에 10을 더한다`(){
        player.addCard(TrumpCard(CardTier.ACE, Shape.DIA))
        player.addCard(TrumpCard(CardTier.NINE, Shape.HEART))

        assertThat(player.sum()).isEqualTo(20)
    }

    @Test
    fun `에이스 카드를 가지고 버스트 되었으면 카드 총합을 유지한다`(){
        player.addCard(TrumpCard(CardTier.ACE, Shape.DIA))
        player.addCard(TrumpCard(CardTier.SEVEN, Shape.HEART))
        player.addCard(TrumpCard(CardTier.NINE, Shape.HEART))

        assertThat(player.sum()).isEqualTo(17)
    }
}
