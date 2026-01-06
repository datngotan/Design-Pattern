package beharvioral

data class User(val userName: String, val password: String)

abstract class Middleware {
    var next: Middleware? = null

    abstract fun handle(user: User)

    fun handleNext(user: User) {
        next?.handle(user)
    }

    companion object {
        fun link(first: Middleware, vararg chain: Middleware): Middleware {
            var head: Middleware = first
            for (nextChain in chain) {
                head.next = nextChain
                head = nextChain
            }
            return first
        }
    }
}


class UserExistsMiddleWare : Middleware() {
    val existUsers = listOf(
        User("dat", "1234"), User("abc", "1234")
    )

    override fun handle(user: User) {
        if (existUsers.any { it.userName == user.userName }) {
            println("The user is already exist")
        } else {
            handleNext(user)
        }
    }
}

class PasswordValidatorMiddleWare : Middleware() {
    override fun handle(user: User) {
        if (user.password.length <= 4) {
            println("The password must be longer than 4 characters")
        } else {
            handleNext(user)
        }
    }
}

fun main() {
    val newAccountValidator = Middleware.link(UserExistsMiddleWare(), PasswordValidatorMiddleWare())
    newAccountValidator.handle(User("dat1", "12345"))
}