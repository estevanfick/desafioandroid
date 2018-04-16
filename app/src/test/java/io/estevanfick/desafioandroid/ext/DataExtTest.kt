package io.estevanfick.desafioandroid.ext

import junit.framework.Assert
import org.junit.Test
import java.util.*

class DataExtTest {

    @Test
    fun formatData(){
        val date = Date("01/01/2018 13:00:00")
        Assert.assertEquals(date.toFormat(), "01/01/2018 13:00")
    }

    @Test
    fun formatDataChangeFormat(){
        val date = Date("01/01/2018 12:00:00")
        Assert.assertEquals(date.toFormat("dd/MM/yyyy"), "01/01/2018")
    }
}