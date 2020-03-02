package com.example.inote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.inote.model.db.NoteEntity
import com.example.inote.editnote.viewmodel.EditNoteViewModel
import com.example.inote.model.NoteListRepository
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EditViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: NoteListRepository


    private lateinit var editNoteViewModel: EditNoteViewModel


    private lateinit var note: NoteEntity


   @Before
    fun setUp(){

       editNoteViewModel = EditNoteViewModel(repository)
    }


    @Test
    fun `getnotesuccess gets single note successfully`(){
        //Given
        val id:Int = 1
        note =  NoteEntity(id,"note","note")
        `when`(repository.getNote(id)).thenReturn(Observable.just(note))

        //When
        editNoteViewModel.getNote(id)

        //Then
        Assert.assertEquals(note, editNoteViewModel.noteSuccess.value)
    }

}