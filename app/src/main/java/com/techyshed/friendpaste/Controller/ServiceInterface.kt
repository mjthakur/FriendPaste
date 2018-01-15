package com.techyshed.volleyk

import org.json.JSONObject
import java.nio.channels.CompletionHandler

/**
 * Created by Mj 2 on 12-Jan-18.
 */
interface ServiceInterface{
    fun post(path:String, params:JSONObject, completionHandler:(response:JSONObject?) ->Unit )
    fun getReq(path:String, completionHandler:(response:String?) ->Unit)
}