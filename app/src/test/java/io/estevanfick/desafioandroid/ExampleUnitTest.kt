package io.estevanfick.desafioandroid

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val tt = mock(test::class.java)
        `when`(tt.getNumber()).thenReturn(4)
        assertEquals(tt.getNumber(), 2 + 2)
    }

    class test {

        fun getNumber(): Int{
            return 5
        }
    }
}
