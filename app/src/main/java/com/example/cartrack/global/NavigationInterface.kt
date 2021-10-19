package com.example.cartrack.global

import android.content.DialogInterface
import androidx.activity.result.ActivityResult

public interface NavigationInterface {
    interface onActivityResult {
        fun onActivityResult(result: ActivityResult?)
    }
}