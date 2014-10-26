package forged.expedition.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import forged.expedition.R;

/**
 * Created by visitor15 on 10/26/14.
 */
public class CircularImageView extends ImageView {

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
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.google_icon);
        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
//        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.red_overlay));

        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getHeight()/2.1f, paint);
        this.setImageBitmap(circleBitmap);
    }

    public void initWithNewImage() {

        Drawable mDrawable = this.getDrawable();

        Bitmap bitmap = Bitmap.createBitmap(mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        mDrawable.draw(canvas);

        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);

        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getHeight()/2, paint);
        this.setImageBitmap(circleBitmap);
    }
}