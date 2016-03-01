package fi.etla.strokehelper.common;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class MyEditText extends EditText {

	public MyEditText(Context context) {
		super(context);
		
	}
	
	public MyEditText(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public MyEditText(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
	
	

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	



	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}


	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event) {
	  if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	    this.clearFocus();
	  }
	  return false;
	}
	
	
}
