package com.example.todolist

class Note {
    companion object{
        var data :  kotlin.collections.MutableList<Note> = mutableListOf<Note>()
    }

    var dataTitle: String? = null
    var dataContent: String? = null
    var dataDate: String? = null

    constructor(dataTitle: String?, dataContent: String?, date:String?){
        this.dataTitle = dataTitle
        this.dataContent = dataContent
        this.dataDate  = date
    }
    constructor()
    {}
}