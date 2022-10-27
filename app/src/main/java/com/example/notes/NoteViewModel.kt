package com.example.notes

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}
//class NoteViewModel(application: Application) : AndroidViewModel(application){
//
//    val allNotes: LiveData<List<Note>>
//    private val repository: NoteRepository
//
//    init{
//        val dao = NoteDatabase.getDatabase(application).getNoteDao()
//        repository= NoteRepository(dao)
//        allNotes = repository.allNotes
//    }
//    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
//        repository.delete(note)
//    }
//    fun insertNote(note: Note)= viewModelScope.launch(Dispatchers.IO) {
//        repository.insert(note)
//    }
//
//}
//
//class NoteViewModelFactory(application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
//    private val repository: NoteRepository = TODO()
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return NoteViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}