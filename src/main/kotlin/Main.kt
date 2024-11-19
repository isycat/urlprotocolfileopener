package com.isycat

import java.net.URI
import java.net.URLDecoder
import java.nio.file.Path
import kotlin.io.path.absolutePathString
import kotlin.io.path.createTempFile
import kotlin.io.path.outputStream

fun main(args: Array<String>) {
    // Open the given URL (args[1]), with the given executable(args[0])
    val target = URI(URLDecoder.decode(args[1].substringAfterLast("://open/?file="), "UTF-8"))
    val suffix = "." + target.path.substringAfterLast("/", "tmp").substringAfterLast(".")
    val file: Path = createTempFile(prefix = "url-file-opener-", suffix = suffix)
    target.toURL().openStream().use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
    Runtime.getRuntime().exec("${args[0]} ${file.absolutePathString()}")
}
