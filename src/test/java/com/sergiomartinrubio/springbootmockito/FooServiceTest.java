package com.sergiomartinrubio.springbootmockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class FooServiceTest {

    @Mock
    private FooRepository fooRepository;

    @InjectMocks
    private FooServiceImpl fooService;

    @Test
    public void getStringTest() {
        MockitoAnnotations.initMocks(this);
        // GIVEN
        given(fooRepository.getFooMessage()).willReturn("Hello");

        // WHEN
        String message = fooService.getString();

        // THEN
        assertThat(message).isEqualTo("Hello");
        then(fooRepository).should().getFooMessage();
    }

    @Test
    public void getNumbersTest() {
        // GIVEN
        when(fooRepository.getNumbers()).thenReturn(List.of(1, 8, 3, 5));

        // WHEN
        List<Integer> numbers = fooService.getNumbers();

        // THEN
        assertThat(numbers).contains(1, 8);
        verify(fooRepository).getNumbers();
    }

    @Test
    public void getNumberTest() {
        // GIVEN
        when(fooRepository.getNumber(eq(0))).thenReturn(8);
        when(fooRepository.getNumber(eq(1))).thenThrow(new ArrayIndexOutOfBoundsException());

        // WHEN
        Integer firstNumber = fooService.getNumber(0);

        // THEN
        assertThat(firstNumber).isEqualTo(8);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> fooService.getNumber(1));
    }

    @Test
    public void getNumberGivenEmptyListTest() {
        // GIVEN
        when(fooRepository.getNumber(anyInt())).thenThrow(new ArrayIndexOutOfBoundsException());

        // WHEN
        // THEN
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> fooService.getNumber(0));
        verify(fooRepository).getNumber(anyInt());
        verifyNoMoreInteractions(fooRepository);
    }

    @Test
    public void getNumberMultipleTimesTest() {
        // GIVEN
        when(fooRepository.getNumber(anyInt())).thenReturn(8, 3, 5);

        // WHEN
        Integer firstNumber = fooService.getNumber(0);
        Integer secondNumber = fooService.getNumber(1);
        Integer thirdNumber = fooService.getNumber(2);

        // THEN
        assertThat(firstNumber).isEqualTo(8);
        assertThat(secondNumber).isEqualTo(3);
        assertThat(thirdNumber).isEqualTo(5);
        verify(fooRepository, times(3)).getNumber(anyInt());
    }

    @Test
    public void getNumberVerifyInOrderTest() {
        // GIVEN
        when(fooRepository.getNumber(anyInt())).thenReturn(8, 3, 5);

        // WHEN
        fooService.getNumber(1);
        fooService.getNumber(0);
        fooService.getNumber(2);

        // THEN
        InOrder inOrder = inOrder(fooRepository);
        inOrder.verify(fooRepository).getNumber(1);
        inOrder.verify(fooRepository).getNumber(0);
        inOrder.verify(fooRepository).getNumber(2);
    }

    @Test
    public void getNumbersWithResetTest() {
        when(fooRepository.getNumbers()).thenReturn(List.of(4, 6, 3));
        List<Integer> numbers = fooService.getNumbers();
        assertThat(numbers).contains(4, 6, 3);

        reset(fooRepository);

        List<Integer> emptyNumbers = fooService.getNumbers();
        assertThat(emptyNumbers).isEmpty();
    }

}