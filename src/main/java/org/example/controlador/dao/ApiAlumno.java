package org.example.controlador.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.example.modulo.Alumno;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ApiAlumno {
    public static String urlAPI = "http://localhost:9000/";
//    public static String urlAPI = "http://172.23.64.1:9000/";

    public List<Alumno> obtenerTodosAlumnos() {
        String url = urlAPI + "api/alumnos";
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = cliente.newCall(request).execute()) {
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                String jsonResponse = response.body().string();
                Type listType = new TypeToken<List<Alumno>>() {
                }.getType();
                return gson.fromJson(jsonResponse, listType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Alumno obtenerAlumno(long id) {
        String url = urlAPI + "api/alumno/busca/" + id;
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = cliente.newCall(request).execute()) {
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                String jsonResponse = response.body().string();
                return gson.fromJson(jsonResponse, Alumno.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertarAlumno(Alumno alumno) {
        OkHttpClient cliente = new OkHttpClient();
        Gson gson = new Gson();
        RequestBody body = RequestBody.create((gson.toJson(alumno)), MediaType.parse("application/json"));
        Request request = new Request.Builder().url(urlAPI + "api/alumno/inserta").post(body).build();
        Call call = cliente.newCall(request);
        try (Response response = call.execute()) {
            if (response.isSuccessful())
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modificarAlumno(long id, Alumno alumno) {
        OkHttpClient cliente = new OkHttpClient();
        Gson gson = new Gson();
        RequestBody body = RequestBody.create((gson.toJson(alumno)), MediaType.parse("application/json"));
        Request request = new Request.Builder().url(urlAPI + "api/alumno/modifica/" + id).put(body).build();
        Call call = cliente.newCall(request);
        try (Response response = call.execute()) {
            if (response.isSuccessful())
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarAlumno(long id) {
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder().url(urlAPI + "api/alumno/elimina/" + id).delete().build();
        Call call = cliente.newCall(request);
        try (Response response = call.execute()) {
            if (response.isSuccessful())
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
