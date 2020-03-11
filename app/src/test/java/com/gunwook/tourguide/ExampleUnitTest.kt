package com.gunwook.tourguide

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testJob() = runBlocking {
        val job = Job()
        val coroutineScope : CoroutineScope = CoroutineScope(Dispatchers.Default + job)
        coroutineScope.launch {
            val jobTwo = launch {
                println("Job two scope for start")
                for (index in 0..10) {
                    if (isActive) {
                        println("Job two scope index $index")
                        delay(1)
                    } else {
                        break
                    }
                }
                println("Job two scope for end")
            }

        }

        coroutineScope.launch {
            val jobTwo = launch {
                println("Job one scope for start")
                for (index in 0..10) {
                    if (isActive) {
                        println("Job one scope index $index")
                        delay(1)
                    } else {
                        break
                    }
                }
                println("Job one scope for end")
            }
        }

        println("Job Cancel")

        job.cancel()

        delay(30) // 30ms test only.
    }
}
