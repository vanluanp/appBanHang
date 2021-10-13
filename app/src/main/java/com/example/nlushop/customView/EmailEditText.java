package com.example.nlushop.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.nlushop.R;

@SuppressLint("AppCompatCustomView")
public class EmailEditText extends EditText {
    Drawable crossx, nonecrossx;
    Boolean visible = false;
    Drawable drawable;

    public EmailEditText(Context context) {
        super(context);
        create();
    }

    public EmailEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        create();
    }

    public EmailEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EmailEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        create();
    }

    private void create(){
        crossx = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nonecrossx = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();

        setup();
    }

    private void setup(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? crossx : nonecrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1],drawable , drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width()) ){
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        //neu lengthafter = 0 va start = 0(tuc la bat dau tai vi tri 0 thi khong hien thi dau X
        //vì nếu lengthafter khác 0 thì chỉ mới là xóa 1 kí tự thôi, có thể chuỗi còn
        //nên thích hợp nhất là lengthafter khác 0 và start = 0.
        if(lengthAfter == 0 && start == 0){
            visible = false;
            setup();
        }else{
            visible = true;
            setup();
        }
    }
}
