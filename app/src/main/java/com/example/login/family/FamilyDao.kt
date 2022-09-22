package com.example.login.family

import androidx.room.*

@Dao
interface FamilyDao {
    @Insert
    suspend fun addNote(note: Family)
    @Update
    suspend fun updateNote(note: Family)
    @Delete
    suspend fun deleteNote(note: Family)
    @Query("SELECT * FROM family")
    suspend fun getNotes() : List<Family>
    @Query("SELECT * FROM family WHERE id =:note_id")
    suspend fun getNote(note_id: Int) : List<Family>
}