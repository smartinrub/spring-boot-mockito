package com.sergiomartinrubio.springbootmockito;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FooServiceImpl implements FooService {

    final FooRepository fooRepository;

    public FooServiceImpl(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public String getString() {
        return fooRepository.getFooMessage();
    }

    @Override
    public List<Integer> getNumbers() {
        return fooRepository.getNumbers();
    }

    @Override
    public Integer getNumber(int index) {
        return fooRepository.getNumber(index);
    }

    @Override
    public void saveNumber(Integer number) {
        fooRepository.saveNumber(number);
    }

    @Override
    public Integer saveAndGetFirstNumber(Integer number) {
        fooRepository.saveNumber(number);
        return fooRepository.getNumber(0);
    }

}
