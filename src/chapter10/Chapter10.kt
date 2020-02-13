package chapter10

abstract class Sql(table:String, columns: Array<Int>) {

    abstract fun generate(): String
}

class CreateSql(table:String, columns: Array<Int>) : Sql(table, columns) {

    override fun generate(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}