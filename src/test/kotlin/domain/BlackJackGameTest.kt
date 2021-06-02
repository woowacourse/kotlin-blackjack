package domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @DisplayName("딜러가 버스트인 경우 결과 계산")
    @Test
    fun getGameResultWithBustDealer() {
        val result = getGameResult(bustDealer, listOf(blackJackPlayer, scoreTwentyPlayer, bustPlayer))
        Assertions.assertThat(result.map { it.second }).containsExactly(2000, 1000, -1000)
    }

    @DisplayName("딜러가 블랙잭인 경우 결과 계산")
    @Test
    fun getGameResultWithBlackJackDealer() {
        val result = getGameResult(blackJackDealer, listOf(blackJackPlayer, scoreTwentyPlayer, bustPlayer))
        Assertions.assertThat(result.map { it.second }).containsExactly(0, -1000, -1000)
    }

    @DisplayName("결과 계산")
    @Test
    fun getGameResult() {
        val result = getGameResult(scoreTenDealer, listOf(blackJackPlayer, scoreTwentyPlayer, scoreTenPlayer, bustPlayer))
        Assertions.assertThat(result.map { it.second }).containsExactly(2000, 1000,  0, -1000)
    }

}