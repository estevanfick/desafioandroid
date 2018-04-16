package io.estevanfick.desafioandroid.ext

import java.text.SimpleDateFormat
import java.util.*


fun Date.toFormat(format: String = "dd/MM/yyyy HH:mm"): String? {
    val fmt = SimpleDateFormat(format)
    return fmt.format(this)
}