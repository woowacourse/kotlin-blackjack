package model

enum class FinalResult(val rate: Double) {
    BLACKJACK_WIN(0.5), WIN(1.0), PUSH(0.0), LOSE(-1.0)
}
