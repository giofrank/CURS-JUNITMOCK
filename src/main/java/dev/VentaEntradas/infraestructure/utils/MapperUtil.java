package dev.VentaEntradas.infraestructure.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MapperUtil {
    private final ModelMapper modelMapper;
    public <O, D> D mapper(O origen, Class<D> classDestino) {
        D destino = modelMapper.map(origen, classDestino);
        return destino;
    };

    public <S, T> List<T> mapperList(List<S> source, Class<T> targetClass) {
        if(source == null) {
            return null;
        }
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }
}