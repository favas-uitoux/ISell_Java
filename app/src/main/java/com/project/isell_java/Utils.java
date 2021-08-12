package com.project.isell_java;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

public final class Utils {




    public static String getProgressDisplayLine(long currentBytes, long totalBytes) {
        return getBytesToMBString(currentBytes) + "/" + getBytesToMBString(totalBytes);
    }

    private static String getBytesToMBString(long bytes){
        return String.format(Locale.ENGLISH, "%.2fMb", bytes / (1024.00 * 1024.00));
    }



}
