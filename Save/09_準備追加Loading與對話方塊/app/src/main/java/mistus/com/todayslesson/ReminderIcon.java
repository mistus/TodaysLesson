package mistus.com.todayslesson;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mistus on 2016/7/29.
 */
public class ReminderIcon extends View {

    Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    public ReminderIcon(Context context) {
        super(context);

    }

    public ReminderIcon(Context context,AttributeSet attributeSet){
        super(context,attributeSet);

    }

    public ReminderIcon(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);

    }
}
