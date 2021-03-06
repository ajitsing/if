package com.thenakliman.ifs;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class OrElseTest {
    @Test
    public void ifOrElse_thenGetElseGet_returnThenGetValue_whenSupplierEvaluatesToTrue() {

        final Integer value = If.orElse(() -> true, () -> 10, () -> 20);

        assertThat(value, is(10));
    }

    @Test
    public void ifOrElse_thenGetElseGet_returnElseGetValue_whenSupplierEvaluatesToFalse() {

        final Integer value = If.orElse(() -> false, () -> 10, () -> 20);

        assertThat(value, is(20));
    }

    @Test
    public void ifOrElse_thenCallElseCall_callThenCall_whenSupplierEvaluatesToTrue() {
        CallTestHelper callTestHelper = mock(CallTestHelper.class);

        If.orElse(() -> true, callTestHelper::thenCallMe1, callTestHelper::elseCallMe);

        verify(callTestHelper).thenCallMe1();
    }

    @Test
    public void ifOrElse_thenCallElseCall_callElseCall_whenSupplierEvaluatesToFalse() {
        CallTestHelper callTestHelper = mock(CallTestHelper.class);

        If.orElse(() -> false, callTestHelper::thenCallMe1, callTestHelper::elseCallMe);

        verify(callTestHelper).elseCallMe();
    }

    @Test
    public void ifOrElse_thenGetElseGet_returnthenGetValue_whenExpressionEvaluatesToTrue() {

        final Integer value = If.orElse(true, () -> 10, () -> 20);

        assertThat(value, is(10));
    }

    @Test
    public void ifOrElse_thenGetElseGet_returnElseGetValue_whenExpressionEvaluatesToFalse() {

        final Integer value = If.orElse(false, () -> 10, () -> 20);

        assertThat(value, is(20));
    }

    @Test
    public void ifOrElse_thenValueElseValue_returnThenValue_whenExpressionEvaluatesToTrue() {

        final Integer value = If.orElse(true, 10, 20);

        assertThat(value, is(10));
    }

    @Test
    public void ifOrElse_thenValueElseValue_returnElseValue_returnElseReturnValue_whenExpressionEvaluatesToFalse() {

        final Integer value = If.orElse(false, 10, 20);

        assertThat(value, is(20));
    }

    @Test
    public void ifOrElse_thenValueElseValue_returnThenValue__whenSupplierIsTrue() {

        final Integer value = If.orElse(() -> true, 10, 20);

        assertThat(value, is(10));
    }

    @Test
    public void ifOrElse_thenValueElseValue_returnElseValue_whenSupplierIsFalse() {

        final Integer value = If.orElse(() -> false, 10, 20);

        assertThat(value, is(20));
    }

    @Test
    public void ifOrElse_thenCallElseCall_callThenCall_whenExpressionEvaluatesToTrue() {
        CallTestHelper callTestHelper = mock(CallTestHelper.class);

        If.orElse(true, callTestHelper::thenCallMe1, callTestHelper::elseCallMe);

        verify(callTestHelper).thenCallMe1();
    }

    @Test
    public void ifOrElse_thenCallElseCall_callElseCall_whenExpressionEvaluatesToFalse() {
        CallTestHelper callTestHelper = mock(CallTestHelper.class);

        If.orElse(false, callTestHelper::thenCallMe1, callTestHelper::elseCallMe);

        verify(callTestHelper).elseCallMe();
    }

    @Test
    public void nullOrElse_thenGetElseGet_returnThenReturn_whenIsNull() {

        final Integer value = If.nullOrElse(null, () -> 10, () -> 20);

        assertThat(value, is(10));
    }

    @Test
    public void nullOrElse_thenGetElseGet_returnElseGet_whenNonNull() {

        final Integer value = If.nullOrElse("202", () -> 10, () -> 20);

        assertThat(value, is(20));
    }

    @Test
    public void nullOrElse_thenGetElseGet_returnElseGetValue_whenObjectIsNonNull() {
        Integer value = If.nullOrElse("1000", () -> 100, Integer::valueOf);

        assertThat(value, is(1000));
    }

    @Test
    public void nullOrElse_thenGetElseGet_returnThenGet_whenObjectIsNull() {
        String conditionValue = null;
        Integer value = If.nullOrElse(conditionValue, () -> 100, Integer::valueOf);

        assertThat(value, is(100));
    }

    @Test
    public void isNullThen_thenCall_callThenCall_whenObjectIsNull() {
        CallTestHelper callTestHelper = mock(CallTestHelper.class);

        If.isNullThen(null, callTestHelper::thenCallMe1);

        verify(callTestHelper).thenCallMe1();
    }

    @Test
    public void isNullThen_thenCall_doNotCall_whenObjectIsNonNull() {
        CallTestHelper callTestHelper = mock(CallTestHelper.class);

        If.isNullThen("something", callTestHelper::thenCallMe1);

        verify(callTestHelper, times(0)).thenCallMe1();
    }
}
