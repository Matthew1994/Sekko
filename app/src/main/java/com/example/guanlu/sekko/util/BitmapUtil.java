package com.example.guanlu.sekko.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by guanlu on 16/5/8.
 */
public class BitmapUtil {



    public  static Bitmap AfterBlurring(Context context, Bitmap bitmap, int width, int height) {
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap != null && context != null) {
            result = blurBitmap(bitmap, context);
            result = big(result, width, height);
        }
        return result;
    }

    public  static Bitmap AfterCut(Context context, Bitmap bitmap, int width, int height) {
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap != null && context != null) {
            //result = blurBitmap(bitmap, context);
            result = big(result, width, height);
        }
        return result;
    }

    public static Bitmap blurBitmap(Bitmap bitmap, Context context) {

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        float radius = 25.0f;
        blurScript.setRadius(radius);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        //bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;

    }

    public  static Bitmap big(Bitmap bitmap, int width, int height) {
        Matrix matrix = new Matrix();
        matrix.postScale((float) width / (float) bitmap.getWidth(), (float) height / (float) bitmap.getHeight()); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }
    public  static Bitmap createReflectedBitmap(Bitmap srcBitmap)
    {
        if (null == srcBitmap)
        {
            return null;
        }

        // The gap between the reflection bitmap and original bitmap.
        final int REFLECTION_GAP = 4;

        int srcWidth  = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        int reflectionWidth  = srcBitmap.getWidth();
        int reflectionHeight = srcBitmap.getHeight() / 3;

        if (0 == srcWidth || srcHeight == 0)
        {
            return null;
        }

        // The matrix
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        try
        {
            // The reflection bitmap, width is same with original's, height is half of original's.
            Bitmap reflectionBitmap = Bitmap.createBitmap(
                    srcBitmap,
                    0,
                    srcHeight / 3,
                    srcWidth,
                    srcHeight / 3,
                    matrix,
                    false);

            if (null == reflectionBitmap)
            {
                return null;
            }

            // Create the bitmap which contains original and reflection bitmap.
            Bitmap bitmapWithReflection = Bitmap.createBitmap(
                    reflectionWidth,
                    srcHeight + reflectionHeight + REFLECTION_GAP,
                    Bitmap.Config.ARGB_8888);

            if (null == bitmapWithReflection)
            {
                return null;
            }

            // Prepare the canvas to draw stuff.
            Canvas canvas = new Canvas(bitmapWithReflection);

            // Draw the original bitmap.
            canvas.drawBitmap(srcBitmap, 0, 0, null);

            // Draw the reflection bitmap.
            canvas.drawBitmap(reflectionBitmap, 0, srcHeight + REFLECTION_GAP, null);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            LinearGradient shader = new LinearGradient(
                    0,
                    srcHeight,
                    0,
                    bitmapWithReflection.getHeight() + REFLECTION_GAP,
                    0x70FFFFFF,
                    0x00FFFFFF,
                    Shader.TileMode.MIRROR);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));

            // Draw the linear shader.
            canvas.drawRect(
                    0,
                    srcHeight,
                    srcWidth,
                    bitmapWithReflection.getHeight() + REFLECTION_GAP,
                    paint);

            return bitmapWithReflection;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
