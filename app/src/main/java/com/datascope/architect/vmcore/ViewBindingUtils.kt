package com.datascope.architect.vmcore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
class ViewBindingUtil {
    companion object {


        @Suppress("UNCHECKED_CAST")
        fun <TLayout> getClassWithIndex(
            viewClass: Class<Any>,
            index: Int
        ): Class<TLayout> {

            val cls =
                (viewClass.genericSuperclass as ParameterizedType).actualTypeArguments[index] as Class<TLayout>
            return cls
        }

        @Suppress("UNCHECKED_CAST")
        fun <TLayout : ViewBinding> getViewBindingClass(viewClass: Class<Any>): Class<TLayout> {
            return if (viewClass.name.endsWith("_")) { // Workaround for Android Annotations
                val superClass = viewClass.superclass
                val genericSuperclass = superClass?.genericSuperclass
                (genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<TLayout>
            } else {
                val argsSize =
                    (viewClass.genericSuperclass as ParameterizedType).actualTypeArguments.size
                if (argsSize > 1) {
                    // Get last parameter
                    (viewClass.genericSuperclass as ParameterizedType).actualTypeArguments[argsSize - 1] as Class<TLayout>

                } else {
                    (viewClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<TLayout>
                }
            }
        }

        inline fun <reified T : ViewBinding> bind(view: View): T {
            return T::class.java
                .getMethod("bind", View::class.java)
                .invoke(null, view) as T
        }

        fun <T : ViewBinding> bind(view: View, clazz: Class<T>): T {
            return clazz
                .getMethod("bind", View::class.java)
                .invoke(null, view) as T
        }

        inline fun <reified T : ViewBinding> inflate(
            inflater: LayoutInflater
        ): T {
            return T::class.java
                .getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, inflater) as T
        }

        fun <T : ViewBinding> inflate(
            inflater: LayoutInflater,
            clazz: Class<T>
        ): T {
            return clazz
                .getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, inflater) as T
        }

        fun <T : ViewBinding> inflate(
            inflater: LayoutInflater,
            root: ViewGroup?,
            attachToRoot: Boolean,
            clazz: Class<T>
        ): T {
            return clazz
                .getMethod(
                    "inflate",
                    LayoutInflater::class.java,
                    ViewGroup::class.java,
                    Boolean::class.java
                )
                .invoke(null, inflater, root, attachToRoot) as T
        }
    }
}