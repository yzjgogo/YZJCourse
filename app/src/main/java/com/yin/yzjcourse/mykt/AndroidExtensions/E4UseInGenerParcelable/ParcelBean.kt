package com.yin.yzjcourse.mykt.AndroidExtensions.E4UseInGenerParcelable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 只需实现Parcelable，并且用Parcelize注解，即可实现Parcelable,此时这个类已经实现了Parcelable,
 * 隐藏了所有Parcelable的样板代码。
 *
 * 是不是很简介
 */
@Parcelize
class ParcelBean(val title: String, val amount: Int) : Parcelable