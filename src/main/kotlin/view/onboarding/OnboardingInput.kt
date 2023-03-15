package view.onboarding

object OnboardingInput {
    fun inputNames(showError: () -> Unit): List<String> {
        val input = readln()
        if (input.isBlank()) {
            showError()
            return inputNames(showError)
        }
        return input.split(',').map { it.trim() }
    }

    fun inputBets(showError: () -> Unit): Double {
        val input = readln().toDoubleOrNull()
        if (input == null) {
            showError()
            return inputBets(showError)
        }
        return input
    }
}
