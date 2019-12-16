package com.sergiomartinrubio.springbootmockito;

import java.util.List;

public interface FooService {

    String getString();

    List<Integer> getNumbers();

    Integer getNumber(int index);

    void saveNumber(Integer number);
}
