package forged.expedition.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import forged.expedition.util.Utils;

/**
 * Created by visitor15 on 10/26/14.
 */
public class CircularImageView extends ImageView {

    private Bitmap bitmap;

    private Bitmap circleBitmap;

    private Canvas canvas;

    private BitmapShader shader;

    private Drawable mDrawable;

    private Paint paint;

    private static int toSetDips = 0;

    public CircularImageView(Context context) {
        super(context);
        init(null);
    }


    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        toSetDips = Utils.dipsToPixels(getContext(), 48);
////        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.google_icon);
//        circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//
////        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        paint = new Paint();
////        paint.setShader(shader);
//        paint.setAntiAlias(true);
//        paint.setColor(getResources().getColor(R.color.red_overlay));
//
//        canvas = new Canvas(circleBitmap);
//        canvas.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getHeight()/2.1f, paint);
//        this.setImageBitmap(circleBitmap);
    }

    public void setColor(int colorId) {



//        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.google_icon);
        circleBitmap = Bitmap.createBitmap(toSetDips, toSetDips, Bitmap.Config.ARGB_8888);

//        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint = new Paint();
//        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(colorId));

        canvas = new Canvas(circleBitmap);
        canvas.drawCircle(toSetDips/2, toSetDips/2, toSetDips / 2.1f, paint);
        this.setImageBitmap(circleBitmap);
    }
}