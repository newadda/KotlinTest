import kotlinx.coroutines.selects.select
import org.junit.jupiter.api.Test

class SqlSelectBuilder{
    private val columns = mutableListOf<String>()

    private lateinit var table: String

    private lateinit var condition:Condition

    fun from(table: String) {
        this.table = table
    }

    fun build():String{
        if (!::table.isInitialized) {
            throw IllegalStateException("Failed to build an sql select - target table is undefined")
        }
        return toString()
    }

    override fun toString(): String {
        val columnsToFetch =
            if (columns.isEmpty()) {
                "*"
            } else {
                columns.joinToString(", ")
            }



        return "select $columnsToFetch from $table where $condition"
    }

    fun select(vararg columns: String) {
        if (columns.isEmpty()) {
            throw IllegalArgumentException("At least one column should be defined")
        }
        if (this.columns.isNotEmpty()) {
            throw IllegalStateException("Detected an attempt to re-define columns to fetch. "
                    + "Current columns list: "
                    + "${this.columns}, new columns list: $columns")
        }
        this.columns.addAll(columns)
    }


    fun where(initializer: Condition.() -> Unit) {
        condition = And().apply(initializer)
    }

}


abstract class Condition{
    fun eq(initializer: Condition.() -> Unit) {
        addCondition(And().apply(initializer))
    }
    fun and(initializer: Condition.() -> Unit) {
        addCondition(And().apply(initializer))
    }

    fun or(initializer: Condition.() -> Unit) {
        addCondition(Or().apply(initializer))
    }
    infix fun String.eq(value: Any?) {
        addCondition(Eq(this,value))
    }
    abstract fun addCondition(condition: Condition):Unit;
}
open class CompositeCondition(private val sqlOperator: String) : Condition() {
    private val conditions = mutableListOf<Condition>()

    override fun addCondition(condition: Condition) {
        conditions += condition
    }

    override fun toString(): String {
        return if (conditions.size == 1) {
            conditions.first().toString()
        } else {
            conditions.joinToString(prefix = "(", postfix = ")", separator = " $sqlOperator ")
        }
    }
}

class Eq(private val column: String, private val value: Any?) : Condition() {

    init {
        if (value != null && value !is Number && value !is String) {
            throw IllegalArgumentException(
                "Only <null>, numbers and strings values can be used in the 'where' clause")
        }
    }

    override fun addCondition(condition: Condition) {
        throw IllegalStateException("Can't add a nested condition to the sql 'eq'")
    }

    override fun toString(): String {
        return when (value) {
            null -> "$column is null"
            is String -> "$column = '$value'"
            else -> "$column = $value"
        }
    }
}
class And : CompositeCondition("and")
class Or : CompositeCondition("or")


fun query(init:SqlSelectBuilder.()->Unit):SqlSelectBuilder{
     return SqlSelectBuilder().apply(init);

}


@Test

fun main()
{
    val test=query {
        select("1","1")
        from("myTable")
        where {
            "column1" eq 4
            or {
                "column2" eq null
                "column3" eq 42
            }
        }
    }.build()

    println(test)
    /// select 1, 1 from myTable where (column1 = 4 and (column2 is null or column3 = 42))
}