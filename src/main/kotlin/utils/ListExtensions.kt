package utils

fun <T> List<T>.forEachWhere(condition: (T) -> Boolean, action: (T) -> Unit) {
    this.filter(condition).forEach(action)
}