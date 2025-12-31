package structural

// Component
interface Notifier {
    fun execute()
}

// Concrete Components
class FacebookNotifier : Notifier {
    override fun execute() {
        println("Send notification to Facebook")
    }
}

class SlackNotifier : Notifier {
    override fun execute() {
        println("Send notification to Slack")
    }
}

class SMSNotifier : Notifier {
    override fun execute() {
        println("Send notification to SMS")
    }
}

// Base Decorator
abstract class NotifierDecorator(
    protected val wrapper: Notifier
) : Notifier {

    override fun execute() {
        wrapper.execute()
    }
}

// Concrete Decorators
class FacebookDecorator(
    notifier: Notifier
) : NotifierDecorator(notifier) {

    override fun execute() {
        super.execute()
        println("Additional Facebook decoration")
    }
}

class SlackDecorator(
    notifier: Notifier
) : NotifierDecorator(notifier) {

    override fun execute() {
        super.execute()
        println("Additional Slack decoration")
    }
}

class SMSDecorator(
    notifier: Notifier
) : NotifierDecorator(notifier) {

    override fun execute() {
        super.execute()
        println("Additional SMS decoration")
    }
}

fun main() {
    val decorator = FacebookDecorator(SlackDecorator(SMSDecorator(FacebookNotifier())))
    decorator.execute()
}