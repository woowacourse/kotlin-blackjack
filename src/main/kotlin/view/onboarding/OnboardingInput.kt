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
}
