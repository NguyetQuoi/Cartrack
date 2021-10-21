package com.example.cartrack.extention

import android.graphics.Color
import android.graphics.PorterDuff
import android.text.SpannableStringBuilder
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.util.Linkify
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputLayout
import com.example.cartrack.R
import com.example.cartrack.base.BaseRecyclerViewAdapter
import timber.log.Timber
import java.util.regex.Pattern

/**
 * Action: set refreshing value
 * @param visible value which will be set to [refreshing] variable
 */
@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: Boolean) {
    isRefreshing = visible
}

/**
 * Set background for view
 * @param view which will be set
 * @param isToggle [Boolean] toggle or not
 */
@BindingAdapter("passwordToggle")
fun setBackgroundWithId(view: EditText, isToggle: Boolean) {
    if (isToggle) {
        view.transformationMethod = HideReturnsTransformationMethod.getInstance()
    } else {
        view.transformationMethod = PasswordTransformationMethod.getInstance()
    }
}

/**
 * Action: bind visibility for view
 * @param view which will be set
 * @param visible which state want to be set for this view
 * GONE - VISIBLE - INVISIBLE
 */
@BindingAdapter("android:visibility")
fun bindVisibility(view: View, visible: Boolean?) {
    if (visible == null)
        view.visibility = View.GONE
    else {
        val visibility = if (visible) View.VISIBLE else View.GONE
        view.visibility = visibility
    }
}

/**
 * Action: bind isChecked for imageView
 * @param imageView which will be set
 * @param isChecked which state want to be set for this imageView
 */
@BindingAdapter("android:checked")
fun bindVisibility(imageView: ImageView, isChecked: Boolean) {
    val stateSet = intArrayOf(android.R.attr.state_checked * if (isChecked) 1 else -1)
    imageView.setImageState(stateSet, true)
}

/**
 * Action: set adapter for recycle view
 * @param recyclerView which will be set
 * @param adapter resource
 * @param itemClick a handler for item click
 * @param itemLongClick a hander for item long click
 */
@BindingAdapter(value = ["bind:adapter", "bind:itemClick", "bind:itemLongClick"], requireAll = false)
fun <T : BaseRecyclerViewAdapter<*>> setRecyclerViewAdapter(recyclerView: RecyclerView, adapter: T,
                                                            itemClick: BaseRecyclerViewAdapter.ItemClickListener?,
                                                            itemLongClick: BaseRecyclerViewAdapter.ItemLongClickListener?) {
    recyclerView.adapter = adapter
    if (itemClick != null) adapter.setItemClickListener(itemClick)
    if (itemLongClick != null) adapter.setItemLongClickListener(itemLongClick)
}

/**
 * On action of doing swipe view to refresh the state of view
 * @param refreshLayout which layout want to refresh
 * @param listener listener for the refresh handler
 */
@BindingAdapter("bind:onRefresh")
fun onSwipeToRefresh(refreshLayout: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener) {
    refreshLayout.setOnRefreshListener(listener)
}

/**
 * Action: set resource for image view
 * @param imageView which resource will set for
 * @param resource the resource which will set for image view
 */
@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

/**
 * Action: set color for text of text view
 * @param textView which text will set to
 * @param color color of text
 */
@BindingAdapter("android:textColor")
fun setTextColor(textView: TextView, color: String?) {
    if (color == null || color.isEmpty()) return
    val eventColor = Color.parseColor(color)
    textView.setTextColor(eventColor)
}

/**
 * Action: ser tint color for image view
 * @param imageView which will be set
 * @param color tint color
 */
@BindingAdapter("android:tint")
fun seImageTintColor(imageView: ImageView, color: String?) {
    if (color == null || color.isEmpty()) return
    val eventColor = Color.parseColor(color)
    imageView.setColorFilter(eventColor, PorterDuff.Mode.SRC_IN)
}

/**
 * Action: set text for text view
 * @param textView which will be set
 * @param stringId resource id of string
 */
@BindingAdapter("android:text")
fun setTextWithId(textView: TextView, stringId: Int) {
    textView.setText(stringId)
}

/**
 * Action: set text for text view
 * @param textView which will be set
 * @param string a string with custom format as SpannableStringBuilder
 */
@BindingAdapter("android:text")
fun setTextWithId(textView: TextView, string: SpannableStringBuilder) {
    textView.text = string
}

/**
 * Action: set hint for input layout
 * @param inputLayout which will be set
 * @param stringId resource id of string
 */
@BindingAdapter("android:hint")
fun setHintWithId(inputLayout: TextInputLayout, stringId: Int?) {
    if (stringId == null) return
    val str = inputLayout.context.getString(stringId)
    inputLayout.hint = str
}

/**
 * Action: set link for text view
 * @param textview which will be set
 * @param link url
 */
@BindingAdapter("bind:link")
fun setTextviewLink(textview: TextView, link: String) {
    val pattern = Pattern.compile(link)
    Linkify.addLinks(textview, pattern, "http://")
}

