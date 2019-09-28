package org.sergei.functional.third

import org.junit.Test
import kotlin.math.sqrt

class ThirdSolutionTest {

    @Test
    fun `Set contains a given element`() {
        val set: Set = { it == 1 }
        assert(set contains 1)
    }

    @Test
    fun `Singleton set contains a given element`() {
        val set: Set = singletonSet(2)
        assert(set contains 2)
    }

    @Test
    fun `Union set contains both elements`() {
        val set = singletonSet(1) union singletonSet(2)
        assert(set contains 1)
        assert(set contains 2)
    }

    @Test
    fun `Intersect set works correctly`() {
        val set = singletonSet(1) union singletonSet(2) intersect singletonSet(1)
        assert(set contains 1)
        assert((set contains 2).not())
    }

    @Test
    fun `Diff set works correctly`() {
        val set = singletonSet(1) union singletonSet(2) union singletonSet(3) diff singletonSet(2)
        assert(set contains 1)
        assert(set contains 3)
        assert((set contains 2).not())
    }

    @Test
    fun `Filter set works correctly`() {
        val set = (singletonSet(1) union singletonSet(2)).filter { it == 1 }
        assert(set contains 1)
        assert((set contains 2).not())
    }

    @Test
    fun `Exists set works correctly`() {
        val set = singletonSet(1) union singletonSet(2)
        assert(set.exists { it == 1 })
        assert(set.exists { it == 2 })
    }

    @Test
    fun `Map set works correctly`() {
        val set = singletonSet(1).map { it * -1 }
        assert(set contains -1)
    }

    @Test
    fun `For all set works correctly`() {
        val set = singletonSet(2) union singletonSet(4) union singletonSet(6)
        assert(set.forAll { it % 2 == 0 })
        assert((set.forAll { sqrt(it.toFloat()).toInt() % 2 == 0 }).not())
    }

    @Test
    fun `Content works correctly`() {
        val set = singletonSet(1) union singletonSet(2)
        assert(set.contents == "{1,2}")
    }
}