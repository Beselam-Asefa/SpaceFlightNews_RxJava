package com.example.spaceflightnews.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * the Application class for integrating hilt in this application
 */
@HiltAndroidApp
class NewsApplication : Application()