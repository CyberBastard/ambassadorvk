package com.lanzdev.core.dao.impl

import com.lanzdev.MockitoExtension
import com.lanzdev.contexts.VkContext
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import com.vk.api.sdk.actions.Wall
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.objects.wall.WallPostFull
import com.vk.api.sdk.objects.wall.responses.GetResponse
import com.vk.api.sdk.queries.wall.WallGetQuery
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
internal class DefaultVkPostDaoTest {

    val screenName = "screenName"
    val one = 1
    val five = 5

    private val vk : VkApiClient = mock()
    private val actor: UserActor = mock()
    private val wall: Wall = mock()
    private val vkContext: VkContext = mock()
    private val wallPostFull1: WallPostFull = mock()
    private val wallPostFull2: WallPostFull = mock()
    private val wallGetQuery: WallGetQuery = mock()
    private val getResponse: GetResponse = mock()

    @InjectMocks
    private lateinit var dao: DefaultVkPostDao

    @BeforeEach
    fun `set up mocks`() {
        reset(vk, actor, wall, vkContext, wallPostFull1, wallPostFull2, wallGetQuery, getResponse)

        whenever(vkContext.vk).thenReturn(vk)
        whenever(vkContext.actor).thenReturn(actor)
        whenever(vk.wall()).thenReturn(wall)
        whenever(wall.get(actor)).thenReturn(wallGetQuery)
        whenever(wallGetQuery.filter(any())).thenReturn(wallGetQuery)
        whenever(wallGetQuery.domain(any())).thenReturn(wallGetQuery)
        whenever(wallGetQuery.execute()).thenReturn(getResponse)
        whenever(getResponse.items).thenReturn(listOf(wallPostFull1))
    }

    @Nested
    inner class WhenSelectLast {
        @Test
        fun `should return WallPostFull when selectLast() and vk returns 1 element`() {
            val actual = dao.selectLast(screenName)
            assertEquals(wallPostFull1, actual)
        }

        @Test
        fun `should return WallPostFull from top when selectLast() and vk returns list`() {
            whenever(getResponse.items).thenReturn(listOf(wallPostFull1, wallPostFull2))
            val actual = dao.selectLast(screenName)
            assertEquals(wallPostFull1, actual)
        }
    }

    @Nested
    inner class WhenSelectItems {
        @Test
        fun `should return 1 WallPostFull when select items and limit is 1`() {
            whenever(getResponse.items).thenReturn(listOf(wallPostFull1, wallPostFull2))
            val actual = dao.selectItems(screenName, one)
            assertAll(
                    { assertEquals(one, actual.size) },
                    { assertEquals(wallPostFull1, actual[0]) }
            )
        }

        @Test
        fun `should return 5 WallPostFull when select items and limit is 5`() {
            val forReturn = listOf(wallPostFull1, null, null, wallPostFull2, null, null ,null)
            whenever(getResponse.items).thenReturn(forReturn)
            val actual = dao.selectItems(screenName, five)
            assertAll(
                    { assertEquals(five, actual.size)},
                    { assertEquals(forReturn.take(5), actual)}
            )
        }
    }


}