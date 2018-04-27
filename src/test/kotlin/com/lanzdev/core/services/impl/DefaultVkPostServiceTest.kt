package com.lanzdev.core.services.impl

import com.lanzdev.MockitoExtension
import com.lanzdev.core.dao.VkPostDao
import com.lanzdev.core.validators.NumberValidator
import com.lanzdev.core.validators.StringValidator
import com.nhaarman.mockito_kotlin.*
import com.vk.api.sdk.objects.wall.WallPostFull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks

@ExtendWith(MockitoExtension::class)
internal class DefaultVkPostServiceTest {

    val screenName = "screenName"
    val limit = 1

    private val stringValidator: StringValidator = mock()
    private val numberValidator: NumberValidator = mock()
    private val dao: VkPostDao = mock()
    private val wallPostFull: WallPostFull = mock()

    @InjectMocks
    private lateinit var service: DefaultVkPostService

    @BeforeEach
    fun `set up mocks`() {
        reset(stringValidator, numberValidator, dao, wallPostFull)

        doNothing().whenever(stringValidator).validateEmptiness(any(), any())
        doNothing().whenever(numberValidator).validateInRange(any(), any(), any(), any())

        whenever(dao.selectLast(screenName)).thenReturn(wallPostFull)
        whenever(dao.selectItems(screenName, limit)).thenReturn(listOf(wallPostFull))
    }

    @Nested
    inner class WhenSelectLast {
        @Test
        fun `should call validate emptiness`() {
            service.selectLast(screenName)
            verify(stringValidator).validateEmptiness(`eq`(screenName), anyString())
        }

        @Test
        fun `should call select last on dao`() {
            service.selectLast(screenName)
            verify(dao).selectLast(screenName)
        }
    }

    @Nested
    inner class WhenSelectItems {
        @Test
        fun `should call validate emptiness`() {
            service.selectItems(screenName, limit)
            verify(stringValidator).validateEmptiness(eq(screenName), anyString())
        }

        @Test
        fun `should call validate in range`() {
            service.selectItems(screenName, limit)
            verify(numberValidator).validateInRange(eq(limit), anyInt(), anyInt(), anyString())
        }

        @Test
        fun `should call select items on dao`() {
            service.selectItems(screenName, limit)
            verify(dao).selectItems(screenName, limit)
        }
    }
}
