package com.techyshed.volleyk

class UserData{

    var id:Int?=null
    var title:String?=null
    var des:String?=null
    var url:String?=null

    constructor(id:Int, title:String, des:String, url:String){
        this.id = id
        this.title = title
        this.des = des
        this.url = url
    }
}