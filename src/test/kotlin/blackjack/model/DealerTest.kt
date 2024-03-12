package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    private val cardDeck = CardDeck.cardDeck

    @Test
    fun `딜러의 카드 패 상태를 구한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
            )

        val dealer = Dealer(cardHand)

        assertThat(dealer.getState()).isEqualTo(CardHandState.HIT)
    }

    @Test
    fun `상태가 HIT 이면 카드 한 장을 더 뽑는다`() {
        val dealer =
            Dealer(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.SPADE, CardNumber.TWO),
                ),
            )

        if (dealer.getState() == CardHandState.HIT) {
            dealer.runPhase(RandomCardGenerator(cardDeck))
        }

        assertThat(dealer.cardHand.hand.size).isEqualTo(3)
    }

    @Test
    fun `상태가 HIT 이 아니면 카드를 더 뽑지 않는다`() {
        val dealer =
            Dealer(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.HEART, CardNumber.NINE),
                ),
            )

        if (dealer.getState() == CardHandState.HIT) {
            dealer.runPhase(RandomCardGenerator(cardDeck))
        }

        assertThat(dealer.cardHand.hand.size).isEqualTo(2)
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
                    PlayerName("악어") to WinningResultStatus.DRAW,
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

    @Test
    fun `딜러의 최종 승패를 판단한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.EIGHT),
                Card(CardShape.SPADE, CardNumber.TEN),
            )
        val dealer = Dealer(cardHand)

        val playerWinning =
            PlayerWinning(
                mapOf(
                    PlayerName("심지") to WinningResultStatus.DEFEAT,
                    PlayerName("해나") to WinningResultStatus.DRAW,
                    PlayerName("악어") to WinningResultStatus.VICTORY,
                    PlayerName("팡태") to WinningResultStatus.VICTORY,
                ),
            )

        val actual = dealer.judgeDealerWinningResult(playerWinning)
        assertThat(actual).isEqualTo(
            DealerWinning(
                mapOf(
                    WinningResultStatus.VICTORY to 1,
                    WinningResultStatus.DRAW to 1,
                    WinningResultStatus.DEFEAT to 2,
                ),
            ),
        )
    }
}
