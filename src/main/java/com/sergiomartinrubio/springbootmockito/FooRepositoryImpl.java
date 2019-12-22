package com.sergiomartinrubio.springbootmockito;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FooRepositoryImpl implements FooRepository {

    private List<Integer> numbers = new ArrayList<>();

    @Override
    public String getFooMessage() {
        return "Hello World";
    }

    @Override
    public List<Integer> getNumbers() {
        return this.numbers;
    }

    @Override
    public Integer getNumber(int index) {
        return numbers.get(index);
    }

    @Override
    public void saveNumber(Integer number) {
        numbers.add(number);
    }
}
