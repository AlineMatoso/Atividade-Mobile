package com.example.meuprimeiroapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context) :
    SQLiteOpenHelper(context, "workspot.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Criando a tabela de reservas
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

    // Função adaptada do put.txt para inserir uma Reserva
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
}