package com.sergiomartinrubio.springbootmockito;

import java.util.List;

public interface FooRepository {

    String getFooMessage();

    List<Integer> getNumbers();

    Integer getNumber(int index);

    void saveNumber(Integer number);
}
