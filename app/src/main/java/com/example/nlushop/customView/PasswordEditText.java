package com.example.nlushop.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.nlushop.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

@SuppressLint("AppCompatCustomView")
public class PasswordEditText extends EditText {
    Drawable eyeVisible, eyeHide;
    Boolean visible = false;
    Boolean useEyeHide = false;
    Drawable drawable;
    int ALPHA = (int) (255 * .55f);
    Boolean useValidate = false;

    public PasswordEditText(Context context) {
        super(context);
        create(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        create(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        create(attrs);
    }

    private void create(AttributeSet attributeSet){
        if(attributeSet != null){
            //lay du lieu ben style.xml va giao dien password edit text
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.PasswordEditText, 0, 0);
            this.useEyeHide = typedArray.getBoolean(R.styleable.PasswordEditText_useEyeHide, false);
            this.useValidate = typedArray.getBoolean(R.styleable.PasswordEditText_useValidate, false);
        }
        //hinh anh icon
        eyeVisible = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeHide = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
//        if(this.useValidate){
//            setOnFocusChangeListener(new OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
                    //n???u kh??ng focus th?? xu???t l???i n???u c??
//                    if(!hasFocus){
//                        String chuoi = getText().toString();
//                        ViewParent parent = v.getParent();
//                        TextInputLayout textInputLayout =  v.getParent();// v?? textinputlayout l?? cha c???a PasswordEditText
//                        if (true) {
//                            EditText textInputLayout = (EditText) parent;
////                            textInputLayout.setErrorEnabled(true);//cho ph??p xu???t l???i
//                            textInputLayout.setError("L???i r???i!");
//                            TextInputLayout til = (TextInputLayout) findViewById(R.id.input_edMatKhauDangKy);
//                            til.setError("You need to enter a name");
//                        }
//                    }
//                }
//            });
//        }
        setup();
    }

    private void setup(){
        //set an hien password text
        setInputType(InputType.TYPE_CLASS_TEXT | (visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        //neu nguoi dung bam eyehide vaf visible = false thi an di
        //Mac dinh visible la false va useEyeHide duoc set ben layout edittext la true
        drawable = (useEyeHide && !visible)? eyeHide : eyeVisible;
        //set cho icon m??? ??i 55%
        drawable.setAlpha(ALPHA);
        //Lay draw cua the input text, t???c l?? 4 khu v???c tr??i, tr??n, ph???i, d?????i
        Drawable[] drawables = getCompoundDrawables();
        //set hinh anh vao password edit text t???i khu v???c b??n PH???I
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //event.getAction() == MotionEvent.ACTION_DOWN--kiem tra cham vao man hinh
        //event.getX() >= getRight() - drawable.getBounds().width()--toa do X (phai la event, neu khong de event se hieu la getX cua edittext giong nhu getRight)
        // diem touch vao phai >= getRight(la diem cuoi edittext)
        // - drawable.getBounds().width()(la width icon)
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) ){
            visible = !visible;
            setup();
            //Kiem tra lai man hinh neu co thay doi
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
