package codes.romao.paging_movies.core

import android.content.res.Resources

fun Int.dp(resources: Resources): Int {
    val scale = resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}