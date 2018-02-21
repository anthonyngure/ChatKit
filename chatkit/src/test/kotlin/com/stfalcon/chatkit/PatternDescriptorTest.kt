package com.stfalcon.chatkit

import com.stfalcon.chatkit.messages.MarkDown
import com.stfalcon.chatkit.messages.utils.MessageTextUtils
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * @author Maciej Madetko
 * @email maciej.madetko@ftlearning.com
 * Nikkei FT Learning Limited
 * @since 20/02/2018.
 */
class PatternDescriptorTest {

    @Test
    fun boldTest() {
        val content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
        val expected = "*Lorem ipsum dolor sit amet, consectetur adipiscing elit*"

        val pattern = MessageTextUtils.PatternDescriptor(content, surrounding = MarkDown.BOLD)
        assertEquals(expected,pattern.toTag())
    }

    @Test
    fun italicTest() {
        val content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
        val expected = "_Lorem ipsum dolor sit amet, consectetur adipiscing elit_"

        val pattern = MessageTextUtils.PatternDescriptor(content, surrounding = MarkDown.ITALIC)
        assertEquals(expected,pattern.toTag())
    }

    @Test
    fun strokeTest() {
        val content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
        val expected = "~Lorem ipsum dolor sit amet, consectetur adipiscing elit~"

        val pattern = MessageTextUtils.PatternDescriptor(content, surrounding = MarkDown.STROKE)
        assertEquals(expected,pattern.toTag())
    }

    @Test
    fun linkTest() {
        val content = "http://lorem.ipsum.dolor.com"
        val expected = "<http://lorem.ipsum.dolor.com>"

        val pattern = MessageTextUtils.PatternDescriptor(content, surrounding = MarkDown.LINK)
        assertEquals(expected,pattern.toTag())
    }

    @Test
    fun linkLabelTest() {
        val content = "http://lorem.ipsum.dolor.com"
        val label = "Lorem ipsum"
        val expected = "<http://lorem.ipsum.dolor.com|Lorem ipsum>"

        val pattern = MessageTextUtils.PatternDescriptor(content, label = label, surrounding = MarkDown.LINK)
        assertEquals(expected,pattern.toTag())
    }

    @Test
    fun linkLabelToDisplayTest() {
        val content = "http://lorem.ipsum.dolor.com"
        val label = "Lorem ipsum"
        val expected = "Lorem ipsum"

        val pattern = MessageTextUtils.PatternDescriptor(content, label = label, surrounding = MarkDown.LINK)
        assertEquals(expected,pattern.getLabelToDisplay())
    }

    @Test
    fun labelToDisplayTest() {
        val content = "http://lorem.ipsum.dolor.com"
        val label = null
        val expected = "http://lorem.ipsum.dolor.com"

        val pattern = MessageTextUtils.PatternDescriptor(content, label = label, surrounding = MarkDown.LINK)
        assertEquals(expected,pattern.getLabelToDisplay())
    }

    @Test
    fun unknownSurroundingTest() {
        val content = "http://lorem.ipsum.dolor.com"
        val expected = "http://lorem.ipsum.dolor.com"

        val pattern = MessageTextUtils.PatternDescriptor(content, surrounding = 12)
        assertEquals(expected,pattern.toTag())
    }

    @Test
    fun unknownSurroundingTestBoldFailure() {
        val content = "Lorem ipsum dolor sit amet"
        val expected = "*Lorem ipsum dolor sit amet*"

        val pattern = MessageTextUtils.PatternDescriptor(content, surrounding = 5)
        assertNotEquals(expected,pattern.toTag())
    }

    @Test
    fun unknownSurroundingTestStrokeFailure() {
        val content = "Lorem ipsum dolor sit amet"
        val expected = "~Lorem ipsum dolor sit amet~"

        val pattern = MessageTextUtils.PatternDescriptor(content, surrounding = MarkDown.ITALIC)
        assertNotEquals(expected,pattern.toTag())
    }
}