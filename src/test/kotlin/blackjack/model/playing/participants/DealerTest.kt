package blackjack.model.playing.participants

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.winning.PlayerWinning
import blackjack.model.winning.WinningResultStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 카드 패에 카드가 2장 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 BLACKJACK 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.JACK),
                Card(CardShape.SPADE, CardNumber.ACE),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.BLACKJACK)
    }

    @Test
    fun `딜러의 카드 패 합이 16 이하라면 카드 패의 상태는 HIT 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.HIT)
    }

    @Test
    fun `딜러의 카드 패 합이 17 이상 20 이하 라면 카드 패의 상태는 STAY 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.DIAMOND, CardNumber.FIVE),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.STAY)
    }

    @Test
    fun `딜러의 카드 패에 카드가 3장 이상 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 STAY 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.FIVE),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.DIAMOND, CardNumber.TEN),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.STAY)
    }

    @Test
    fun `카드 패 숫자의 합이 21 초과이면 카드 패의 상태는 BUST 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.FIVE),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.DIAMOND, CardNumber.SEVEN),
                Card(CardShape.DIAMOND, CardNumber.EIGHT),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.BUST)
    }

    @Test
    fun `딜러 카드 패의 합과 플레이어들의 카드 패의 합을 각각 비교해서 플레이어들의 승패 여부를 판단한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.EIGHT),
                Card(CardShape.SPADE, CardNumber.TEN),
            )
        val dealer = Dealer(cardHand)

        val playerResult = mapOf(PlayerName("해나") to 17, PlayerName("심지") to 20, PlayerName("악어") to 18, PlayerName("팡태") to 25)

        val result =
            PlayerWinning(
                mapOf(
                    PlayerName("해나") to WinningResultStatus.DEFEAT,
                    PlayerName("심지") to WinningResultStatus.VICTORY,
                    PlayerName("악어") to WinningResultStatus.PUSH,
                    PlayerName("팡태") to WinningResultStatus.DEFEAT,
                ),
            )
        val winningResult = dealer.judgePlayerWinningResult(playerResult)
        assertThat(winningResult).isEqualTo(result)
    }

    @Test
    fun `딜러 카드 패의 합이 21 초과 이고, 플레이어 카드 패의 합이 21 이하이면 플레이어가 승리한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.EIGHT),
                Card(CardShape.SPADE, CardNumber.NINE),
            )
        val dealer = Dealer(cardHand)

        val playerResult = mapOf(PlayerName("해나") to 17, PlayerName("심지") to 20, PlayerName("악어") to 18, PlayerName("팡태") to 25)

        val result =
            PlayerWinning(
                mapOf(
                    PlayerName("해나") to WinningResultStatus.VICTORY,
                    PlayerName("심지") to WinningResultStatus.VICTORY,
                    PlayerName("악어") to WinningResultStatus.VICTORY,
                    PlayerName("팡태") to WinningResultStatus.DEFEAT,
                ),
            )
        val winningResult = dealer.judgePlayerWinningResult(playerResult)
        assertThat(winningResult).isEqualTo(result)
    }
}
