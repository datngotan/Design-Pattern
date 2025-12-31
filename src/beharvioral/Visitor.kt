package beharvioral

import kotlin.jvm.Throws

// AST tree example

interface ExprVisitor<R> {
    fun visitNumber(numberLiteral: NumberLiteral): R
    fun visitBinaryExpr(binaryExpr: BinaryExpr): R
}

data class NumberLiteral(val value: Int) : Expr {
    override fun <R> accept(visitor: ExprVisitor<R>): R {
        return visitor.visitNumber(this)
    }
}

data class BinaryExpr(
    val left: Expr,
    val operator: String,
    val right: Expr
) : Expr {
    override fun <R> accept(visitor: ExprVisitor<R>): R {
        return visitor.visitBinaryExpr(this)
    }
}

interface Expr {
    fun <R> accept(visitor: ExprVisitor<R>): R
}

class PrintVisitor: ExprVisitor<String> {
    override fun visitNumber(numberLiteral: NumberLiteral): String {
        return numberLiteral.value.toString()
    }

    override fun visitBinaryExpr(binaryExpr: BinaryExpr): String {
        val left = binaryExpr.left.accept(this)
        val right = binaryExpr.right.accept(this)
        return "($left ${binaryExpr.operator} $right)"
    }
}

class EvalVisitor: ExprVisitor<Int> {
    override fun visitNumber(numberLiteral: NumberLiteral): Int {
        return numberLiteral.value
    }

    override fun visitBinaryExpr(binaryExpr: BinaryExpr): Int {
        val left = binaryExpr.left.accept(this)
        val right = binaryExpr.right.accept(this)
        return when(binaryExpr.operator) {
            "+" -> left + right
            "-" -> left - right
            "*" -> left * right
            "/" -> left / right
            else -> throw Exception("The operator is not supported")
        }
    }
}

fun main() {
    val expression = BinaryExpr(
        BinaryExpr(NumberLiteral(3), "+", NumberLiteral(4)),
        "*",
        NumberLiteral(3)
    )
    val printVisitor = PrintVisitor()
    println(printVisitor.visitBinaryExpr(expression))
    println(expression.accept(printVisitor))
    val evalVisitor = EvalVisitor()
    println(evalVisitor.visitBinaryExpr(expression))
    println(expression.accept(evalVisitor))
}