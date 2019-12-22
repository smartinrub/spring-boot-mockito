package com.sergiomartinrubio.springbootmockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FooServiceWithSpiesTest {

    @Spy
    private FooRepositoryImpl spy;

    @InjectMocks
    private FooServiceImpl fooService;

    @Test
    public void saveAndGetFirstNumberWithSpyTest() {
        doReturn(4).when(spy).getNumber(anyInt());
          // throws IndexOutOfBoundsException because the real method is first called
//        when(spy.getNumber(0)).thenReturn(4);

        Integer firstNumber = fooService.saveAndGetFirstNumber(3);

        assertThat(firstNumber).isEqualTo(4);
        verify(spy).saveNumber(3);
    }

}
