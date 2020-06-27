package com.caparepa.basemvvmboilerplate.internal


import java.io.IOException

class NoConnectivityException : IOException()

class LocationPermissionNotGrantedException : Exception()

class DateNotFoundException : Exception()

class ApiException(message: String) : IOException(message)
