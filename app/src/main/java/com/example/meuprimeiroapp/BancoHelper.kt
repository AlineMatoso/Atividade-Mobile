package com.example.meuprimeiroapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context) :
    SQLiteOpenHelper(context, "workspot.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE reservas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                data TEXT,
                local TEXT,
                descricao TEXT,
                favorito INTEGER DEFAULT 0
            )
        """.trimIndent())
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) { }

    fun inserir(reserva: Reserva): Long {
        val valores = ContentValues().apply {
            put("nome", reserva.nome)
            put("data", reserva.data)
            put("local", reserva.local)
            put("descricao", reserva.descricao)
            put("favorito", if (reserva.favorito) 1 else 0)
        }

        return writableDatabase.insert(
            "reservas",
            null,
            valores
        )
    }

    fun listar(): MutableList<Reserva> {
        val lista = mutableListOf<Reserva>()
        val cursor = readableDatabase.rawQuery(
            "SELECT * FROM reservas ORDER BY id DESC",
            null
        )

        while (cursor.moveToNext()) {
            lista.add(
                Reserva(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                    data = cursor.getString(cursor.getColumnIndexOrThrow("data")),
                    local = cursor.getString(cursor.getColumnIndexOrThrow("local")),
                    descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao")),
                    favorito = cursor.getInt(cursor.getColumnIndexOrThrow("favorito")) == 1
                )
            )
        }
        cursor.close()
        return lista
    }

    fun buscarPorId(id: Int): Reserva? {
        val cursor = readableDatabase.rawQuery(
            "SELECT * FROM reservas WHERE id = ?",
            arrayOf(id.toString())
        )
        val reserva = if (cursor.moveToFirst()) {
            Reserva(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                data = cursor.getString(cursor.getColumnIndexOrThrow("data")),
                local = cursor.getString(cursor.getColumnIndexOrThrow("local")),
                descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao")),
                favorito = cursor.getInt(cursor.getColumnIndexOrThrow("favorito")) == 1
            )
        } else null

        cursor.close()
        return reserva
    }


    fun excluir(id: Int): Boolean {
        val banco = writableDatabase
        // O comando delete retorna o número de linhas afetadas.
        // Se for maior que 0, significa que excluiu com sucesso.
        val linhasDeletadas = banco.delete(
            "reservas",
            "id = ?",
            arrayOf(id.toString())
        )
        return linhasDeletadas > 0
    }

    fun atualizar(reserva: Reserva): Boolean {
        val valores = android.content.ContentValues().apply {
            put("nome", reserva.nome)
            put("data", reserva.data)
            put("local", reserva.local)
            put("descricao", reserva.descricao)
            // Se tivermos a função de favoritar no futuro, ela já salva aqui também
            put("favorito", if (reserva.favorito) 1 else 0)
        }

        val banco = writableDatabase
        val linhasAtualizadas = banco.update(
            "reservas",
            valores,
            "id = ?",
            arrayOf(reserva.id.toString())
        )
        return linhasAtualizadas > 0
    }
}
