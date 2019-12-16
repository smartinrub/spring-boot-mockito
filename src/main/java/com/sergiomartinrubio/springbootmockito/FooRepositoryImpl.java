package com.sergiomartinrubio.springbootmockito;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class FooRepositoryImpl implements FooRepository {

    private List<Integer> numbers = Arrays.asList(4, 5, 2, 6, 8);

    @Override
    public String getFooMessage() {
        return "Hello World";
    }

    @Override
    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public Integer getNumber(int index) {
        return numbers.get(index);
    }

    @Override
    public void saveNumber(Integer number) {

    }
}
