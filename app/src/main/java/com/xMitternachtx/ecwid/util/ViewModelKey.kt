package com.xMitternachtx.ecwid.util

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


/**
*
 */

@Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.MustBeDocumented
@MapKey
@kotlin.annotation.Retention
annotation class ViewModelKey(val value: KClass<out ViewModel>)