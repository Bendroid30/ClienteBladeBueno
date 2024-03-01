package org.example.controlador;

import java.util.List;

public interface ICRUD <T>{
    void agregar(T object) throws Exception;
    T buscar(T object);
    void eliminar(T object);
    void modificar(long id, T object);
}
