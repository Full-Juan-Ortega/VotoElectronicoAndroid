package com.ispc.ispcappvoto.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ispc.ispcappvoto.DatabaseHelper;
import com.ispc.ispcappvoto.models.Votacion;

import java.util.ArrayList;

public class VotacionController {

    private DatabaseHelper DatabaseHelper;
    private String NOMBRE_TABLA = "votaciones";

    public VotacionController(Context contexto) {
        DatabaseHelper = new DatabaseHelper(contexto);
    }


    public int eliminarVotacion(Votacion votacion) {

        SQLiteDatabase baseDeDatos = DatabaseHelper.getWritableDatabase();
        String[] argumentos = {String.valueOf(votacion.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaVotacion(Votacion votacion) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = DatabaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", votacion.getNombre());
        valoresParaInsertar.put("descripcion", votacion.getDescripcion());
        valoresParaInsertar.put("valoracion", votacion.getValoracion());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Votacion votacionEditada) {
        SQLiteDatabase baseDeDatos = DatabaseHelper.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", votacionEditada.getNombre());
        valoresParaActualizar.put("descripcion", votacionEditada.getDescripcion());
        valoresParaActualizar.put("valoracion", votacionEditada.getValoracion());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idVotacion
        String[] argumentosParaActualizar = {String.valueOf(votacionEditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Votacion> obtenerVotaciones() {
        ArrayList<Votacion> votaciones = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = DatabaseHelper.getReadableDatabase();
        // SELECT nombre, descripcion, valoracion, id
        String[] columnasAConsultar = {"nombre", "descripcion", "valoracion", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from votaciones
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return votaciones;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return votaciones;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de votaciones
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, descripcion, valoracion, id entonces el nombre es 0, descripcion 1, valoracion 2 e id es 3
            String nombreObtenidoDeBD = cursor.getString(0);
            String descripcionObtenidaDeBD = cursor.getString(1);
            int valoracionObtenidaDeBD = cursor.getInt(2);
            long idVotacion = cursor.getLong(3);
            Votacion votacionObtenidaDeBD = new Votacion(nombreObtenidoDeBD, descripcionObtenidaDeBD, valoracionObtenidaDeBD, idVotacion);
            votaciones.add(votacionObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de votaciones :)
        cursor.close();
        return votaciones;
    }
}
