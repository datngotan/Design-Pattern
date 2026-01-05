package structural

data class User(val name: String)

interface Validator<T> {
    fun validate(t: T)
}

class EmailValidator : Validator<User> {
    override fun validate(t: User) {
        println("Validating ${t.name} user email...")
    }
}

class PasswordValidator : Validator<User> {
    override fun validate(t: User) {
        println("Validating ${t.name} user password...")
    }
}

class CompositeUserValidator(private val validators: List<Validator<User>>) : Validator<User> {
    override fun validate(t: User) {
        validators.forEach {
            it.validate(t)
        }
    }
}

fun main() {
    val compositeUserValidator = CompositeUserValidator(
        listOf(
            EmailValidator(), PasswordValidator()
        )
    )
    compositeUserValidator.validate(User("Dat"))
}