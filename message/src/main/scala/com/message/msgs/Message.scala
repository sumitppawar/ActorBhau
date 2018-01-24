package com.message.msgs


case class SetValueRequest(k:String, v: String)

case class GetValueRequest(k:String)

case class ValueNotFoundException(msg: String) extends Exception(msg)