/**
 * Action: set error text for input layout in case something wrong with the input
 * @param textInputLayout which will be set
 * @param error resource string id of error text
 */
@BindingAdapter("bind:errorText")
fun setErrorText(textInputLayout: TextInputLayout, error: Int) {
    if (error <= 0)
        textInputLayout.error = ""
    else
        textInputLayout.error = textInputLayout.context.getString(error)
}

/**
 * Action set source for spinner
 * @param spinner which will be set
 * @param arrayId resource
 * @param selectedIndex index of resource
 */
@BindingAdapter(value = ["bind:arrayId", "bind:selectedIndex"], requireAll = false)
fun setSpinnerSource(spinner: Spinner, arrayId: Int, selectedIndex: Int) {
    val adapter = ArrayAdapter.createFromResource(spinner.context, arrayId, R.layout.item_spinner_text)
    adapter.setDropDownViewResource(R.layout.item_spinner_text_dropdown)
    spinner.adapter = adapter
    if (selectedIndex >= 0 && selectedIndex < adapter.count && selectedIndex != spinner.selectedItemPosition)
        spinner.setSelection(selectedIndex)
}

/**
 * Action: get selected index of the spinner
 * @param spinner spinner
 * @return index of selected item
 */
@InverseBindingAdapter(attribute = "bind:selectedIndex")
fun getSpinnerSelectedIndex(spinner: Spinner): Int {
    return spinner.selectedItemPosition
}

/**
 * Action: set handler for item selected or nothing selected
 * @param spinner spinner
 * @param attrChange handler
 */
@BindingAdapter("app:selectedIndexAttrChanged")
fun setOnSpinnerItemSelected(spinner: Spinner, attrChange: InverseBindingListener) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            attrChange.onChange()
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
        }
    }
}

/**
 * Action: set background for view
 * @param view which will be set
 * @param drawableId resource id of drawable
 */
@BindingAdapter("android:background")
fun setBackgroundWithId(view: View, drawableId: Int) {
    if (drawableId != 0)
        view.setBackgroundResource(drawableId)
}

/**
 * Set view number for text view
 * @param view which will be set
 * @param number number of view
 */
@BindingAdapter("android:backgroundTint")
fun setBackgroundTint(view: Button, color: Int) {
    view.backgroundTintList = ContextCompat.getColorStateList(view.context, color)
    view.backgroundTintMode = PorterDuff.Mode.SRC_OVER
}

/**
 * Set constraint param for view
 */
@BindingAdapter(
        "layout_constraintTop_toTopOf",
        "layout_constraintTop_toBottomOf",
        requireAll = false)
fun setLayoutConstraint(view: View, top2top: Int?, top2bottom: Int?) {
    Timber.d("top2top$top2top, top2bottom$top2bottom")
    val constraintLayout: ConstraintLayout = view.parent as ConstraintLayout
    val constraintSet = ConstraintSet()
    constraintSet.clone(constraintLayout)

    if (top2top != null && top2bottom != null) {
        constraintSet.clear(view.id, ConstraintSet.TOP)
        if (top2top != -1) constraintSet.connect(view.id, ConstraintSet.TOP, if (top2top == 0) ConstraintSet.PARENT_ID else top2top, ConstraintSet.TOP)
        else constraintSet.connect(view.id, ConstraintSet.TOP, if (top2bottom == 0) ConstraintSet.PARENT_ID else top2bottom, ConstraintSet.BOTTOM)
    } else {
        top2top?.let {
            if (it != -1) constraintSet.connect(view.id, ConstraintSet.TOP, if (it == 0) ConstraintSet.PARENT_ID else it, ConstraintSet.TOP)
        }

        top2bottom?.let {
            if (it != -1) constraintSet.connect(view.id, ConstraintSet.TOP, if (it == 0) ConstraintSet.PARENT_ID else it, ConstraintSet.BOTTOM)
        }
    }

    constraintLayout.setConstraintSet(constraintSet)
}

@BindingAdapter("android:layout_marginStart",
        "android:layout_marginEnd",
        "android:layout_marginTop",
        "android:layout_marginBottom",
        requireAll = false)
fun setMargins(view: View, marginStart: Float?, marginEnd: Float?, marginTop: Float?, marginBottom: Float?) {
    val p = view.layoutParams as? ViewGroup.MarginLayoutParams
    p?.setMargins(marginStart?.toInt() ?: 0, marginTop?.toInt() ?: 0, marginEnd?.toInt()
            ?: 0, marginBottom?.toInt() ?: 0)
}

@BindingAdapter("app:bindingHeight")
fun setMaxHeight(view: View, dimenRes: Int) {
    view.layoutParams.height = view.resources.getDimensionPixelSize(dimenRes)
}

@BindingAdapter("android:textSize")
fun setTextSize(view: TextView, dimenRes: Int) {
    view.setTextSize(TypedValue.COMPLEX_UNIT_PX, view.resources.getDimensionPixelSize(dimenRes).toFloat())
}